package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldx + gp.player.screenX;
        int screenY = worldY - gp.player.worldy + gp.player.screenY;


        if (worldX + gp.titleSize > gp.player.worldx - gp.player.screenX &&
                worldX - gp.titleSize < gp.player.worldx + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldy - gp.player.screenY &&
                worldY - gp.titleSize < gp.player.worldy + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }
}
