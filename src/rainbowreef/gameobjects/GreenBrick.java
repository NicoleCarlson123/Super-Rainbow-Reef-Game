package rainbowreef.gameobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GreenBrick extends GameObjects{
    private Rectangle bounds;
    public GreenBrick(BufferedImage img, int x, int y) {
        super(x, y, img);
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        gameObjects.add(this);
    }
}
