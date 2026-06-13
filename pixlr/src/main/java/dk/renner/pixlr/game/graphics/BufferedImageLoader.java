package dk.renner.pixlr.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class BufferedImageLoader {

    private BufferedImageLoader() {
    }

    public static BufferedImage loadImage(String path) {
        try (InputStream stream = BufferedImageLoader.class.getResourceAsStream("/" + path)) {
            if (stream == null) {
                throw new IllegalArgumentException("Could not find resource: " + path);
            }
            BufferedImage image = ImageIO.read(stream);
            if (image == null) {
                throw new IllegalArgumentException("Resource is not a supported image: " + path);
            }
            return image;
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not load image resource: " + path, ex);
        }
    }

}
