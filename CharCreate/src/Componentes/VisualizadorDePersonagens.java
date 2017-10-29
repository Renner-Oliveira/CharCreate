package Componentes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class VisualizadorDePersonagens extends StackPane{

	private ImageView Cabelo, Corpo, Roupa, bg, admemblema;

	private Label lblNomePersonagem;

	private StringProperty nomePersonagem;

	@SuppressWarnings("static-access")
	public VisualizadorDePersonagens() {

		this.Corpo = new ImageView();
		this.Cabelo = new ImageView();
		this.Roupa = new ImageView();
		this.bg = new ImageView();
		this.admemblema = new ImageView();
		this.lblNomePersonagem = new Label();
		this.nomePersonagem = new SimpleStringProperty();
		this.nomePersonagem.set("");
		this.getChildren().add(bg);
		this.getChildren().add(Corpo);
		this.getChildren().add(Cabelo);
		this.getChildren().add(Roupa);
		this.getChildren().add(admemblema);
		this.lblNomePersonagem.setStyle("-fx-font-size: 12pt; -fx-text-fill: #E6E6E6; -fx-font-weight: bold;");
		this.setAlignment(lblNomePersonagem, Pos.BOTTOM_CENTER);
		this.setAlignment(admemblema, Pos.TOP_CENTER);
		this.getChildren().add(lblNomePersonagem);
		this.lblNomePersonagem.textProperty().bind(nomePersonagem);
	}

	public void setDesign(int corpo, int cabelo, int corcabelo, int roupa) {


		this.Corpo.setImage(new Image("Imagens/sprites_"+corpo+".png"));
		this.Corpo.setX(0);
		this.Corpo.setY(11);


		this.Cabelo.setImage(new Image("Imagens/sprites_"+cabelo+"_"+corcabelo+".png"));
		this.Cabelo.setX(0);
		this.Cabelo.setY(11);


		this.Roupa.setImage(new Image("Imagens/sprites_"+roupa+".png"));
		this.Roupa.setX(0);
		this.Roupa.setY(11);

	}
	public void setDesignFurlan() {

		this.Corpo.setImage(new Image("Imagens/sprites_8.png"));
		this.Corpo.setX(0);
		this.Corpo.setY(11);

		this.Cabelo.setImage(new Image("Imagens/sprites_5_1.png"));
		this.Cabelo.setX(0);
		this.Cabelo.setY(11);

		this.Roupa.setImage(new Image("Imagens/sprites_31.png"));
		this.Roupa.setX(0);
		this.Roupa.setY(11);
	}

	public void setBG(String url){
		this.bg.setImage(new Image(url));
	}

	public void setADM(){
		this.admemblema.setImage(new Image("Imagens/sprites_adm.png"));
	}

	public void clean() {

		this.bg.setImage(new Image("Imagens/bgpersonsnotcreate.png"));

		this.Corpo.setImage(null);

		this.Cabelo.setImage(null);

		this.Roupa.setImage(null);

		this.nomePersonagem.set("");
	}

	public String getNomePersonagem() {
		return nomePersonagem.get();
	}

	public StringProperty NomePersonagem() {
		return nomePersonagem;
	}

	public void setNomePersonagem(String nomePersonagem) {
		this.nomePersonagem.set(nomePersonagem);
	}

}