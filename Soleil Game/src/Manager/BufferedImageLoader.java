package Manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String ss) throws IOException {
        image = ImageIO.read(getClass().getResource(ss));
        return image;
    }
}
