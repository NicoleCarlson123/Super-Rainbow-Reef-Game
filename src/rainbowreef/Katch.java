package rainbowreef;

import rainbowreef.gameobjects.GameObjects;
import rainbowreef.gameobjects.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Katch extends GameObjects {
    private int R = 3;
    private int angle;
    private int vx;
    private int vy;
    private boolean LeftPressed;
    private boolean RightPressed;
    private boolean UpPressed;
    private boolean DownPressed;
    private Rectangle bounds = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());

    public Katch(int x, int y, int vx, int vy, BufferedImage img) {
        super(x,y,img);
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        Map.objects.add(this);
    }

    void toggleLeftPressed() { this.LeftPressed = true; }

    void toggleRightPressed() { this.RightPressed = true; }

    void toggleUpPressed() { this.UpPressed = true; }

    void toggleDownPressed() { this.DownPressed = true; }

    void unToggleLeftPressed() { this.LeftPressed = false; }

    void unToggleRightPressed() { this.RightPressed = false; }

    void unToggleUpPressed() { this.UpPressed = false; }

    void unToggleDownPressed() { this.DownPressed = false; }

    public void update() {
        if (this.LeftPressed) {
            this.moveLeft();
            updateBounds();
        }
        if (this.RightPressed) {
            this.moveRight();
            updateBounds();
        }
        if (this.UpPressed) {
            this.moveUp();
            updateBounds();
        }
        if (this.DownPressed) {
            this.moveDown();
            updateBounds();
        }
        checkCollision(this);
    }

    public void moveLeft() {
        vx = R;
        vy = 0;
        x -= vx;
        y -= vy;
        updateBounds();
    }

    public void moveRight() {
        vx = R;
        vy = 0;
        x += vx;
        y += vy;
        updateBounds();
    }

    public void moveUp() {
        vy = R;
        vx = 0;
        y -= vy;
        x -= vx;
    }

    public void moveDown() {
        vy = R;
        vx = 0;
        y += vy;
        x += vx;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void updateBounds() {
        this.bounds = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }

    public void checkCollision(Katch katch) {
        GameObjects obj;
        Rectangle kbounds = katch.getBounds();
        for (int i=0; i<Map.objects.size(); i++) {
            obj = Map.objects.get(i);
            if (kbounds.intersects(obj.getBounds())) {
                handle(obj);
            }
        }
    }

    public void handle(GameObjects obj) {
        if (obj instanceof Wall) {
            if (this.LeftPressed) {
                this.x += vx;
            }
            if (this.RightPressed) {
                this.x -= vx;
            }
        }
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform rotation = AffineTransform.getTranslateInstance(x,y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        g2.drawImage(this.img, rotation, null);
    }

    @Override
    public String toString() {
        return "x: " + x + "y: " + y;
    }
}

