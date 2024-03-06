package main;

import object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        OBJ_key key = new OBJ_key();
        keyImage = key.image;
    }


    //dont use the line below it will consume resourese beacuse it will call this method 60 times
    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage,gp.titleSize/2,gp.titleSize/2,gp.titleSize,gp.titleSize,null);
        g2.drawString("x"+ gp.player.hasKey,75,65);
    }
}


