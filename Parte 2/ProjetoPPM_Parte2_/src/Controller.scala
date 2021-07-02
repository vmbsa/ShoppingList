import Sistema.Organizador
import javafx.fxml.FXML
import javafx.scene.control.{Button, Label, PasswordField, Slider, TextArea, TextField}
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.paint.Color
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import java.time.LocalDateTime


class Controller extends Application {

  @FXML
  private var username: TextField = _
  @FXML
  private var maskLogin: TextField = _
  @FXML
  private var password: PasswordField = _
  @FXML
  private var ErroLogin: Label = _
  @FXML
  private var loginButton: Button = _
  @FXML
  private var userLabel: Label = _
  @FXML
  private var timeDate: Label = _
  @FXML
  private var things: TextArea = _
  @FXML
  private var labelTextArea: Label = _
  @FXML
  private var plastic: Slider = _
  @FXML
  private var sliderLabel: Label = _
  @FXML
  private var solucionLabel: Label = _
  @FXML
  private var idProduto: TextField = _
  @FXML
  private var marcaProduto: TextField = _
  @FXML
  private var precoProduto: TextField = _
  @FXML
  private var categoriaProdutos: TextField = _
  @FXML
  private var registerProductName: Button = _
  @FXML
  private var leaveButton: Button = _
  @FXML
  private var usernameRegister: TextField = _
  @FXML
  private var passwordRegister: PasswordField = _
  @FXML
  private var age: Slider = _
  @FXML
  private var registerButtonRegister: Button = _
  @FXML
  private var erroRegister: Label = _
  @FXML
  private var ageLabel: Label = _
  @FXML
  private var maskRegister: TextField = _
  @FXML
  private var RegisterButton: Button = _



  def ClickCheckBox(): Unit = {
    if (!maskLogin.isVisible) {
      maskLogin.setText(password.getText())
      maskLogin.setVisible(true)
      password.setVisible(false)
    } else {
      password.setText(maskLogin.getText())
      maskLogin.setVisible(false)
      password.setVisible(true)
    }

  }

  def onLoginClicked() {
    Utilizador.Login.tentarLogin(username.getText, password.getText) match {
      case 0 => ErroLogin.setTextFill(Color.GREEN); ErroLogin.setText("Login com sucesso"); loginButton.getScene.getWindow.hide(); CreateMenuWindows()

      case 1 => ErroLogin.setTextFill(Color.RED); ErroLogin.setText("Erro no Login, tente outra vez") // Erro tentar novamente
    }
  }

  def onRegisterClicked(): Unit = {
    RegisterButton.getScene.getWindow.hide()
    CreateRegisterWindows()
  }

  def CreateRegisterWindows() {
    val FxmLLoaderRegisto = new FXMLLoader(getClass.getResource("Register.fxml"))
    val mainViewRoot: Parent = FxmLLoaderRegisto.load()
    val scene = new Scene(mainViewRoot)
    val registerStage: Stage = new Stage()

    registerStage.setTitle("Register")
    registerStage.setScene(scene)
    registerStage.show()

  }

  def CreateMenuWindows() {
    val FxmLLoaderMenu = new FXMLLoader(getClass.getResource("Menu.fxml"))
    val mainViewRoot: Parent = FxmLLoaderMenu.load()
    val scene = new Scene(mainViewRoot)
    val menuStage: Stage = new Stage()

    menuStage.setTitle("Menu")
    menuStage.setScene(scene)
    menuStage.show()
  }

  def CreateLoginWindows() {
    val FxmLLoaderLogin = new FXMLLoader(getClass.getResource("Login.fxml"))
    val mainViewRoot: Parent = FxmLLoaderLogin.load()
    val scene = new Scene(mainViewRoot)
    val loginStage: Stage = new Stage()
    loginStage.setTitle("Login")
    loginStage.setScene(scene)
    loginStage.show()


  }

  //Registo

  def ClickCheckBoxRegister(): Unit = {
    if (!maskRegister.isVisible) {
      maskRegister.setText(passwordRegister.getText())
      maskRegister.setVisible(true)
      passwordRegister.setVisible(false)
    } else {
      passwordRegister.setText(maskRegister.getText())
      maskRegister.setVisible(false)
      passwordRegister.setVisible(true)
    }

  }
  def onSlider(): Unit = {

    ageLabel.setText(age.getValue.toInt.toString)
  }


  def onRegistedClickedRegister() {
    Utilizador.Login.criarConta(usernameRegister.getText, passwordRegister.getText, age.getValue.toInt) match {
      case 0 => erroRegister.setTextFill(Color.GREEN); erroRegister.setText("Registado com sucesso"); registerButtonRegister.getScene().getWindow.hide(); CreateLoginWindows()

      case 1 => erroRegister.setTextFill(Color.RED); erroRegister.setText("Erro no Registo, tente outra vez") // Erro tentar novamente

    }
  }

  //Comeca o menu


  def onclicked() {
    userLabel.setText("Bem vindo " + Utilizador.Login.user)
    timeDate.setText(LocalDateTime.now().getDayOfWeek.toString + ": " + LocalDateTime.now().getDayOfMonth + " OF " + LocalDateTime.now().getMonth + " OF " + LocalDateTime.now().getYear)

  }

  def onSliderMenu() {
    sliderLabel.setText(plastic.getValue.toInt.toString + "%")

  }

  def onMouseRealeased() {
    if (plastic.getValue.toInt < 15) {
      solucionLabel.setTextFill(Color.GREEN);
      solucionLabel.setText("Pouca percentagem de plástico, continue assim!")
    }
    else if (plastic.getValue.toInt < 30) {
      solucionLabel.setTextFill(Color.GREENYELLOW);
      solucionLabel.setText("Percentagem de plástico baixa")
    }
    else if (plastic.getValue.toInt < 45) {
      solucionLabel.setTextFill(Color.YELLOWGREEN);
      solucionLabel.setText("Percentagem de plástico media")
    }
    else if (plastic.getValue.toInt < 75) {
      solucionLabel.setTextFill(Color.YELLOW);
      solucionLabel.setText("Percentagem de plástico muito alta")
    }
    else {
      solucionLabel.setTextFill(Color.RED);
      solucionLabel.setText("Grande percentagem de plástico, reconcidere alguns produtos...")
    }
  }

  def onVerReceitas() {
    things.clear()
    things.setVisible(true)
    things.setText(Organizador.writeToString(Organizador.readFromFile(Utilizador.Receita.ficheiroReceita.toString), "\n"))
    labelTextArea.setText("Nome Recebita , Ingredientes")
  }

  def onVerBD() {
    things.clear()
    things.setVisible(true)
    things.setText(Organizador.writeToString(Organizador.readFromFile(Utilizador.Produto.ficheiroProdutos.toString), "\n"))
    labelTextArea.setText("ID Produto, Nome, Categoria, Marca, Preço, Plastico")

  }

  def onAdicionarProdutosBD() {
    val FxmLLoaderExtra = new FXMLLoader(getClass.getResource("AddProducts.fxml"))
    val mainViewRoot: Parent = FxmLLoaderExtra.load()
    val scene = new Scene(mainViewRoot)
    val extraStage: Stage = new Stage()

    extraStage.setTitle("Register Produtcs")
    extraStage.setScene(scene)
    extraStage.show()

  }

  def onClickedRegisterProductsButton() {
    Utilizador.Produto.adicionarProdutoBD(idProduto.getText(), categoriaProdutos.getText(), marcaProduto.getText(), precoProduto.getText().toInt, plastic.getValue.toInt)
    registerProductName.getScene().getWindow.hide()
  }

  def onVerQuantidadePlastico() {
    labelTextArea.setText("")
    things.clear()
    things.setVisible(true)
    things.setText("A sua quantidade de plastico é: " + Utilizador.Produto.funPlastico(Utilizador.Produto.listaProdutosGeral).toString + "%")
  }

  def onLeaveButton() {
    leaveButton.getScene.getWindow.hide()
  }

  def onLogoutClicked() {
    onLeaveButton()
    CreateLoginWindows()
  }


  override def start(primaryStage: Stage): Unit = {
    CreateLoginWindows()
  }

  object LoginController {

    def main(args: Array[String]): Unit = {
      Application.launch(classOf[Controller], args: _*)
    }

  }

}



