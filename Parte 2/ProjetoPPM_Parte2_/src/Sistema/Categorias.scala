package Sistema
import java.io.File

class Categorias()

object Categorias {

  val ficheiroCategorias = new File(s"${Utilizador.Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/ListaCategorias.txt")

  def verificarCategoria(categoria: String, nome: String): Boolean = {
    categoria.toUpperCase match {
      case ("BEBIDA" | "COMIDA" | "HIGIENE" | "LIMPEZA" | "BEBIDA ALCOOLICA") => adicionarCategoria(categoria, nome); true
      case _ => false
    }
  }

  def adicionarCategoria(categoria: String, nome: String) {
    val listaCategorias = Organizador.readFromFile(ficheiroCategorias.toString)
    helper(listaCategorias)
    def helper(lista: List[String]) {
      lista match {
        case x :: Nil => if (x.split(",")(0).toUpperCase().equals(categoria)) {
          val novaLista = listaCategorias.updated(listaCategorias.indexOf(x), x.concat("," + nome))
          Organizador.writeToFile(ficheiroCategorias, novaLista, ",", false)
        }
        case x :: t => if (x.split(",")(0).toUpperCase().equals(categoria)) {
          val novaLista = listaCategorias.updated(listaCategorias.indexOf(x), x.concat("," + nome))
          Organizador.writeToFile(ficheiroCategorias, novaLista, ",", false)
        } else helper(t)
      }
    }
  }

  def verListaCategoria(lista: List[String]): Unit = {
    lista match {
      case x :: Nil => println(x)
      case x :: t => println(x)
        verListaCategoria(t)
    }
  }

  def verCategoriaProduto(id: Int):String ={
    val listaProdutos: List[String] = Organizador.readFromFile(Utilizador.Produto.ficheiroProdutos.toString)
    var categoria = ""
    helper(listaProdutos)
    def helper(list: List[String]) {
      list match {
        case Nil => print("lista vazia")
        case x :: Nil => if (x.split(",")(0).equals(id.toString)) categoria = x.split(",")(2)
        case x :: t => if (x.split(",")(0).equals(id.toString))  categoria = x.split(",")(2)
        else helper(t)
      }
    }
    categoria
  }


}
