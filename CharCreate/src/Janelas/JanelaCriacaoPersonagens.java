package Janelas;

import Entidades.Conta;
import BancoDeDados.Conexao;
import Entidades.Personagem;

import java.util.List;

import javax.persistence.EntityManager;

import Componentes.MostarMensagem;
import Componentes.VisualizadorDeClasse;
import Componentes.VisualizadorDePersonagens;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JanelaCriacaoPersonagens extends Stage{

	//=====================> DEFININDO <=====================//
	private int CabeloSelecionado = 1;
	private int CorCabeloSelecionado = 1;
	private int PeleSelecionada = 9;
	private int ClasseSelecionada = 12;

	private Pane painel;

	private TextField txtNomePersonagem;

	private Label lblNomePersonagem;
	private Label lblCabelo;
	private Label lblCorCabelo;
	private Label lblPele;
	private Label lblClasse;

	private Button btnMaisCabelo;
	private Button btnMenosCabelo;
	private Button btnMaisCorCabelo;
	private Button btnMenosCorCabelo;
	private Button btnMaisPele;
	private Button btnMenosPele;
	private Button btnMaisClasse;
	private Button btnMenosClasse;
	private Button btnSalvar;
	private Button btnVoltar;

	private HBox hbCentral;
	private HBox hbLblBtnCabelo;
	private HBox hbLblBtnCorCabelo;
	private HBox hbLblBtnPele;
	private HBox hbLblBtnClasse;
	private HBox hbBotoesBaixo;

	private VBox vbBtnMais;
	private VBox vbCentral;
	private VBox vbBtnMenos;

	private VisualizadorDePersonagens personagem;

	private VisualizadorDeClasse VisualizadorClasse;

	private MostarMensagem messagebox;

	private ObservableList<Personagem> listaDePersonagem;

	//============================================================//

	public JanelaCriacaoPersonagens(ObservableList<Personagem> listaDePersonagem, int contaLogada) {

		//=====================> INSTANCIANDO <=====================//
		this.listaDePersonagem = listaDePersonagem;

		this.personagem = new VisualizadorDePersonagens();

		this.VisualizadorClasse = new VisualizadorDeClasse();

		this.messagebox = new MostarMensagem();

		this.painel = new Pane();

		this.txtNomePersonagem = new TextField();

		this.lblNomePersonagem = new Label("Nome do Personagem: ");
		this.lblCabelo = new Label("Cabelo: ");
		this.lblCorCabelo = new Label("Cor do Cabelo: ");
		this.lblPele = new Label("Pele: ");
		this.lblClasse = new Label("Classe: ");

		this.btnMaisCabelo = new Button("   ");
		this.btnMenosCabelo = new Button("   ");
		this.btnMaisCorCabelo = new Button("   ");
		this.btnMenosCorCabelo = new Button("   ");
		this.btnMaisPele = new Button("   ");
		this.btnMenosPele = new Button("   ");
		this.btnMaisClasse = new Button("   ");
		this.btnMenosClasse = new Button("   ");
		this.btnSalvar = new Button("Salvar");
		this.btnVoltar = new Button("Voltar");

		this.hbCentral = new HBox();
		this.hbLblBtnCabelo = new HBox();
		this.hbLblBtnCorCabelo = new HBox();
		this.hbLblBtnPele = new HBox();
		this.hbLblBtnClasse = new HBox();
		this.hbBotoesBaixo = new HBox(3);

		this.vbCentral = new VBox(5);
		this.vbBtnMais = new VBox(20);
		this.vbBtnMenos = new VBox(20);

		//==========================================================//

		//=====================> UM "POUCO" DE DESIGN <=====================//
		VBox.setMargin(this.btnMaisCabelo, new Insets(65,0,0,0));
		HBox.setMargin(this.vbBtnMenos, new Insets(65,0,0,0));
		this.painel.setPadding(new Insets(0, 10, 10, 10));
		this.hbLblBtnCabelo.setAlignment(Pos.CENTER_RIGHT);
		this.hbLblBtnCorCabelo.setAlignment(Pos.CENTER_RIGHT);
		this.hbLblBtnPele.setAlignment(Pos.CENTER_RIGHT);
		this.hbLblBtnClasse.setAlignment(Pos.CENTER_RIGHT);
		this.hbBotoesBaixo.setAlignment(Pos.CENTER);
		this.hbCentral.setAlignment(Pos.CENTER);
		this.vbCentral.setAlignment(Pos.CENTER);
		this.btnMaisCabelo.setId("mais");
		this.btnMaisCorCabelo.setId("mais");
		this.btnMaisPele.setId("mais");
		this.btnMaisClasse.setId("mais");
		this.btnMenosCabelo.setId("menos");
		this.btnMenosCorCabelo.setId("menos");
		this.btnMenosPele.setId("menos");
		this.btnMenosClasse.setId("menos");
		this.lblCabelo.setId("texto");
		this.lblCorCabelo.setId("texto");
		this.lblPele.setId("texto");
		this.lblClasse.setId("texto");
		this.lblNomePersonagem.setId("texto");
		//=================================================================//

		//=====================> SETANDO COMPONENTES NAS V/HBOXES <=====================//
		this.hbBotoesBaixo.getChildren().addAll(this.btnSalvar, this.btnVoltar);
		this.hbLblBtnCabelo.getChildren().addAll(this.lblCabelo,this.btnMenosCabelo);
		this.hbLblBtnCorCabelo.getChildren().addAll(this.lblCorCabelo,this.btnMenosCorCabelo);
		this.hbLblBtnPele.getChildren().addAll(this.lblPele,this.btnMenosPele);
		this.hbLblBtnClasse.getChildren().addAll(this.lblClasse,this.btnMenosClasse);
		this.vbBtnMais.getChildren().addAll(this.btnMaisCabelo,this.btnMaisCorCabelo, this.btnMaisPele, this.btnMaisClasse);
		this.vbBtnMenos.getChildren().addAll(this.hbLblBtnCabelo,this.hbLblBtnCorCabelo, this.hbLblBtnPele, this.hbLblBtnClasse);
		this.vbCentral.getChildren().addAll(this.lblNomePersonagem ,this.txtNomePersonagem, this.personagem,this.VisualizadorClasse ,this.hbBotoesBaixo);
		this.hbCentral.getChildren().addAll(this.vbBtnMenos, this.vbCentral, this.vbBtnMais);

		this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		this.VisualizadorClasse.setText(ChecaClasse());

		this.painel.getChildren().add(hbCentral);
		//==============================================================================//


		//=====================> BOTÕES DE MUDAR A APARENCIA <=====================//
		this.btnMaisCabelo.setOnMouseClicked(clique -> {
			this.CabeloSelecionado++;
			if (this.CabeloSelecionado > 4) {
				this.CabeloSelecionado = 1;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});
		this.btnMenosCabelo.setOnMouseClicked(clique -> {
			this.CabeloSelecionado--;
			if (this.CabeloSelecionado < 1) {
				this.CabeloSelecionado = 4;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});

		this.btnMaisCorCabelo.setOnMouseClicked(clique -> {
			this.CorCabeloSelecionado++;
			if (this.CorCabeloSelecionado > 6) {
				this.CabeloSelecionado = 1;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});
		this.btnMenosCorCabelo.setOnMouseClicked(clique -> {
			this.CorCabeloSelecionado--;
			if (this.CorCabeloSelecionado < 1) {
				this.CorCabeloSelecionado = 6;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});

		this.btnMaisPele.setOnMouseClicked(clique -> {
			this.PeleSelecionada++;
			if (this.PeleSelecionada > 11) {
				this.PeleSelecionada = 9;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});
		this.btnMenosPele.setOnMouseClicked(clique -> {
			this.PeleSelecionada--;
			if (this.PeleSelecionada < 9) {
				this.PeleSelecionada = 11;
			}
			this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
		});

		this.btnMaisClasse.setOnMouseClicked(clique -> {
			if (this.txtNomePersonagem.getText().equals("Furlan")) {
				this.personagem.setDesignFurlan();
				this.DesabilitarTudo();
				this.VisualizadorClasse.setText("Vegano");
			}else{
				this.ClasseSelecionada++;
				if (this.ClasseSelecionada > 17) {
					this.ClasseSelecionada = 12;
				}
				this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
				this.VisualizadorClasse.setText(ChecaClasse());
			}
		});
		this.btnMenosClasse.setOnMouseClicked(clique -> {
			if (this.txtNomePersonagem.getText().equals("Furlan")) {
				this.personagem.setDesignFurlan();
				this.DesabilitarTudo();
				this.VisualizadorClasse.setText("Vegano");
			}
			else {
				this.ClasseSelecionada--;
				if (this.ClasseSelecionada < 12) {
					this.ClasseSelecionada = 17;
				}
				this.personagem.setDesign(this.PeleSelecionada, this.CabeloSelecionado, this.CorCabeloSelecionado, this.ClasseSelecionada);
				this.VisualizadorClasse.setText(ChecaClasse());
			}
		});
		//==========================================================================//

		//=====================> BOTÕES SALVAR/VOLTAR <=====================//

		this.btnSalvar.setOnMouseClicked(clique -> {
			EntityManager gerenciador = Conexao.gerarGerenciador();

			int nomeusado = 0;

			List<Personagem> Retornos = gerenciador.createQuery("Select a From Personagem a Where nome_char = '"+this.txtNomePersonagem.getText()+"'", Personagem.class).getResultList();
			for (Personagem i : Retornos) {
				i.getCod_char();
				nomeusado++;
			}

			if(this.txtNomePersonagem.getText().equals("")){
				this.messagebox.Alert("Preencha um Nome", "Nome do personagem não pode ser vazio.");
			}else if(this.txtNomePersonagem.getText().length() < 4){
				this.messagebox.Alert("Nome muito pequeno", "Nome não pode ser menor que 4 caracteres.");
			}else if(this.txtNomePersonagem.getText().length() > 12){
				this.messagebox.Alert("Nome muito grande", "Nome não pode ser maior que 12 caracteres.");
			}else{
				if(nomeusado == 0){
					if(this.txtNomePersonagem.getText().equals("Furlan")){
						this.messagebox.Furlan();
						Personagem p = new Personagem();
						p.setNome_char(this.txtNomePersonagem.getText());
						p.setNum_cabelo(5);
						p.setNum_corcabelo(1);
						p.setNum_pele(8);
						p.setNum_classe(31);
						p.setClasse(this.VisualizadorClasse.getText());
						List<Conta> RetornosConta = gerenciador.createQuery("Select a From Conta a Where cod_conta ="+contaLogada, Conta.class).getResultList();
						for (Conta i : RetornosConta) {
							p.setConta(i);
						}
						this.listaDePersonagem.add(p);
						this.close();
					}else{
						Personagem p = new Personagem();
						p.setNome_char(this.txtNomePersonagem.getText());
						p.setNum_cabelo(this.CabeloSelecionado);
						p.setNum_corcabelo(this.CorCabeloSelecionado);
						p.setNum_pele(this.PeleSelecionada);
						p.setNum_classe(this.ClasseSelecionada);
						p.setClasse(this.VisualizadorClasse.getText());
						List<Conta> RetornosConta = gerenciador.createQuery("Select a From Conta a Where cod_conta ="+contaLogada, Conta.class).getResultList();
						for (Conta i : RetornosConta) {
							p.setConta(i);
						}
						this.listaDePersonagem.add(p);
						this.close();
						this.messagebox.Info("Sucesso", "Cadastrado com Sucesso!");
					}
				}else{
					this.messagebox.Alert("Nome indisponivel", "Já existe um personagem com esse nome!");
				}
			}

		});

		this.btnVoltar.setOnMouseClicked(clique -> {
			this.close();
		});


		//==================================================================//

		//=====================> CENA E JANELA <=====================//
		Scene cena = new Scene(painel);
		painel.setId("pane");
		cena.getStylesheets().addAll("css/style.css");
		this.setScene(cena);
		this.initStyle(StageStyle.UNDECORATED);
		this.centerOnScreen();
		this.setResizable(false);
		this.show();
		//=========================================================//

	}

	//=====================> METODOS <=====================//
	private void DesabilitarTudo(){
		this.txtNomePersonagem.setEditable(false);
		this.btnMaisCabelo.setDisable(true);
		this.btnMenosCabelo.setDisable(true);
		this.btnMaisCorCabelo.setDisable(true);
		this.btnMenosCorCabelo.setDisable(true);
		this.btnMaisPele.setDisable(true);
		this.btnMenosPele.setDisable(true);
		this.btnMaisClasse.setDisable(true);
		this.btnMenosClasse.setDisable(true);
	}

	private String ChecaClasse(){
		switch (ClasseSelecionada) {
		case 12:
			return "Guerreiro";
		case 13:
			return "Arqueiro";
		case 14:
			return "Assassino";
		case 15:
			return "Cavaleiro Negro";
		case 16:
			return "Mago";
		case 17:
			return "Bruxo";

		default:
			return "";
		}
	}
	//=====================================================//
}
