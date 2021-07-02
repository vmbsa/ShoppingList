package Utilizador

import java.io.File

import Sistema.Organizador

class Inventario()

object Inventario {

  val ficheiroInventario = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/ListaInventario.txt")


  def adicionarInventario(input: String): Unit = {
    Receita.verSeExistemingredientes((input.split(",").toList))
    val l = Organizador.readFromFile(ficheiroInventario.toString)
    helper(l)
    def helper(lista: List[String]) {
      lista match {
        case Nil => Organizador.writeToFile(ficheiroInventario, List(Login.user + "," + input), "\n", false)
        case x :: Nil => if (x.split(",")(0).equals(Login.user)) {
          val novaLista = l.updated(l.indexOf(x), x.concat("," + input))
          Organizador.writeToFile(ficheiroInventario, novaLista, "\n", false)
        } else {
          Organizador.writeToFile(ficheiroInventario, List(Login.user + "," + input), "\n", true)
        }
        case x :: t => if (x.split(",")(0).equals(Login.user)) {
          val novaLista = l.updated(l.indexOf(x), x.concat("," + input))
          Organizador.writeToFile(ficheiroInventario, novaLista, "\n", false)
        } else {
          helper(t)
        }
      }
    }
  }


  def adicionaLPInventario (): Unit ={
    val listaLP: List[String] = Sistema.Organizador.readFromFile(Sistema.ListaPessoal.ficheiroLPs.toString)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => println("A lista esta vazia. Nao ha produtos a adicionar")
        case x :: Nil => if(x.split(",")(0).equals(Login.user)){
          adicionarInventario(x.substring(Login.user.length+1))
          val novaLista = listaLP.updated(listaLP.indexOf(x), Login.user)
          Organizador.writeToFile(Sistema.ListaPessoal.ficheiroLPs, novaLista, "\n", false)
        }else{
          println("ERRO: A lista de compras esta vazia")
        }
        case x :: t => if(x.split(",")(0).equals(Login.user)){
          adicionarInventario(x.substring(Login.user.length+1))
          val novaLista = listaLP.updated(listaLP.indexOf(x), Login.user)
          Organizador.writeToFile(Sistema.ListaPessoal.ficheiroLPs, novaLista, "\n", false)
        }
        else helper(t)
      }
    }
    helper(listaLP)
  }


}