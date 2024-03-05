package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldx,worldy;
    public  int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

   // create a abstract reactangle
    public Rectangle solidArea;
    public int solidAreaDefaultX , getSolidAreaDefaultY;
    public  boolean collisionOn = false;

}

//change 1
