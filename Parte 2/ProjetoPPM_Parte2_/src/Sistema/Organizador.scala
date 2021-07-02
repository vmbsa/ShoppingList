package Sistema
import java.io._

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

  def writeToString(itens: List[String], separador: String): String = {
    var helper: String =""
    var helper1: String =""
    def helperFunction(arg: List[String]): Unit = {
      arg match {
        case Nil => helper1 = helper.concat("\n")
        case x :: Nil => helper1 = helper.concat(x +"\n"); helper=helper1
        case x :: t => helper1 = helper.concat(x +separador); helper=helper1
          helperFunction(t)
      }
    }
    helperFunction(itens)
    helper1
  }

  def readFromFile(file: String): List[String] = {
    val bufferedSource = io.Source.fromFile(file)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

}