package Utilizador

import java.io.File

import Sistema.Organizador
import Utilizador.Menu.printLogin

class Login()

object Login {
  val ficheiroLogin = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Melhoria codigo parte 1/ProjetoPPM/src/Base de Dados/Login.txt")
  var user: String = ""
  var pass: String = ""
  var idade = 0

  def tentarLogin(user: String, pass: String) {
    val listaLogins: List[String] = Sistema.Organizador.readFromFile(ficheiroLogin.toString)
    helper(listaLogins)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => Organizador.printString("Nao ha contas registadas na base de dados")   //nao ha contas na BD
        case x :: Nil => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user) && linha(1).equals(pass)) {
            idade = linha(2).toInt
            Organizador.printString("Login efetuado com sucesso | Bom dia " + user + ". O que vamos fazer hoje?")
            Menu.iniciarMenu()
            return
          }
          if(linha(0).equals(user) && !linha(1).equals(pass)) {
            Organizador.printString("Palavra passe errada. tenta novamente")
            Menu.iniciarLogin(printLogin)
          }
          else {
            Organizador.printString("Esta conta nao existe. Por favor crie uma conta ou faca login numa ja existente")
            Menu.iniciarLogin(printLogin)
          }

        case x :: t => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user) && linha(1).equals(pass)) {
            idade = linha(2).toInt
            Organizador.printString("Login efetuado com sucesso | Bom dia " + user + ". O que vamos fazer hoje?")
            Menu.iniciarMenu()
            return
          }
          if(linha(0).equals(user) && !linha(1).equals(pass)) {
            Organizador.printString("Palavra passe errada. tenta novamente")
            Menu.iniciarLogin(printLogin)
          }
          else helper(t)
      }
    }
  }

  def criarConta(): Unit = {
    Organizador.printStr("Nome de utilizador: ")
    user = scala.io.StdIn.readLine()
    Organizador.printStr("Password: ")
    pass = scala.io.StdIn.readLine()
    Organizador.printStr("Idade: ")
    idade = scala.io.StdIn.readInt()
    val listaLogins : List[String]  = Sistema.Organizador.readFromFile(ficheiroLogin.toString)
    helper(listaLogins)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => Sistema.Organizador.writeToFile(ficheiroLogin, List(user,pass,idade.toString), ",", true)
        case x :: Nil => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user)) {
            Organizador.printString("ERRO: Ja existe um utilizador com esse nome. Tente novamente")
            criarConta()
          }
          else Sistema.Organizador.writeToFile(ficheiroLogin, List(user,pass,idade.toString), ",", true)
        case x :: t => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user)) {
            Organizador.printString("ERRO: Ja existe um utilizador com esse nome. Tente novamente")
            criarConta()
          }
          else helper(t)
      }
    }
    Organizador.printString("Conta criada com sucesso!")
    Menu.iniciarLogin(printLogin)
  }


}