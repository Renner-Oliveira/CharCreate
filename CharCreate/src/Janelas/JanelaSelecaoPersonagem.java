package Janelas;

import Componentes.MostarMensagem;
import Componentes.VisualizadorDePersonagens;
import Entidades.Personagem;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JanelaSelecaoPersonagem extends Stage{

	//=====================> DEFININDO <=====================//

	private AnchorPane painel;

	private Button btnEntrar;
	private Button btnCriarPersonagem;
	private Button btnDeletarPersonagem;
	private Button btnVerPersonagens;
	private Button btnDeslogar;

	private static VisualizadorDePersonagens personagemPrimeiro = new VisualizadorDePersonagens();;
	private static VisualizadorDePersonagens personagemSegundo = new VisualizadorDePersonagens();;
	private static VisualizadorDePersonagens personagemTerceiro = new VisualizadorDePersonagens();;

	private HBox hbGeral;

	private VBox vbBtns;
	private VBox vbGeral;

	private ImageView imgPersonagens;

	private Personagem personagemLogado;

	private ObservableList<Personagem> listaDePersonagem;

	private MostarMensagem messageBox;

	private boolean personagem1Select, personagem2Select, personagem3Select = false;

	private JanelaLogin janelaLogin = null;

	//==========================================================//

	public JanelaSelecaoPersonagem(int contaLogada, ObservableList<Personagem> listaDePersonagem) {

		//=====================> INSTANCIANDO <=====================//

		this.listaDePersonagem = listaDePersonagem;

		this.painel = new AnchorPane();

		this.messageBox = new MostarMensagem();

		this.btnEntrar = new Button("Entrar");
		this.btnCriarPersonagem = new Button("Criar Personagem");
		this.btnDeletarPersonagem = new Button("Deletar Personagem");
		this.btnVerPersonagens = new Button("Ver Personagens");
		this.btnDeslogar = new Button("Deslogar");

		this.hbGeral = new HBox(3);

		this.vbBtns = new VBox(9);
		this.vbGeral = new VBox(5);

		this.imgPersonagens = new ImageView();

		//===========================================================//

		//=====================> UM POUCO DE DESIGN <=====================//

		this.imgPersonagens.setImage(new Image("Imagens/personagensimg.png"));
		vbBtns.setAlignment(Pos.CENTER);
		hbGeral.setAlignment(Pos.CENTER);
		vbGeral.setAlignment(Pos.CENTER);
		AnchorPane.setTopAnchor(vbGeral, 15d);
		AnchorPane.setLeftAnchor(vbGeral, 15d);
		AnchorPane.setRightAnchor(vbGeral, 15d);
		AnchorPane.setBottomAnchor(vbGeral, 15d);
		personagemPrimeiro.setBG("Imagens/bgpersonsnotcreate.png");
		personagemSegundo.setBG("Imagens/bgpersonsnotcreate.png");
		personagemTerceiro.setBG("Imagens/bgpersonsnotcreate.png");
		personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
		personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
		personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
		this.hbGeral.setPadding(new Insets(10, 10, 10, 10));
		this.hbGeral.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 15, 0, 0, 0); -fx-background-color: #804800; -fx-background-radius: 20px;");

		//==================================================================//

		//=====================> CARREGANDO PERSONAGENS <=====================//

		JanelaSelecaoPersonagem.CarregaPersonagens(this.listaDePersonagem);

		//===================================================================//

		//=====================> COLOCANDO COMPONENTES NAS V/HBOXES <=====================//

		this.vbBtns.getChildren().addAll(this.btnEntrar, this.btnCriarPersonagem, this.btnDeletarPersonagem, this.btnVerPersonagens, this.btnDeslogar);
		this.hbGeral.getChildren().addAll(personagemPrimeiro, personagemSegundo, personagemTerceiro, this.vbBtns);
		this.vbGeral.getChildren().addAll(this.imgPersonagens, this.hbGeral);
		this.painel.getChildren().add(vbGeral);

		//===============================================================================//

		//=====================> AÇÃO DOS BOTÕES <=====================//

		this.btnCriarPersonagem.setOnMouseClicked(clique -> {
			if(this.listaDePersonagem.size() == 3){
				messageBox.Alert("Limite de Personagens", "Você só pode ter 3 personagens por conta!");
			}else{
				JanelaCriacaoPersonagens janelaCriacaoPersonagens = null;
				if(janelaCriacaoPersonagens == null){
					janelaCriacaoPersonagens = new JanelaCriacaoPersonagens(this.listaDePersonagem, contaLogada);
				}
				janelaCriacaoPersonagens.show();
			}

		});

		personagemPrimeiro.setOnMouseClicked(clique -> {
			personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("red"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagem1Select = true;
			personagem2Select = false;
			personagem3Select = false;
			this.personagemLogado = listaDePersonagem.get(0);
		});

		personagemSegundo.setOnMouseClicked(clique -> {
			personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("red"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagem1Select = false;
			personagem2Select = true;
			personagem3Select = false;
			this.personagemLogado = listaDePersonagem.get(1);
		});

		personagemTerceiro.setOnMouseClicked(clique -> {
			personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("red"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
			personagem1Select = false;
			personagem2Select = false;
			personagem3Select = true;
			this.personagemLogado = listaDePersonagem.get(2);
		});

		this.btnEntrar.setOnAction(evento -> {
			if(personagemLogado != null){
				new Game(this.personagemLogado);
				this.close();
			}else{
				messageBox.Alert("Selecione um Personagem!", "Nunhum personagem selecionado");
			}
		});

		this.btnDeletarPersonagem.setOnMouseClicked(clique -> {
			if(personagem1Select){
				if(!personagemPrimeiro.getNomePersonagem().equals("")){
					if(this.messageBox.ConfirmarDelete(personagemPrimeiro.getNomePersonagem(), this.listaDePersonagem, 0)){
						personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagem1Select = false;
						personagem2Select = false;
						personagem3Select = false;
						this.personagemLogado = null;
					}
				}else{
					this.messageBox.Alert("Personagem invalido", "Selecione um Personagem.");
				}
			}
			else if(personagem2Select){
				if(!personagemSegundo.getNomePersonagem().equals("")){
					if(this.messageBox.ConfirmarDelete(personagemSegundo.getNomePersonagem(), this.listaDePersonagem, 1)){
						personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagem1Select = false;
						personagem2Select = false;
						personagem3Select = false;
						this.personagemLogado = null;
					}
				}else{
					this.messageBox.Alert("Personagem invalido", "Selecione um Personagem.");
				}
			}
			else if(personagem3Select){
				if(!personagemTerceiro.getNomePersonagem().equals("")){
					if(this.messageBox.ConfirmarDelete(personagemTerceiro.getNomePersonagem(), this.listaDePersonagem, 2)){
						personagemPrimeiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemSegundo.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagemTerceiro.setBorder(new Border(new BorderStroke(Color.web("#aa6000"), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(3))));
						personagem1Select = false;
						personagem2Select = false;
						personagem3Select = false;
						this.personagemLogado = null;
					}
				}else{this.messageBox.Alert("Personagem invalido", "Selecione um Personagem.");

				}
			}else{
				this.messageBox.Alert("Personagem invalido", "Selecione um Personagem.");
			}
		});

		this.btnVerPersonagens.setOnMouseClicked(clique -> {
			new JanelaVerPersonagens();
		});

		this.btnDeslogar.setOnMouseClicked(clique -> {
			this.close();
			if(this.janelaLogin == null){
				this.janelaLogin = new JanelaLogin();
			}
			this.janelaLogin.show();
		});

		//=============================================================//

		painel.setId("pane2");

		Scene cena = new Scene(painel);
		cena.getStylesheets().addAll("css/style.css");
		this.setScene(cena);
		this.setTitle("Personagens");
		this.getIcons().add(new Image("Imagens/icon.png"));
		this.show();
	}

	public static void CarregaPersonagens(ObservableList<Personagem> listaDePersonagens){
		int c = 0;
		System.out.println(listaDePersonagens.size());
		if(listaDePersonagens.size() > 0)
		{
			for(Personagem p : listaDePersonagens){
				if(c == 0){
					if(p.getNome_char().equals("Furlan")){
						personagemPrimeiro.setDesignFurlan();
						personagemPrimeiro.setBG("Imagens/bgpersons2.png");
						personagemPrimeiro.setNomePersonagem(p.getNome_char());
						personagemSegundo.clean();
						personagemTerceiro.clean();
					}else{
						personagemPrimeiro.setDesign(p.getNum_pele(), p.getNum_cabelo(), p.getNum_corcabelo(), p.getNum_classe());
						personagemPrimeiro.setBG("Imagens/bgpersons2.png");
						personagemPrimeiro.setNomePersonagem(p.getNome_char());
						personagemSegundo.clean();
						personagemTerceiro.clean();
					}
				}
				if(c == 1){
					if(p.getNome_char().equals("Furlan")){
						personagemSegundo.setDesignFurlan();
						personagemSegundo.setBG("Imagens/bgpersons2.png");
						personagemSegundo.setNomePersonagem(p.getNome_char());
						personagemTerceiro.clean();
					}else{
						personagemSegundo.setDesign(p.getNum_pele(), p.getNum_cabelo(), p.getNum_corcabelo(), p.getNum_classe());
						personagemSegundo.setBG("Imagens/bgpersons2.png");
						personagemSegundo.setNomePersonagem(p.getNome_char());
						personagemTerceiro.clean();
					}
				}
				if(c == 2){
					if(p.getNome_char().equals("Furlan")){
						personagemTerceiro.setDesignFurlan();
						personagemTerceiro.setNomePersonagem(p.getNome_char());
						personagemTerceiro.setBG("Imagens/bgpersons2.png");
					}else{
						personagemTerceiro.setDesign(p.getNum_pele(), p.getNum_cabelo(), p.getNum_corcabelo(), p.getNum_classe());
						personagemTerceiro.setNomePersonagem(p.getNome_char());
						personagemTerceiro.setBG("Imagens/bgpersons2.png");
					}
				}
				c++;
			}
		}else{
			personagemPrimeiro.clean();
			personagemSegundo.clean();
			personagemTerceiro.clean();
		}
	}

}
