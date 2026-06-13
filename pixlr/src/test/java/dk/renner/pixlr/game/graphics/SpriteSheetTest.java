package dk.renner.pixlr.game.graphics;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpriteSheetTest {

    @Test
    void grabImageSlicesByOneBasedColumnAndRow() {
        BufferedImage source = new BufferedImage(4, 4, BufferedImage.TYPE_INT_RGB);
        source.setRGB(2, 2, Color.RED.getRGB());

        BufferedImage slice = new SpriteSheet(source).grabImage(2, 2, 2, 2);

        assertEquals(2, slice.getWidth());
        assertEquals(2, slice.getHeight());
        assertEquals(Color.RED.getRGB(), slice.getRGB(0, 0));
    }
}
