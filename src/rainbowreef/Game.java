package rainbowreef;

import rainbowreef.gameobjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static javax.imageio.ImageIO.read;

public class Game extends JPanel {
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static final int SCREEN_WIDTH = 640;
    public static final int SCREEN_HEIGHT = 470;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;

    private Katch katch;
    private Reef reef;
    private Map background;
    public static void main(String[] args) throws InterruptedException{

    }

    private void initialize(){
        this.jf = new JFrame("Rainbow Reef");
        this.world = new BufferedImage(Game.GAME_WIDTH, Game.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage katchImg = null;
        BufferedImage reefImg = null;
        BufferedImage blueBrick = null;
        BufferedImage doubleBrick = null;
        BufferedImage greenBrick = null;
        BufferedImage purpleBrick = null;
        BufferedImage redBrick = null;
        BufferedImage starBrick = null;
        BufferedImage tealBrick = null;
        BufferedImage yellowBrick = null;
        BufferedImage wall = null;
        try {
            System.out.println(System.getProperty("user.dir"));
            katchImg = read(new File("resources/Katch.gif"));
            reefImg = read(new File("resources/pop.gif"));
            blueBrick = read(new File("resources/Block6.gif"));
            doubleBrick = read(new File("resources/Block_double.gif"));
            greenBrick = read(new File("resources/Block4.gif"));
            purpleBrick = read(new File("resources/Block1.gif"));
            redBrick = read(new File("resources/Block3.gif"));
            starBrick = read(new File("resources/Block_split.gif"));
            tealBrick = read(new File("resources/Block5.gif"));
            yellowBrick = read(new File("resources/Block2.gif"));
            wall = read(new File("resources/ Block_solid.gif"));

            InputStreamReader isr = new InputStreamReader(Game.class.getClassLoader().getResourceAsStream("maps/map1"));
            BufferedReader mapReader = new BufferedReader(isr);
            String row = mapReader.readLine();
            if(row == null){
                throw new IOException("no data in file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow < numRows; curRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol = 0; curCol < numCols; curCol++){
                    switch(mapInfo[curCol]){
                        case "1":
                            Map.objects.add(new BlueBrick( blueBrick, curCol *30, curRow*30));
                            break;
                        case "2":
                            Map.objects.add(new DoubleBrick(doubleBrick, curCol *30, curRow*30));
                            break;
                        case "3":
                            Map.objects.add(new GreenBrick(greenBrick, curCol *30, curRow*30));
                            break;
                        case "4":
                            Map.objects.add(new PurpleBrick(purpleBrick, curCol *30, curRow*30));
                            break;
                        case "5":
                            Map.objects.add(new RedBrick(redBrick, curCol *30, curRow*30));
                            break;
                        case "6":
                            Map.objects.add(new StarBrick(starBrick, curCol *30, curRow*30));
                            break;
                        case "7":
                            Map.objects.add(new TealBrick(tealBrick, curCol *30, curRow*30));
                            break;
                        case "8":
                            Map.objects.add(new YellowBrick(yellowBrick, curCol *30, curRow*30));
                            break;
                        case "9":
                            Map.objects.add(new Wall(wall, curCol *30, curRow*30));


                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        katch = new Katch(280,410,0,0,katchImg);
        reef = new Reef(reefImg, 280,340,-1,-2);

        background = new Map();
        background.init();

        KatchControl kc = new KatchControl(katch, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);
        this.jf.addKeyListener(kc);
        this.jf.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.background.drawImage(buffer);
        this.katch.drawImage(buffer);
        this.reef.drawImage(buffer);

        g2.drawImage(world,0,0,null);

        g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g2.setColor(Color.BLACK);
        g2.drawString("Lives :" + this.reef.getLives(), 25, 445);
        if (this.reef.getLives() == 0) {
            g2.drawString("GAME OVER", 190,445);
        }
        g2.drawString("Score : " + this.reef.getPoints(), 470, 445);


    }
}
