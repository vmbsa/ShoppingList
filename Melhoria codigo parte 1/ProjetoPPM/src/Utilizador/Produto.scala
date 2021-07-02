package Utilizador
import java.io.File

import Sistema.Organizador

import scala.annotation.tailrec

case class  Produto(id: Int, nome: String, categoria: String, marca: String, preco: Int, plastico: Int)

object Produto {

  val ficheiroProdutos = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Melhoria codigo parte 1/ProjetoPPM/src/Base de Dados/ListaProdutos.txt")
  var listaProdutosGeral: List[String] = Organizador.readFromFile(ficheiroProdutos.toString)
  var id = 1
  var plastico = 0



  def adicionarProdutoBD() {
      numeId(Organizador.readFromFile(ficheiroProdutos.toString))
      Organizador.printString("\n ******** Adicionar produto ********")
      Organizador.printStr("Nome do produto: ")
      val nome = scala.io.StdIn.readLine()
      Organizador.printStr("Categoria do produto(Bebida, Comida, Higiene, Limpeza):")
      var categoria = scala.io.StdIn.readLine()

      if (categoria.toLowerCase().equals("bebida")) {
        Organizador.printStr("Contem alcool? Sim(s)/Nao(n): ")
        if (scala.io.StdIn.readLine.equals("s")) {
          if (Login.idade < 18) {
            Organizador.printString("É proibida a venda de bebidas alcoolicas a menores de idade");
            return
          }
          else categoria = "bebida alcoolica"
        }
      }
    Sistema.Categorias.verificarCategoria(categoria, nome)
    try {
      Organizador.printStr("Marca do produto: ")

      val marca = scala.io.StdIn.readLine()
      Organizador.printStr("Preco do produto: ")
      val preco = scala.io.StdIn.readInt()
      Organizador.printStr("Quantidade de Plastico: ")
      val plastico = scala.io.StdIn.readInt()

      val prod = new Produto(id, nome, categoria, marca, preco, plastico)

      Organizador.writeToFile(ficheiroProdutos, List(id.toString, nome, categoria, marca, preco.toString, plastico.toString), ",", true)
      id += 1
    }catch{case _: Throwable => Organizador.printString("Erro a criar produto")}
      Organizador.printString("Adicionar mais produtos? (Sim = 1 / Nao = 2) ")


      val ans = scala.io.StdIn.readLine()
      ans match {
        case "1" =>adicionarProdutoBD()
        case "2" =>
        case _ => Organizador.printString("Opçao invalida, a voltar para o menu")
        }

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
    Organizador.printString("\n ******** BD de produtos ********\n" +
      "[ID produto, nome, categoria, marca, preco, percent. plastico]")
    val bd: List[String] = Organizador.readFromFile(ficheiroProdutos.toString)
    @tailrec
    def helper(arg: List[String]) {
      arg match {
        case Nil => Organizador.printString("Nao ha produtos na base de dados")
        case x :: Nil => Organizador.printString(x)
        case x :: t => Organizador.printString(x)
          helper(t)
      }
    }
    helper(bd)
    Organizador.printString("*********************************\n\n")
  }


  def getNome(id: Int): String = {
    var nome= ""
    @tailrec
    def help(list: List[String]) {
      list match {
        case Nil => Organizador.printString("Nao ha ")
        case x :: Nil => if (x.split(",")(0).equals(id.toString)) nome = x.split(",")(1)
        case x :: t => if (x.split(",")(0).equals(id.toString)) nome = x.split(",")(1)
        else help(t)
      }
    }
    help(listaProdutosGeral)
    nome
  }

  def getPlastico(id:Int): Int ={
    var plastico = 0
    @tailrec
    def help(list: List[String]): Unit ={
      list match {
        case Nil => Organizador.printString("Nao ha ")
        case x::Nil =>if (x.split(",")(0).equals(id.toString)) plastico = x.split(",")(5).toInt
        case x :: t => if (x.split(",")(0).equals(id.toString)) plastico = x.split(",")(5).toInt
        else help(t)
      }
    }
    help(listaProdutosGeral)
    plastico
  }

}
