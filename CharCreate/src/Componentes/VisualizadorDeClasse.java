package Componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class VisualizadorDeClasse extends HBox{

	private Label lblClasse;
	private AnchorPane Fundo;

	public VisualizadorDeClasse() {
		this.lblClasse = new Label();
		this.Fundo = new AnchorPane();

		AnchorPane.setTopAnchor(this.lblClasse, 2D);
		AnchorPane.setLeftAnchor(this.lblClasse, 2D);
		AnchorPane.setRightAnchor(this.lblClasse, 2D);
		AnchorPane.setBottomAnchor(this.lblClasse, 2D);

		this.getChildren().add(this.Fundo);
		this.Fundo.getChildren().add(this.lblClasse);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: #2E2E2E; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: black; -fx-opacity: 0.85;");
		this.lblClasse.setStyle("-fx-font-size: 12pt; -fx-text-fill: #E6E6E6; -fx-font-weight: bold;");
	}

	public void setText(String classe){
		this.lblClasse.setText(classe);
	}

	public String getText(){
		return lblClasse.getText();
	}



}
