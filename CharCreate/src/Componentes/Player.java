package Componentes;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Sprite{

	private ImageView Cabelo, Corpo, Roupa, Arma;
	private int cabelo, corcabelo, corpo, roupa; //,arma;
	private Group verPersonagem;
	private boolean andando;
	private Animation animacaoAndando;
	private boolean atacando;
	private boolean flipado = false;

	public Player() {
		this.andando = false;
		this.Corpo = new ImageView();
		this.Arma = new ImageView();
		this.Cabelo = new ImageView();
		this.Roupa = new ImageView();
		Corpo.setViewport(new Rectangle2D(0, 0, 129, 234));
		Arma.setViewport(new Rectangle2D(0, 0, 180, 234));
		Cabelo.setViewport(new Rectangle2D(0, 0, 129, 234));
		Roupa.setViewport(new Rectangle2D(0, 0, 129, 234));
		this.verPersonagem = new Group();
		this.animacaoAndando = new SpriteAnimation(Corpo, Cabelo, Roupa, Arma, Duration.millis(700), 4, 3, 0, 0, 129, 234);
		this.animacaoAndando.setCycleCount(Animation.INDEFINITE);
		this.animacaoAndando.play();
		this.animacaoAndando.stop();
	}

	@Override
	public void setImage(int corpo, int cabelo, int corcabelo, int roupa)
    {
		/*String tipoarma = "espada_1";
		if(roupa == 12)
		{
			tipoarma = "espada_1";
		}else if(roupa == 13){
			tipoarma = "arco_1";
		}*/
		this.Corpo.setImage(new Image("animacoes/sprites_"+corpo+".gif"));
		//this.Arma.setImage(new Image("itens/"+tipoarma+".gif"));
		this.Cabelo.setImage(new Image("animacoes/sprites_"+cabelo+"_"+corcabelo+".gif"));
		this.Roupa.setImage(new Image("animacoes/sprites_"+roupa+".gif"));
		this.verPersonagem.getChildren().addAll(Corpo, Cabelo, Roupa, Arma);
		this.corpo = corpo;
		this.cabelo = cabelo;
		this.corcabelo = corcabelo;
		this.roupa = roupa;
        width = this.Roupa.getImage().getWidth();
        height = this.Roupa.getImage().getHeight()+20;
        try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	@Override
	public void update(double time, Pane pane) {
        positionX += velocityX * time;
        positionY += velocityY * time;
        verPersonagem.setTranslateX(positionX);
		verPersonagem.setTranslateY(positionY);
		pane.getChildren().remove(verPersonagem);
        pane.getChildren().add( verPersonagem );
	}

	@Override
	public void setFlipped(Boolean flipped) {

		if(flipped){
			this.Corpo.setScaleX(-1);
			this.Cabelo.setScaleX(-1);
			this.Roupa.setScaleX(-1);
			this.Arma.setScaleX(-1);
			this.flipado = true;
    	}else{
    		this.Corpo.setScaleX(1);
			this.Cabelo.setScaleX(1);
			this.Roupa.setScaleX(1);
			this.Arma.setScaleX(1);
			this.flipado = false;
    	}
	}

	@Override
	public void render(Pane pane) {
		verPersonagem.setTranslateX(positionX);
		verPersonagem.setTranslateY(positionY);
        pane.getChildren().add( verPersonagem );
	}

	public void ficarParado(Pane root){
		this.Corpo.setImage(new Image("animacoes/sprites_"+corpo+".gif"));
		this.Cabelo.setImage(new Image("animacoes/sprites_"+cabelo+"_"+corcabelo+".gif"));
		this.Roupa.setImage(new Image("animacoes/sprites_"+roupa+".gif"));
		this.animacaoAndando.playFrom(Duration.millis(100));
		this.update(1, root);
	}

	public void andarDireita(Pane root){
		andando = true;
		this.setVelocity(5, 0);
		this.setFlipped(false);
		this.update(1, root);
	}

	public void andarEsquerda(Pane root){
		andando = true;
		this.setVelocity(-5, 0);
		this.setFlipped(true);
		this.update(1, root);
	}

	public void atacar(Pane root, Player personagem){
		atacando = true;
		AtackAnimation atack = new AtackAnimation(Roupa, Arma, Duration.millis(450), personagem);
		atack.play();
		atack.setOnFinished(value -> {
			atacando = false;
			ficarParado(root);
		});
	}

	public boolean isAndando() {
		return andando;
	}

	public void setAndando(boolean andando) {
		this.andando = andando;
	}

	@Override
	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX + (129 /2) - 5,positionY,15,254);
	}

	public boolean isAtacando() {
		return atacando;
	}

	public void setAtacando(boolean atacando) {
		this.atacando = atacando;
	}

	public boolean isFlipado() {
		return flipado;
	}




}
