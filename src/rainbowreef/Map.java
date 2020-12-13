package rainbowreef;

import rainbowreef.gameobjects.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Map {
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static ArrayList<GameObjects> objects = new ArrayList<>();
    private BufferedImage background;

    public Map() {
    }

    public void init() {
        try {
        background = read(Objects.requireNonNull(Game.class.getClassLoader().getResource("Background1.bmp")));
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
