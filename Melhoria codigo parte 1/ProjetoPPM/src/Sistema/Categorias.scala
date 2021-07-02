package Sistema
import java.io.File

class Categorias()

object Categorias {

  val ficheiroCategorias = new File(s"${Utilizador.Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Melhoria codigo parte 1/ProjetoPPM/src/Base de Dados/ListaCategorias.txt")

  def verificarCategoria(categoria: String, nome: String): Boolean = {
    categoria.toUpperCase match {
      case ("BEBIDA" | "COMIDA" | "HIGIENE" | "LIMPEZA" | "BEBIDA ALCOOLICA" | "OUTROS") => adicionarCategoria(categoria, nome); true
      case _ => false
    }
  }

  def adicionarCategoria(categoria: String, nome: String) {
    val listaCategorias = Organizador.readFromFile(ficheiroCategorias.toString)
    helper(listaCategorias)
    def helper(lista: List[String]) {
      lista match {
        case x :: Nil =>if (x.split(",")(0).toLowerCase().equals(categoria)) {
          val novaLista = listaCategorias.updated(listaCategorias.indexOf(x), x.concat("," + nome))
          Organizador.writeToFile(ficheiroCategorias, novaLista, "\n", false)
        }
        case x :: t =>if (x.split(",")(0).toLowerCase().equals(categoria)) {
          val novaLista = listaCategorias.updated(listaCategorias.indexOf(x), x.concat("," + nome))
          Organizador.writeToFile(ficheiroCategorias, novaLista, "\n", false)
        } else helper(t)
      }
    }
  }

  def verListaCategoria(lista: List[String]): Unit = {
    lista match {
      case x :: Nil =>Organizador.printString(x.toString)
      case x :: t => Organizador.printString(x.toString)
        verListaCategoria(t)
    }
  }

  def verCategoriaProduto(id: Int):String ={
    val listaProdutos: List[String] = Organizador.readFromFile(Utilizador.Produto.ficheiroProdutos.toString)
    var categoria = ""
    helper(listaProdutos)
    def helper(list: List[String]) {
      list match {
        case Nil => Organizador.printString("lista vazia")
        case x :: Nil => if (x.split(",")(0).equals(id.toString)) categoria = x.split(",")(2)
        case x :: t => if (x.split(",")(0).equals(id.toString))  categoria = x.split(",")(2) else helper(t)
      }
    }
    categoria
  }


}
