package title;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage() {


        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldRow) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;

                }
            }
            br.close();

        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {
//        g2.drawImage(tile[0].image,0,0,gp.titleSize, gp.titleSize,null);
//        g2.drawImage(tile[1].image,48,0,gp.titleSize, gp.titleSize,null);
//        g2.drawImage(tile[2].image,96,0,gp.titleSize, gp.titleSize,null);
        int worldCol = 0;
        int worldRow = 0;
//        int x = 0;
//        int y = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldx + gp.player.screenX;
            int screenY = worldY - gp.player.worldy + gp.player.screenY;


            if (worldX + gp.titleSize > gp.player.worldx - gp.player.screenX &&
                    worldX - gp.titleSize < gp.player.worldx + gp.player.screenX &&
                    worldY + gp.titleSize > gp.player.worldy - gp.player.screenY &&
                    worldY - gp.titleSize < gp.player.worldy + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            }
            worldCol++;
//            x += gp.titleSize;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
//                x = 0;
                worldRow++;
//                y += gp.titleSize;
            }
        }
    }
}



