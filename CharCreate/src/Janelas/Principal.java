package Janelas;

import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application{

	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	public static int contalogada = 1;
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		JanelaLogin janela = null;
		if(janela == null)
		{
			janela = new JanelaLogin();
		}
		janela.show();
	}

}
