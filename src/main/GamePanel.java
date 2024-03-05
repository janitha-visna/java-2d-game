package main;

import entity.Player;
import object.SuperObject;
import title.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen setting
    final int originalTitleSize = 16; // 16*16 title
    final int scale = 3;
    public final int titleSize = originalTitleSize * scale; // 48*48 title
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    //world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //fps
    int FPS = 60;

    //system
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public  AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    // enitity and object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    //set player's default position
    int playerX = 100;
    int playerY = 100;

    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;  //0.01666 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 100000000) {
                System.out.println("fps" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //tileM and player order is important

        //tile
        tileM.draw(g2);

        //object
        for (int i=0; i < obj.length; i++){
            if (obj[i] != null ){
                obj[i].draw(g2,this);
            }
        }

        //player
        player.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
