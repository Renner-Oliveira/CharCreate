package Componentes;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final ImageView imageView2;
    private final ImageView imageView3;
    private final ImageView imageView4;
    private int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            ImageView imageView2,
            ImageView imageView3,
            ImageView imageView4,
            Duration duration,
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
        this.imageView = imageView;
        this.imageView2 = imageView2;
        this.imageView3 = imageView3;
        this.imageView4 = imageView4;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            //System.out.println(index);
            //System.out.println(x);
            final int y = (index / columns) * height + offsetY;
            final int x2 = (index % columns) * (width+51)  + offsetX;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            imageView4.setViewport(new Rectangle2D(x2, y, width, height));
            imageView2.setViewport(new Rectangle2D(x, y, width, height));
            imageView3.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }

	public void setCount(int count) {
		this.count = count;
	}





}