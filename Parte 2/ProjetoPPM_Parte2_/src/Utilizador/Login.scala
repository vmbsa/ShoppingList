package Utilizador

import java.io.File
import scala.util.control.Breaks.break

class Login()

object Login {
  val ficheiroLogin = new File(s"${Menu.home}/IdeaProjects/ProjetoPt2PPM_grupo27_JoaoVeloso87305_AnaSantana82540_VascoSilva82688/Parte 2/ProjetoPPM_Parte2_/src/Base de Dados/Login.txt")
  var user: String = ""
  var pass: String = ""
  var idade = 0

  def tentarLogin(user: String, pass: String): Int={
    this.user=user
    val listaLogins: List[String] = Sistema.Organizador.readFromFile(ficheiroLogin.toString)
    var verificador: Boolean = false
    helper(listaLogins)
    def helper(arg: List[String]){
      arg match {
        case Nil =>
        case x :: Nil => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user) && linha(1).equals(pass)) {
            idade = linha(2).toInt
            verificador = true
             // login efutuado com sucesso
          }
        case x :: t => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user) && linha(1).equals(pass)) {
            idade = linha(2).toInt
            verificador=true

          }
          else helper(t)
      }
    }
    if(!verificador)
       return 1
    else
      return 0

  }

  def criarConta(user: String, pass: String, idade: Int): Int = {
    val listaLogins : List[String]  = Sistema.Organizador.readFromFile(ficheiroLogin.toString)
    var verificador: Boolean = false
    helper(listaLogins)
    def helper(arg: List[String]): Unit = {
      arg match {
        case Nil => Sistema.Organizador.writeToFile(ficheiroLogin, List(user,pass,idade.toString), ",", true)
        case x :: Nil => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user)) {
            //Ja existe um utilizador com esse nome. Tente novamente")
            verificador = true;
          }
          else Sistema.Organizador.writeToFile(ficheiroLogin, List(user,pass,idade.toString), ",", true)
        case x :: t => val linha: Array[String] = x.split(",")
          if(linha(0).equals(user)) {
            verificador = true;
            //Ja existe um utilizador com esse nome. Tente novamente")
          }
          else helper(t)
      }
    }
    if(verificador)
      return 1
    else
      return 0
    //criadocom sucesso
  }


}