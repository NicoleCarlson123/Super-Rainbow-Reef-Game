package rainbowreef.gameobjects;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class YellowBrick extends GameObjects {
    private Rectangle bounds;
    public YellowBrick(BufferedImage img, int x, int y) {
        super(x, y, img);
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        gameObjects.add(this);

    }
}
