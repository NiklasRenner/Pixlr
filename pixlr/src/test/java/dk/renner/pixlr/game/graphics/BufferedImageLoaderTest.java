package dk.renner.pixlr.game.graphics;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BufferedImageLoaderTest {

    @Test
    void loadImageLoadsClasspathResource() {
        BufferedImage image = BufferedImageLoader.loadImage("graphics/player.png");

        assertEquals(512, image.getWidth());
        assertEquals(512, image.getHeight());
    }

    @Test
    void loadImageFailsClearlyWhenResourceIsMissing() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> BufferedImageLoader.loadImage("graphics/missing.png")
        );

        assertEquals("Could not find resource: graphics/missing.png", exception.getMessage());
    }
}
