package Componentes;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import javafx.geometry.Rectangle2D;

public class Sprite
{
	private Node imageV;
	protected Rectangle2D boundary;
	protected double positionX;
	protected double positionY;
	protected double velocityX;
	protected double velocityY;
    protected double width;
    protected double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i)
    {
    	imageV = new ImageView(i);
        width = i.getWidth();
        height = i.getHeight()+2;

    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setFlipped(Boolean flipped){
    	if(flipped){
    		imageV.setScaleX(-1);
    	}else{
    		imageV.setScaleX(1);
    	}
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time, Pane pane)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
        try {
        	Thread.sleep(1);
        } catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        pane.getChildren().remove(imageV);
        this.render(pane);
    }

    public void render(Pane pane)
    {
    	imageV.setTranslateX(positionX);
    	imageV.setTranslateY(positionY);
        pane.getChildren().add( imageV );
    }

    public Rectangle2D getBoundary()
    {
    	return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
        + " Velocity: [" + velocityX + "," + velocityY + "]" +
        " Tamanho: [" + width + "," + height + "]";
    }

	public void setImage(int corpo, int cabelo, int corcabelo, int roupa) {
		// TODO Auto-generated method stub

	}
}