package dk.renner.pixlr.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class BufferedImageLoader {

    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream(path);
            image = ImageIO.read(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

}
