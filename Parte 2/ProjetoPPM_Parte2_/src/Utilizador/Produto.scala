package Utilizador

import java.io.File

import Sistema.{ListaPessoal, Organizador}

import scala.annotation.tailrec

case class Produto(var id: Int, var nome: String, var categoria: String, var marca: String, var preco: Int, var plastico: Int)

object Produto {

  val ficheiroProdutos = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/ListaProdutos.txt")
  var listaProdutosGeral: List[String] = Organizador.readFromFile(ficheiroProdutos.toString)
  var id = 1

  def adicionarProdutoBD(nome:String,categoria:String,marca:String,preco:Int,plastico:Int) {
    numeId(Organizador.readFromFile(ficheiroProdutos.toString))
    var prod = new Produto(id, nome, categoria, marca, preco, plastico)
    Organizador.writeToFile(ficheiroProdutos, List(id.toString, nome, categoria, marca, preco.toString, plastico.toString), ",", true)
    id += 1
  }



  @tailrec
  def numeId(arg: List[String]): Unit = {
    arg match {
      case Nil => id = 1
      case x :: Nil => id = x.split(",")(0).toInt + 1
      case x :: t => numeId(t)
    }
  }

  def verListaBD(list: List[String]) {
    println("\n ******** BD de produtos ********\n" +
      "[ID produto, nome, categoria, marca, preco, percent. plastico]")
    val bd: List[String] = Organizador.readFromFile(ficheiroProdutos.toString)
    @tailrec
    def helper(arg: List[String]) {
      arg match {
        case Nil => println("Nao ha produtos na base de dados")
        case x :: Nil => println(x)
        case x :: t => println(x)
          helper(t)
      }
    }
    helper(bd)
    print("*********************************\n\n")
  }

  def solucoesPlastico(): Unit = {
    if (funPlastico(listaProdutosGeral) < 15) println("Pouca percentagem de plástico, continue assim!")
    else if (funPlastico(listaProdutosGeral) < 30) println("Percentagem de plástico baixa")
    else if (funPlastico(listaProdutosGeral) < 45) println("Percentagem de plástico media")
    else if (funPlastico(listaProdutosGeral) < 75) println("Percentagem de plástico muito alta")
    else println("Grande percentagem de plástico, reconcidere alguns produtos...")

    //maybe meter produtos que sejam parecidos e tenham menos quantidade de plastico/
  }

  def funPlastico(list: List[String]): Int = {
    var plastico: Int =0
    help(listaProdutosGeral)
    def help(list: List[String]) {
      list match {
        case Nil => println("Nao ha produtos na base de dados")
        case x :: Nil => plastico += x.split(",")(5).toInt
        case x :: t => plastico += x.split(",")(5).toInt
          help(t)
      }
    }
    plastico / id
  }

  def getNome(id: Int): String = {
    var nome= ""
    def help(list: List[String]) {
      list match {
        case Nil => println("Nao ha ")
        case x :: Nil => if (x.split(",")(0).equals(id.toString)) nome = x.split(",")(1)
        case x :: t => if (x.split(",")(0).equals(id.toString)) nome = x.split(",")(1)
        else help(t)
      }
    }
    help(listaProdutosGeral)
    nome
  }

}
