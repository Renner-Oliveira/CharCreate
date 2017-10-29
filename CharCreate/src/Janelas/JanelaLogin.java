package Janelas;


import Componentes.EscutadorDaLista;
import Janelas.JanelaSelecaoPersonagem;
import BancoDeDados.Conexao;
import Componentes.MostarMensagem;
import Entidades.Conta;
import Entidades.Personagem;



import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.Query;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class JanelaLogin extends Stage{

	//=====================> DEFININDO <=====================//

	private AnchorPane painel;

	private ImageView imgLogin;

	private Label lblLogin;
	private Label lblSenha;

	private TextField txtLogin;
	private PasswordField txtSenha;

	private Button btnLogar;
	private Button btnNovaConta;

	private HBox hbLogin;
	private HBox hbSenha;

	private VBox vbPainel;
	private VBox vbGeral;

	private int contaLogada;

	private EntityManager gerenciador;

	private MostarMensagem messageBox;

	private ObservableList<Personagem> listaDePersonagem;

	private JanelaSelecaoPersonagem janelaSelecaoPersonagem = null;

	private JanelaCadastro janelaCadastro = null;

	//======================================================//

	@SuppressWarnings("unchecked")
	public JanelaLogin() {
		//=====================> INSTANCIANDO <=====================//

		this.painel = new AnchorPane();

		this.imgLogin = new ImageView(new Image("Imagens/loginimg.png"));

		this.lblLogin = new Label("Login: ");
		this.lblSenha = new Label("Senha: ");

		this.txtLogin = new TextField();
		this.txtSenha = new PasswordField();

		this.btnLogar = new Button("Login");
		this.btnNovaConta = new Button("Cadastrar");

		this.hbLogin = new HBox(3);
		this.hbSenha = new HBox();

		this.vbPainel = new VBox(5);
		this.vbGeral = new VBox(15);

		this.gerenciador = Conexao.gerarGerenciador();

		this.messageBox = new MostarMensagem();


		//=========================================================//

		//=====================> UM POUCO DE DESIGN <=====================//

		this.hbLogin.setAlignment(Pos.CENTER);
		this.hbSenha.setAlignment(Pos.CENTER);
		this.vbGeral.setAlignment(Pos.CENTER);
		this.vbPainel.setAlignment(Pos.CENTER);
		this.lblLogin.setId("texto");
		this.lblSenha.setId("texto");
		AnchorPane.setTopAnchor(this.vbGeral, 15d);
		AnchorPane.setLeftAnchor(this.vbGeral, 15d);
		AnchorPane.setRightAnchor(this.vbGeral, 15d);
		AnchorPane.setBottomAnchor(this.vbGeral, 15d);
		this.vbPainel.setPadding(new Insets(10, 10, 10, 10));
		this.painel.setId("pane2");
		this.vbPainel.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 15, 0, 0, 0); -fx-background-color: #804800; -fx-background-radius: 20px;");


		//===============================================================//

		//=====================> ADICIONANDO COMPONENTES NOS V/HBOXES <=====================//

		this.hbLogin.getChildren().addAll(this.lblLogin, this.txtLogin);
		this.hbSenha.getChildren().addAll(this.lblSenha, this.txtSenha);
		this.vbPainel.getChildren().addAll(this.hbLogin, this.hbSenha,this.btnLogar , this.btnNovaConta);
		this.vbGeral.getChildren().addAll(this.imgLogin, this.vbPainel);
		this.painel.getChildren().add(this.vbGeral);

		//==============================================================================//

		this.btnLogar.setOnMouseClicked(clique -> {

			boolean contaexiste = false;
			List<Conta> Retornos = this.gerenciador.createQuery("Select a From Conta a Where nome_usuario = '"+this.txtLogin.getText()+"' and senha_usuario = '"+this.txtSenha.getText()+"'", Conta.class).getResultList();
			for (Conta i : Retornos) {
				contaexiste = true;
				this.contaLogada = i.getCod_conta();
			}
			if(contaexiste == true){
				this.close();

				if(janelaSelecaoPersonagem == null)
				{

					Query sql = this.gerenciador.createQuery("from Personagem where cod_conta ="+this.contaLogada);

					this.listaDePersonagem = FXCollections.observableArrayList();

					this.listaDePersonagem.addAll(sql.getResultList());

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					this.gerenciador.clear();
					this.gerenciador.close();

					this.listaDePersonagem.addListener(new EscutadorDaLista(this.listaDePersonagem));
					janelaSelecaoPersonagem = new JanelaSelecaoPersonagem(this.contaLogada, this.listaDePersonagem);
				}
				this.close();
				janelaSelecaoPersonagem.show();
			}else{
				this.messageBox.Alert("Conta Inexistente", "Login ou senha invalido(s)");
			}

		});

		this.btnNovaConta.setOnMouseClicked(clique -> {
			if(janelaCadastro == null){
				this.janelaCadastro = new JanelaCadastro();
			}
			this.janelaCadastro.show();
		});



		//=====================> JANELA <=====================//

		Scene cena = new Scene(painel);
		cena.getStylesheets().addAll("css/style.css");
		this.setScene(cena);
		this.setTitle("Login");
		this.getIcons().add(new Image("Imagens/icon.png"));
		this.show();

		//==================================================//


	}

}
