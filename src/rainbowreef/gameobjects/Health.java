package rainbowreef.gameobjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health extends GameObjects{
    private Rectangle bounds;

    public Health(BufferedImage img, int x, int y) {
        super(x,y,img);
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        gameObjects.add(this);
    }
}

