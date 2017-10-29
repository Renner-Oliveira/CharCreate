package Componentes;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class AtackAnimation extends Transition {

    private final ImageView imageView;
    private final ImageView imageView2;
    private final Player personagem;

    public AtackAnimation(
            ImageView imageView,
            ImageView imageView2,
            Duration duration,
            Player personagem
    		) {
        this.imageView = imageView;
        this.imageView2 = imageView2;
        this.personagem = personagem;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {

	    	imageView.setViewport(new Rectangle2D(0, 234, 210, 234));
	    	imageView2.setViewport(new Rectangle2D(0, 234, 210, 234));


        }
    }