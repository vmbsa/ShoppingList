package Utilizador

import Sistema.{ListaPessoal, Organizador}
import Utilizador.Login.{pass, user}
import Utilizador.Menu.printLogin
import Utilizador.Produto.{adicionarProdutoBD, listaProdutosGeral, verListaBD}

class Menu()

object Menu {
  val home = System.getProperty("user.home")


  def printLogin(i:Int):String={
    i match{
      case 1 => "== Bem vindo/a a nossa aplicacao =="
      case 2 => "1-Fazer login \n2-Criar conta \n3-Sair \n" +
                "Escolha uma opcao: "
    }
  }
  def iniciarLogin(f:Int=>String) {
    Organizador.printString(f(2))
    scala.io.StdIn.readInt() match {
      case 1 => Organizador.printStr("Nome de utilizador: ")
        user = scala.io.StdIn.readLine()
        Organizador.printStr("Password: ")
        pass = scala.io.StdIn.readLine()
        Login.tentarLogin(user, pass)
      case 2 => Login.criarConta()
      case 3 => Organizador.printString("|======== Tenha um bom dia ========|")
      case _ => Organizador.printStr("Insira um numero valido") ; iniciarLogin(printLogin)
    }
  }

  def printMenu() {
    Organizador.printStr("==========[MENU]==========\n " +
      "1-Adicionar produto a base de dados \n " +
      "2-Ver base de dados de produtos \n " +
      "3-Adicionar produto a minha lista de compras pessoal \n " +
      "4-Adicionar uma receita\n " +
      "5-Ver receitas\n " +
      "6-Ver quantidade de plastico\n " +
      "7-Adicionar produtos da receita para a lista de compras\n " +
      "8-Adicionar produtos ao inventario\n " +
      "9-Copiar a lista de um utilizador\n " +
      "10-Limpar lista de compras e adicionar ao inventario\n " +
      "11-Ver lista de compras pessoal\n " +
      "22-Fazer logout \n " +
      "23-Sair \n " +
      "========================== \n" +
      "Escolhe uma opcao: ")
  }
  def iniciarMenu() {
    Produto.numeId(listaProdutosGeral)
    printMenu()
    scala.io.StdIn.readInt() match {
      case 1 => adicionarProdutoBD() ; iniciarMenu()
      case 2 => Organizador.printString("1- Ver por categoria \n2- Ver tudo")
        scala.io.StdIn.readInt() match {
          case 1 => Organizador.printString("******** Lista categorizada ********")
            Sistema.Categorias.verListaCategoria(Sistema.Organizador.readFromFile(Sistema.Categorias.ficheiroCategorias.toString)) ; iniciarMenu()
            Organizador.printString("********************************")
          case 2 => verListaBD(listaProdutosGeral) ; iniciarMenu()
          case _ => Organizador.printString("Insira um numero valido")
        }
      case 3 => verListaBD(listaProdutosGeral)
        Organizador.printString("1- Adiconar um produto, existente na base de dados, a sua lista\n2- Adicionar novo produto")
        scala.io.StdIn.readInt() match {
          case 1 => Organizador.printString("Escolha o ID do produto"); ListaPessoal.adicionaProdutoLista(scala.io.StdIn.readInt().toString) ; iniciarMenu()
          case 2 => adicionarProdutoBD(); ListaPessoal.adicionaProdutoLista((Produto.id - 1).toString) ; iniciarMenu()
          case _ => Organizador.printString("Insira um numero valido")
        }
      case 4 => Receita.criarReceita() ; iniciarMenu()
      case 5 => Receita.verReceitas() ; iniciarMenu()
      case 6 => ListaPessoal.solucoesPlastico(); iniciarMenu()
      case 7 => Receita.verReceitas(); Organizador.printStr("Escolha o nome da receita: "); ListaPessoal.adicionaReceitaLP(scala.io.StdIn.readLine()) ; iniciarMenu()
      case 8 => Organizador.printString("Insira os produtos que tem no seu inventario separados por virgulas: "); Inventario.adicionarInventario(scala.io.StdIn.readLine()) ; iniciarMenu()
      case 9 => Organizador.printStr("Escolha o nome do user de quem quer copiar a lista: "); ListaPessoal.copiaLP(scala.io.StdIn.readLine()) ; iniciarMenu()
      case 10 => Utilizador.Inventario.adicionaLPInventario() ; iniciarMenu()
      case 11 => ListaPessoal.imprimeLP(Login.user) ; iniciarMenu()
      case 22 => Organizador.printString("Sessao terminada"); iniciarLogin(printLogin)
      case 23 => Organizador.printString("========Tenha um bom dia========")
      case _ => Organizador.printString("Insira um numero valido") ; iniciarMenu()
      }
    }

  def main(args: Array[String]): Unit = {
    Organizador.printString(printLogin(1))
    Menu.iniciarLogin(printLogin)
  }
}
