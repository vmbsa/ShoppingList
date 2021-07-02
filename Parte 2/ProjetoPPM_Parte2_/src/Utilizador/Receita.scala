package Utilizador
import java.io.File,Utilizador.Produto.listaProdutosGeral,scala.annotation.tailrec

class Receita()

object Receita {

  val ficheiroReceita = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/ListaReceitas.txt")
  var nome = ""
  var ids = ""

  def criarReceita() {
    print("Insira o nome da Receita: ")
    nome = scala.io.StdIn.readLine()
    Produto.verListaBD(listaProdutosGeral)
    println("Escolha os ID´s dos produtos que pretende adicionar, separado por virgulas")
    ids = scala.io.StdIn.readLine()
    if (verSeExistemingredientes(ids.split(",").toList)) adicionarLista(List(nome + "," + ids))
  }

  @tailrec
  def verSeExistemingredientes(ids: List[String]): Boolean = {
    ids match {
      case Nil => true
      case x :: Nil => if (x.toInt>(Produto.id-1)){ println("O Produto "+ x + " nao existente"); false }; else true
      case x :: t => if (x.toInt.>(Produto.id-1)) { println("O Produto "+ x + " nao existente"); false }; else
        verSeExistemingredientes(t)
    }
  }

  def adicionarLista(list: List[String]): Unit = {
    Sistema.Organizador.writeToFile(ficheiroReceita, list, ",", true)
  }

  def verReceitas() {
    val bd: List[String] = Sistema.Organizador.readFromFile(ficheiroReceita.toString)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => println("Nao ha receitas na base de dados")
        case x :: Nil => print(x.split(",")(0)+": ")
          escreveIngredientes(x.substring(x.split(",")(0).length+1))
        case x :: t => print(x.split(",")(0)+": ")
          escreveIngredientes(x.substring(x.split(",")(0).length+1))
          helper(t)
      }
    }
    helper(bd)
  }

  def escreveIngredientes(subs:String) {
    val list: List[String] = subs.split(",").toList
    def helper(arg:List[String]):String ={
      arg match {
        case Nil=> ""
        case x :: Nil =>println(Produto.getNome(x.split(",")(0).toInt)) ; ""
        case x :: t =>print(Produto.getNome(x.split(",")(0).toInt) + ","); helper(t)
      }
    }
    helper(list)
  }

}


