package rainbowreef.gameobjects;

import rainbowreef.Katch;
import rainbowreef.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Reef extends GameObjects{
    private int R = 1;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int lives;
    private int angle;
    private int minPos;
    private int maxPos;
    private int points;
    private int scored;
    private int spawnX;
    private int spawnY;
    private int bigLegsAlive;
    private boolean isAlive;
    private boolean isMoving;
    private Rectangle bounds = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());

    public Reef(BufferedImage img, int x, int y, int vx, int vy) {
        super(x,y,img);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.lives = 3;
        this.points = 0;
        this.spawnY = y;
        this.spawnX = x;
        this.isAlive = true;
        this.isMoving = false;
        this.bigLegsAlive = 3;
        this.bounds = new Rectangle(x,y,this.img.getWidth(),this.img.getHeight());
        Map.objects.add(this);
    }

    public void update() {
        move();
        updateBounds();
        checkCollision(this);
        if (lives != 0) {
            respawn();
        }
        if (getLegs() == 0) {
            System.exit(1);
        }
    }

    public void respawn() {
        if (y > 470) {
            lives -= 1;
            this.x = spawnX;
            this.y = spawnY;
        }
    }

    public void move() {
        x += vx;
        y += vy;
        if (x < 0) vx = -vx;
        if (y < 0) vy = -vy;
        if (x > 640) vx = -vx;
        isMoving = true;
    }

    public int getLives() {
        return lives;
    }

    public int getLegs() {
        return bigLegsAlive;
    }

    public int getPoints() {
        return points;
    }

    public void setScore(int x) {
        this.scored = x;
    }

    public void scorePoints() {
        points += scored;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void updateBounds() {
        this.bounds = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }

    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform rotation = AffineTransform.getTranslateInstance(x,y);
        g2.drawImage(this.img, rotation, null);
    }

    public void checkCollision(Reef reef){
        GameObjects obj;
        Rectangle tbound = reef.getBounds();
        for (int i =0; i< Map.objects.size();i++){
            obj = Map.objects.get(i);
            if (tbound.intersects(obj.getBounds())){
                handle(obj);
            }
        }
    }

    public void handle(GameObjects obj) {
        if (obj instanceof RedBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(2);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof YellowBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(4);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof BlueBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(6);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof GreenBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(8);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof TealBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(10);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof DoubleBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(20);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof PurpleBrick) {
            if (this.isMoving) {
                vy = -vy;
                setScore(12);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof Health) {
            if (this.isMoving) {
                vy = -vy;
                lives +=1;
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof StarBrick) {
            if (this.isMoving) {
                vy = -vy;
                points *= 2;
                Map.objects.remove(obj);
            } else vx = -vx;
        }
        if (obj instanceof Wall) {
            if (this.isMoving) {
                vx = -vx;
            } else vy = -vy;
        }
        if (obj instanceof Katch) {
            if (this.isMoving) {
                vy = -vy;
            }
        }
        if (obj instanceof BigLegs) {
            if(this.isMoving) {
                vy = -vy;
                bigLegsAlive -= 1;
                setScore(100);
                scorePoints();
                Map.objects.remove(obj);
            } else vx = -vx;
        }

}}
