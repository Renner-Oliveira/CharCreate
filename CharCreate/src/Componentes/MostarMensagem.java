package Componentes;

import Entidades.Personagem;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MostarMensagem {

	private Alert alertaviso;

	public MostarMensagem() {
		this.alertaviso = new Alert(AlertType.WARNING);
		this.alertaviso.setHeaderText(null);
		Stage stage = (Stage) this.alertaviso.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("Imagens/icon.png"));
		DialogPane dialogPane = this.alertaviso.getDialogPane();
		dialogPane.getStylesheets().add("css/messagebox.css");
		dialogPane.getStyleClass().add("messagebox");
	}

	public void Alert(String titulo, String mensagem){
		this.alertaviso.setAlertType(AlertType.WARNING);
		this.alertaviso.setHeaderText(null);
		this.alertaviso.setGraphic(new ImageView("Imagens/iconwarning.png"));
		this.alertaviso.setTitle(titulo);
		this.alertaviso.setContentText(mensagem);
		this.alertaviso.showAndWait();
	}

	public void Info(String titulo, String mensagem){
		this.alertaviso.setAlertType(AlertType.INFORMATION);
		this.alertaviso.setHeaderText(null);
		this.alertaviso.setGraphic(new ImageView("Imagens/iconinfo.png"));
		this.alertaviso.setTitle(titulo);
		this.alertaviso.setContentText(mensagem);
		this.alertaviso.showAndWait();
	}
	public void Furlan(){
		this.alertaviso.setAlertType(AlertType.INFORMATION);
		this.alertaviso.setHeaderText("Andre Furlan?");
		this.alertaviso.setGraphic(new ImageView("Imagens/iconinfo.png"));
		this.alertaviso.setTitle("Alert EasterEgg");
		this.alertaviso.setContentText("Bem Vindo Administrador! :p");
		this.alertaviso.showAndWait();
	}

	public boolean ConfirmarDelete(String nomePersonagem, ObservableList<Personagem> listaDePersonagem, int numPersonagem){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setGraphic(new ImageView("Imagens/iconwarning.png"));
		dialog.setTitle("Deletar Personagem");
		dialog.setHeaderText("Digite o nome do seu personagem para confirmar a remoção");
		dialog.setContentText("Nome: ");
		DialogPane dialogPane2 = dialog.getDialogPane();
		dialogPane2.getStylesheets().add("css/messagebox.css");
		dialogPane2.getStyleClass().add("messagebox");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    if(result.get().equals(nomePersonagem))
		    {
		    	listaDePersonagem.remove(numPersonagem);
		    	return true;
		    }
		    else
		    {
		    	this.Alert("Nome incorreto.", "O nome não é igual do personagem.");
		    	return false;
		    }
		}
		return false;
	}



}
