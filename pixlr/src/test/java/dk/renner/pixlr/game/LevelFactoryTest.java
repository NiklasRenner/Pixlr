package dk.renner.pixlr.game;

import dk.renner.pixlr.game.objects.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LevelFactoryTest {

    @Test
    void loadLevelMapsKnownPixelColorsToObjects() {
        BufferedImage image = new BufferedImage(8, 1, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, GameObjectType.BLOCK.rgb());
        image.setRGB(1, 0, GameObjectType.SPIKES.rgb());
        image.setRGB(2, 0, GameObjectType.GRASS.rgb());
        image.setRGB(3, 0, GameObjectType.LASER_TOP.rgb());
        image.setRGB(4, 0, GameObjectType.LASER_BOTTOM.rgb());
        image.setRGB(5, 0, GameObjectType.CHECKPOINT.rgb());
        image.setRGB(6, 0, GameObjectType.MOVING_BLOCK.rgb());
        image.setRGB(7, 0, GameObjectType.PLAYER.rgb());

        var data = LevelFactory.loadLevel(image);

        assertEquals(7, data.objects().size());
        assertInstanceOf(Player.class, data.player());
        assertEquals(GameObjectType.BLOCK, data.objects().get(0).getType());
        assertInstanceOf(Block.class, data.objects().get(0));
        assertInstanceOf(Spikes.class, data.objects().get(1));
        assertEquals(GameObjectType.SPIKES, data.objects().get(1).getType());
        assertEquals(GameObjectType.GRASS, data.objects().get(2).getType());
        assertInstanceOf(Block.class, data.objects().get(2));
        assertEquals(GameObjectType.LASER_TOP, data.objects().get(3).getType());
        assertInstanceOf(Laser.class, data.objects().get(3));
        assertEquals(GameObjectType.LASER_BOTTOM, data.objects().get(4).getType());
        assertInstanceOf(Laser.class, data.objects().get(4));
        assertInstanceOf(Checkpoint.class, data.objects().get(5));
        assertEquals(GameObjectType.CHECKPOINT, data.objects().get(5).getType());
        assertInstanceOf(MovingBlock.class, data.objects().get(6));
        assertEquals(GameObjectType.MOVING_BLOCK, data.objects().get(6).getType());
    }

    @Test
    void loadLevelIgnoresUnknownPixelColors() {
        BufferedImage image = new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, Color.MAGENTA.getRGB());
        image.setRGB(1, 0, GameObjectType.PLAYER.rgb());

        assertEquals(0, LevelFactory.loadLevel(image).objects().size());
    }

    @Test
    void generatedTestLevelLoadsPlayableObjectSet() {
        var data = LevelFactory.loadLevel("graphics/generated-test-level.png");

        assertInstanceOf(Player.class, data.player());
        assertTrue(data.objects().stream().anyMatch(Block.class::isInstance));
        assertTrue(data.objects().stream().anyMatch(Spikes.class::isInstance));
        assertTrue(data.objects().stream().anyMatch(Laser.class::isInstance));
        assertTrue(data.objects().stream().anyMatch(Checkpoint.class::isInstance));
        assertTrue(data.objects().stream().anyMatch(MovingBlock.class::isInstance));
    }
}
