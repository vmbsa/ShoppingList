package Sistema
import java.io._

import Utilizador.Menu

class Organizador()

object Organizador {

  def writeToFile(file: File, itens: List[String], separador: String, append: Boolean): Unit = {
    val pw = new PrintWriter(new BufferedWriter(new FileWriter(file, append)))
    def helperFunction(arg: List[String]): Unit = {
      arg match {
        case Nil => pw.write("\n")
        case x :: Nil => pw.write(x + "\n")
        case x :: t => pw.write(x + separador)
          helperFunction(t)
      }
    }
    helperFunction(itens)
    pw.close()
  }

  def readFromFile(file: String): List[String] = {
    val bufferedSource = io.Source.fromFile(file)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

  def printString(s:String): Unit ={
    println(s)
  }
  def printStr(s:String): Unit ={
    print(s)
  }


}