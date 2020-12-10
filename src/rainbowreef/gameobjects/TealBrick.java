package rainbowreef.gameobjects;

import rainbowreef.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TealBrick extends GameObjects {
    private Rectangle bounds;
    public TealBrick(BufferedImage img, int x, int y) {
        super(x, y, img);
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        gameObjects.add(this);
    }
}
