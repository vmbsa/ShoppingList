package Sistema
import java.io.{File, IOException}

import Sistema.Organizador._
import Utilizador.{Login, Menu, Produto, Receita}

import scala.util.{Failure, Success, Try}

class ListaPessoal()

object ListaPessoal {
  val ficheiroPro = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Melhoria codigo parte 1/ProjetoPPM/src/Base de Dados/ListaProdutos.txt")
  val ficheiroLPs = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Melhoria codigo parte 1/ProjetoPPM/src/Base de Dados/ListaUtilizadores.txt")
  val ficheiroPla: List[String] = Organizador.readFromFile(ficheiroLPs.toString)
  var plastico = 0
  var id = 0

  def adicionaProdutoLista(id: String): Unit = {
    if(Login.idade<18) {
      if (Categorias.verCategoriaProduto(id.toInt).equals("bebida alcoolica")) {
        Organizador.printString("É proibida a venda de bebidas alcoolicas a menores de idade")
        return
      }
    }
    val l = readFromFile(ficheiroLPs.toString)
    helper(l)
    def helper(lista: List[String]) {
      lista match {
        case Nil => Organizador.writeToFile(ficheiroLPs, List(Login.user + "," + id), "\n", false)
        case x :: Nil => if (x.split(",")(0).equals(Login.user)) {
          val novaLista = l.updated(l.indexOf(x), x.concat("," + id))
          Organizador.writeToFile(ficheiroLPs, novaLista, "\n", false)
        } else {
          Organizador.writeToFile(ficheiroLPs, List(Login.user+","+id), "\n", true)
        }
        case x :: t => if (x.split(",")(0).equals(Login.user)) {
          val novaLista = l.updated(l.indexOf(x), x.concat("," + id))
          Organizador.writeToFile(ficheiroLPs, novaLista, "\n", false)
        } else {
          helper(t)
        }
      }
    }
  }


  def adicionaReceitaLP (receita: String): Unit ={
    val listaReceita: List[String] = Sistema.Organizador.readFromFile(Utilizador.Receita.ficheiroReceita.toString)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil =>Organizador.printString("A receita esta vazia. Nao ha produtos a adicionar")
        case x :: Nil => if(x.split(",")(0).equals(receita)){
          adicionaProdutoLista(x.substring(receita.length+1))
        }else{
          Organizador.printString("ERRO: Nao existe nenhuma receita com esse nome")
        }
        case x :: t => if(x.split(",")(0).equals(receita)) {
          adicionaProdutoLista(x.substring(receita.length+1))
        }
        else helper(t)
      }
    }
    helper(listaReceita)
  }

  def copiaLP (username: String): Unit ={
    val listaReceita: List[String] = Sistema.Organizador.readFromFile(ficheiroLPs.toString)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => Organizador.printString("Nenhum utilizador tem uma lista criada. Tenta mais tarde")
        case x :: Nil => if(x.split(",")(0).equals(username)){
          adicionaProdutoLista(x.substring(username.length+1))
        }else{
          Organizador.printString("ERRO: Nao existe nenhuma utilizador com esse nome")
        }
        case x :: t => if(x.split(",")(0).equals(username)) {
          adicionaProdutoLista(x.substring(username.length+1))
        }
        else helper(t)
      }
    }
    try {
      helper(listaReceita)
    }
    catch{case _: Throwable => Organizador.printString("Esse utilizador tem a lista vazia")}
  }

  def imprimeLP(username: String): String ={
    val listaLP: List[String] = Sistema.Organizador.readFromFile(ficheiroLPs.toString)
    Organizador.printString("\n*************************\n")
    def helper(arg:List[String]):String ={
      arg match {
          case Nil => "Tens a lista vazia"
          case x :: Nil => if (x.split(",")(0).equals(username)) {
            try {
              Organizador.printString("Lista de compras:")
              Receita.escreveIngredientes(x.substring(x.split(",")(0).length + 1))

            }catch{case _: Throwable => Organizador.printString("Tem a Lista vazia")}
          }
            ""
          case x :: t => if (x.split(",")(0).equals(username)) {

            try {
              Organizador.printString("Lista de compras:")
              Receita.escreveIngredientes(x.substring(x.split(",")(0).length + 1))

            }catch{case _: Throwable => Organizador.printString("Tem a Lista vazia")}
          }
            helper(t)
        }

    }
    helper(listaLP)
    Organizador.printString("*************************\n\n")
    ""
  }

  def solucoesPlastico(): Unit = {
    try {
      funPlastico()
      val plas = plastico / id
      if (plas < 15) Organizador.printString("Pouca percentagem de plástico, continue assim!")
      else if (plas < 30) Organizador.printString("Percentagem de plástico baixa")
      else if (plas < 45) Organizador.printString("Percentagem de plástico media")
      else if (plas < 75) Organizador.printString("Percentagem de plástico muito alta")
      else {
        Organizador.printString("Grande percentagem de plástico, reconcidere alguns produtos..."
          + "Aqui estão alguns produtos que tem menos quantidade de plastico: ")
        val lista: List[String] = Organizador.readFromFile(ficheiroPro.toString)
        help(lista)

        def help(list: List[String]): Any = {

          list match {
            case Nil => Organizador.printString("Lista de Base dados vazia")
            case x :: Nil => if (x.split(",")(5).toInt < 15) Organizador.printString(x.split(",")(1) + "," + x.split(",")(5))
            case x :: t => if (x.split(",")(5).toInt < 15) Organizador.printString(x.split(",")(1) + "," + x.split(",")(5))
              help(t)
          }
        }

      }
    }
    catch {case _: Throwable => Organizador.printString("Nenhum produto na sua lista")}
  }

  def funPlastico() = {
    val list: List[String] = getListaProdutos().split(",").toList
    help(list)
    def help(lista: List[String]) {
      lista match {
        case Nil =>
        case x :: Nil =>
          plastico += Produto.getPlastico(x.split(",")(0).toInt);id=id+1
          Organizador.printString("Quantidade de plastico:" + plastico/id + "%")
        case x :: t =>
          plastico += Produto.getPlastico(x.split(",")(0).toInt);id=id+1
          help(t)
      }
    }
    plastico
  }

  def getListaProdutos(): String ={
    val l = readFromFile(ficheiroLPs.toString)
    var s = ""
    help(l)
    def help(list: List[String]): Unit ={
      list match {
        case Nil =>Organizador.printString("")
        case x :: Nil =>

          if(x.split(",")(0).equals(Login.user)) {
            s = x.substring(x.split(",")(0).length + 1)
          }
        case x :: t =>if(x.split(",")(0).equals(Login.user)) {
            s = x.substring(x.split(",")(0).length + 1)
            }
          help(t)
      }
    }
  s
  }

}