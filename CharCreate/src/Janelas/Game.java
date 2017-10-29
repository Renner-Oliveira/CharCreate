package Janelas;
import java.util.ArrayList;

import Componentes.Player;
import Componentes.Sprite;
import Entidades.Personagem;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Stage{

	Player personagem = new Player();
	Sprite chao = new Sprite();
	Sprite fundo = new Sprite();
	Sprite paredeleft = new Sprite();
	Sprite parederight = new Sprite();
    private boolean caindo = false;

	public Game(Personagem personagemlogado)
	{
	    this.setTitle( "Jogo" );

	    Pane root = new Pane();
	    root.setPrefSize(900, 500);
	    Scene theScene = new Scene( root );

	    ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();

                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );

                }
            });

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
            	    //personagem.setImage(new Image("personagem.png"));
                }
            });

	    this.setScene( theScene );

	    personagem.setPosition(200, 100);
	    personagem.setImage(personagemlogado.getNum_pele(),personagemlogado.getNum_cabelo(),personagemlogado.getNum_corcabelo(),personagemlogado.getNum_classe());
	    personagem.render(root);
	    fundo.setImage(new Image("Imagens/fundasso.png"));
	    fundo.setPosition(0,0);
	    fundo.render(root);
	    chao.setImage(new Image("Imagens/chao.png"));
	    chao.setPosition(0,399);
	    chao.render(root);
	    paredeleft.setImage(new Image("Imagens/a.gif"));
	    paredeleft.setPosition(0, 0);
	    paredeleft.render(root);
	    parederight.setImage(new Image("Imagens/a.gif"));
	    parederight.setPosition(900,0);
	    parederight.render(root);


	    new AnimationTimer()
	    {


			@Override
			public void handle(long arg0) {

				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//esse aqui testa colisão com o chao!
				if ( !personagem.intersects(chao) )
	            {
					personagem.setVelocity(0, 10);
					personagem.update(1, root);
					caindo = true;
	            }else{
	            	caindo = false;
	            }


				if(!personagem.isAndando()){
					personagem.ficarParado(root);
				}

				if (input.contains("RIGHT") && !input.contains("LEFT") && !personagem.isAtacando()){

					personagem.andarDireita(root);

				}
				if (input.contains("LEFT") && !input.contains("RIGHT") && !personagem.intersects(paredeleft) && !personagem.isAtacando()){

					personagem.andarEsquerda(root);

				}
	            if (input.contains("RIGHT") && input.contains("LEFT")){
	                personagem.setVelocity(0, 0);
	                personagem.setAndando(false);
	            }

	            /*if (input.contains("A") && !personagem.isAtacando()){
	                personagem.setVelocity(0, 0);
	                personagem.setAndando(false);
	                personagem.atacar(root, personagem);
	            }*/

				if (!input.contains("RIGHT") && !input.contains("LEFT") && caindo == false){
	                personagem.setVelocity(0, 0);
	                personagem.setAndando(false);
				}



			}
	    }.start();




	    this.show();


	}

}
