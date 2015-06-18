package Manager;

import java.awt.image.BufferedImage;

public class PlayerSprite {

    private BufferedImage image;

    public PlayerSprite(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return img;
    }
    
    public BufferedImage grabImageBullet(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 30) - 30, (row * 30) - 30, width, height);
        return img;
    }
}
