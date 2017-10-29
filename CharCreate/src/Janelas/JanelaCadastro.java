package Janelas;


import BancoDeDados.Conexao;
import Componentes.MostarMensagem;
import Entidades.Conta;
import javax.persistence.EntityManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JanelaCadastro extends Stage{

	//=====================> DEFININDO <=====================//

	private AnchorPane painel;

	private ImageView imgCadastro;

	private Label lblLogin;
	private Label lblSenha;
	private Label lblSenhaConfirmar;

	private TextField txtLogin;
	private PasswordField txtSenha;
	private PasswordField txtSenhaConfirmar;

	private Button btnCadastrar;

	private HBox hbLogin;
	private HBox hbSenha;
	private HBox hbSenhaConfirmar;

	private VBox vbPainel;
	private VBox vbGeral;

	private EntityManager gerenciador;

	private MostarMensagem messageBox;


	//======================================================//

	public JanelaCadastro() {
		//=====================> INSTANCIANDO <=====================//

		this.painel = new AnchorPane();

		this.imgCadastro = new ImageView(new Image("Imagens/cadastroimg.png"));

		this.lblLogin = new Label("Login: ");
		this.lblSenha = new Label("Senha: ");
		this.lblSenhaConfirmar = new Label("Confirme a senha: ");

		this.txtLogin = new TextField();
		this.txtSenha = new PasswordField();
		this.txtSenhaConfirmar = new PasswordField();

		this.btnCadastrar = new Button("Cadastrar");

		this.hbLogin = new HBox(85);
		this.hbSenha = new HBox(85);
		this.hbSenhaConfirmar = new HBox();

		this.vbPainel = new VBox(5);
		this.vbGeral = new VBox(5);

		this.gerenciador = Conexao.gerarGerenciador();

		this.messageBox = new MostarMensagem();


		//=========================================================//

		//=====================> UM POUCO DE DESIGN <=====================//

		this.hbLogin.setAlignment(Pos.CENTER);
		this.hbSenha.setAlignment(Pos.CENTER);
		this.hbSenhaConfirmar.setAlignment(Pos.CENTER);
		this.vbPainel.setAlignment(Pos.CENTER);
		this.vbGeral.setAlignment(Pos.CENTER);
		AnchorPane.setTopAnchor(this.vbGeral, 15d);
		AnchorPane.setLeftAnchor(this.vbGeral, 15d);
		AnchorPane.setRightAnchor(this.vbGeral, 15d);
		AnchorPane.setBottomAnchor(this.vbGeral, 15d);
		this.painel.setId("pane2");
		this.vbPainel.setPadding(new Insets(10, 10, 10, 10));
		this.vbPainel.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 15, 0, 0, 0); -fx-background-color: #804800; -fx-background-radius: 20px;");
		this.lblLogin.setId("texto");
		this.lblSenha.setId("texto");
		this.lblSenhaConfirmar.setId("texto");

		//===============================================================//

		//=====================> ADICIONANDO COMPONENTES NOS V/HBOXES <=====================//

		this.hbLogin.getChildren().addAll(this.lblLogin, this.txtLogin);
		this.hbSenha.getChildren().addAll(this.lblSenha, this.txtSenha);
		this.hbSenhaConfirmar.getChildren().addAll(this.lblSenhaConfirmar, this.txtSenhaConfirmar);
		this.vbPainel.getChildren().addAll(this.hbLogin, this.hbSenha, this.hbSenhaConfirmar, this.btnCadastrar);
		this.vbGeral.getChildren().addAll(this.imgCadastro, this.vbPainel);
		this.painel.getChildren().add(this.vbGeral);

		//==============================================================================//

		//=====================> ADICIONANDO AÇÃO AO BOTÃO <=====================//

		this.btnCadastrar.setOnMouseClicked(clique -> {
			if(this.txtLogin.getText().equals("")){
				this.messageBox.Alert("Login invalido", "Preencha um login.");
			}else if(this.txtLogin.getText().length() < 4){
				this.messageBox.Alert("Login invalido", "Preencha um login maior que 4 caracteres.");
			}else if(this.txtSenha.getText().equals("")){
				this.messageBox.Alert("Senha invalida", "Preencha uma senha.");
			}else if(this.txtSenha.getText().length() < 6){
				this.messageBox.Alert("Senha invalida", "Preencha uma senha maior que 6 caracteres.");
			}else if(!this.txtSenhaConfirmar.getText().equals(this.txtSenha.getText())){
				this.messageBox.Alert("Senha invalida", "Senhas digitadas diferentes.");
			}else{
				Conta c = new Conta();
				c.setNome_usuario(this.txtLogin.getText());
				c.setSenha_usuario(this.txtSenhaConfirmar.getText());
				this.gerenciador = Conexao.gerarGerenciador();
				this.gerenciador.getTransaction().begin();
				this.gerenciador.persist(c);
				this.gerenciador.getTransaction().commit();
				this.gerenciador.clear();
				this.gerenciador.close();
				this.messageBox.Info("Sucesso!", "Cadastrado com Sucesso!");
				this.close();
			}
		});

		//=========================================================================//

		//=====================> JANELA <=====================//

		Scene cena = new Scene(painel);
		cena.getStylesheets().addAll("css/style.css");
		this.setScene(cena);
		this.setTitle("Cadastro");
		this.getIcons().add(new Image("Imagens/icon.png"));
		this.show();

		//==================================================//


	}

}
