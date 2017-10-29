package Janelas;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import BancoDeDados.Conexao;
import Componentes.VisualizadorDePersonagens;
import Componentes.VisualizadorDePersonagensParaCelula;
import Entidades.Personagem;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JanelaVerPersonagens extends Stage{

	//=====================> DEFININDO <=====================//

	private TableView<Personagem> tblPersonagens;

	private TableColumn<Personagem, String> nome;
	private TableColumn<Personagem, String> nome_usuario;
	private TableColumn<Personagem, VisualizadorDePersonagens> foto;
	private TableColumn<Personagem, String> classe;

	private AnchorPane painel;

	private ObservableList<Personagem> listaDePersonagem;

	//=======================================================//

	@SuppressWarnings("unchecked")
	public JanelaVerPersonagens() {

		//=====================> INSTANCIANDO <=====================//

		this.tblPersonagens = new TableView<Personagem>();

		this.nome = new TableColumn<Personagem, String>("Nome do Personagem");
		this.nome_usuario = new TableColumn<Personagem, String>("Nome do Usuario");
		this.foto = new TableColumn<Personagem, VisualizadorDePersonagens>("Aparencia");
		this.classe = new TableColumn<Personagem, String>("Classe");

		this.painel = new AnchorPane();

		//========================================================//

		//=====================> UM POUCO DE DESIGN <=====================//

		this.tblPersonagens.setMinWidth(680);
		this.tblPersonagens.setMinHeight(600);
		this.tblPersonagens.getStylesheets().add("css/tabela.css");
		this.nome.setStyle("-fx-alignment: CENTER;");
		this.nome_usuario.setStyle("-fx-alignment: CENTER;");
		this.classe.setStyle("-fx-alignment: CENTER;");

		//================================================================//

		//=====================> VALORES DAS CELULAS <=====================//

		this.nome.setCellValueFactory(new PropertyValueFactory<Personagem, String>("nome_char"));
		this.nome_usuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Personagem,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Personagem, String> arg0) {
				// TODO Auto-generated method stub
				return new SimpleObjectProperty<String>(arg0.getValue().getConta().getNome_usuario());
			}
		});
		this.classe.setCellValueFactory(new PropertyValueFactory<Personagem, String>("classe"));


		this.foto.setCellFactory(new VisualizadorDePersonagensParaCelula());
		this.foto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Personagem, VisualizadorDePersonagens>, ObservableValue<VisualizadorDePersonagens>>() {

			@Override
			public ObservableValue<VisualizadorDePersonagens> call(CellDataFeatures<Personagem, VisualizadorDePersonagens> param) {
				// TODO Auto-generated method stub
				VisualizadorDePersonagens person = new VisualizadorDePersonagens();
				person.setBG("Imagens/bgpersons2.png");
				if(param.getValue().getNome_char().equals("Furlan")){
					person.setDesignFurlan();
					person.setADM();
				}else{
					person.setDesign(param.getValue().getNum_pele(),param.getValue().getNum_cabelo(),param.getValue().getNum_corcabelo(),param.getValue().getNum_classe());
				}
				return new SimpleObjectProperty<VisualizadorDePersonagens>(person);
			}
		});

		//======================================================================//

		//=====================> ADICIONANDO COLUNAS NA TABELA <=====================//

		this.tblPersonagens.getColumns().addAll(this.nome_usuario, this.nome, this.foto, this.classe);

		//=========================================================================//

		//=====================> SELECT <=====================//

		EntityManager gerenciador = Conexao.gerarGerenciador();

		Query sql = gerenciador.createQuery("from Personagem");

		this.listaDePersonagem = FXCollections.observableArrayList();

		this.listaDePersonagem.addAll(sql.getResultList());

		//====================================================//

		//=====================> DEFININDO FONTE DE DADOS <=====================//

		this.tblPersonagens.setItems(this.listaDePersonagem);

		//====================================================================//

		this.painel.getChildren().add(tblPersonagens);
		this.painel.setStyle("-fx-background-color: #aa6000;");
		Scene cena = new Scene(this.painel, 680, 595);
		this.setScene(cena);
		this.setResizable(false);
		this.setTitle("Todos os Personagens");
		this.getIcons().add(new Image("Imagens/icon.png"));
		this.show();

	}

}
