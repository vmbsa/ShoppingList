package Sistema
import java.io.File

import Sistema.Organizador._
import Utilizador.{Login, Menu, Receita}

class ListaPessoal()

object ListaPessoal {
  val ficheiroLPs = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/ListaUtilizadores.txt")

  def adicionaProdutoLista(id: String): Unit = {
    if(Login.idade<18) {
      if (Categorias.verCategoriaProduto(id.toInt).equals("Bebida alcoolica")) {
        println("É proibida a venda de bebidas alcoolicas a menores de idade")
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
        case Nil => println("A receita esta vazia. Nao ha produtos a adicionar")
        case x :: Nil => if(x.split(",")(0).equals(receita)){
          adicionaProdutoLista(x.substring(receita.length+1))
        }else{
          println("ERRO: Nao existe nenhuma receita com esse nome")
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
        case Nil => println("Nenhum utilizador tem uma lista criada. Tenta mais tarde")
        case x :: Nil => if(x.split(",")(0).equals(username)){
          adicionaProdutoLista(x.substring(username.length+1))
        }else{
          println("ERRO: Nao existe nenhuma utilizador com esse nome")
        }
        case x :: t => if(x.split(",")(0).equals(username)) {
          adicionaProdutoLista(x.substring(username.length+1))
        }
        else helper(t)
      }
    }
    helper(listaReceita)
  }


  def imprimeLP(username: String): String ={
    val listaLP: List[String] = Sistema.Organizador.readFromFile(ficheiroLPs.toString)
    print("\n*************************\n")
    def helper(arg:List[String]):String ={
      arg match {
        case Nil=>"Tens a lista vazia"
        case x :: Nil => if(x.split(",")(0).equals(username)) {
          println("Lista de compras:")
          Receita.escreveIngredientes(x.substring(x.split(",")(0).length + 1))
        }
          ""
        case x :: t => if(x.split(",")(0).equals(username)) {
          println("Lista de compras:")
          Receita.escreveIngredientes(x.substring(x.split(",")(0).length+1))
        }
          helper(t)
          ""
      }
    }
    helper(listaLP)
    print("*************************\n\n")
    ""
  }



}