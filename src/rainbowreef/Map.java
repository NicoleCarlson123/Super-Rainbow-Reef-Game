package rainbowreef;

import rainbowreef.gameobjects.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Map {
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static ArrayList<GameObjects> objects = new ArrayList<>();
    private BufferedImage background, wall, redbrick, yellowbrick, bluebrick, purplebrick, greenbrick, healthbrick, doublebrick, starbrick, tealbrick, biglegs;

    public Map() {
    }

    public void init() {
        try {
            wall = read(new File("resources/Wall.gif"));
            biglegs = read(new File("resources/Bigleg.gif"));
            redbrick = read(new File("resources/Block3.gif"));
            bluebrick = read(new File("resources/Block6.gif"));
            tealbrick = read(new File("resources/Block5.gif"));
            greenbrick = read(new File("resources/Block4.gif"));
            yellowbrick = read(new File("resources/Block2.gif"));
            purplebrick = read(new File("resources/Block1.gif"));
            starbrick = read(new File("resources/Block_split.gif"));
            healthbrick = read(new File("resources/Block_life.gif"));
            background = read(new File("resources/Background2.bmp"));
            doublebrick = read(new File("resources/Block_double.gif"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
    void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i=0; i<GAME_WIDTH/background.getWidth()+1; i++) {
            for (int j=0; j<GAME_HEIGHT/background.getHeight()+1; j++) {
                g2.drawImage(background, i*background.getWidth(), j*background.getHeight(), null);
            }
        }
        for (int i=0; i<objects.size(); i++) {
            objects.get(i).drawImage(g2);
        }
    }
}
