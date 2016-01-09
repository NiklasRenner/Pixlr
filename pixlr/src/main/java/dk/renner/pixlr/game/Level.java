package dk.renner.pixlr.game;

import dk.renner.pixlr.game.graphics.BufferedImageLoader;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;
import dk.renner.pixlr.game.objects.blocks.Block;
import dk.renner.pixlr.game.objects.blocks.Laser;
import dk.renner.pixlr.game.objects.blocks.Spikes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author NiklasRenner
 */
public class Level {

    private BufferedImage levelImage;

    public Level(String levelSource) {
        levelImage = BufferedImageLoader.loadImage(levelSource);
    }

    public ArrayList<GameObject> loadLevel() {
        ArrayList<GameObject> objects = new ArrayList<>();

        for (int x = 0; x < levelImage.getWidth(); x++) {
            for (int y = 0; y < levelImage.getHeight(); y++) {
                int pixel = levelImage.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 0 && green == 0 && blue == 255) {
                    objects.add(new Block(ObjectEnum.BLOCK.ordinal(), 64, 64, x * 32, y * 32));
                }

                if (red == 255 && green == 0 && blue == 0) {
                    objects.add(new Spikes(ObjectEnum.SPIKES.ordinal(), 64, 64, x * 32, y * 32));
                }

                if (red == 0 && green == 255 && blue == 0) {
                    objects.add(new Block(ObjectEnum.GRASS.ordinal(), 64, 64, x * 32, y * 32));
                }

                if (red == 128 && green == 128 && blue == 128) {
                    objects.add(new Laser(ObjectEnum.LASER_TOP.ordinal(), 64, 64, x * 32, y * 32));
                }

                if (red == 255 && green == 255 && blue == 255) {
                    objects.add(new Laser(ObjectEnum.LASER_BOTTOM.ordinal(), 64, 64, x * 32, y * 32));
                }

            }
        }

        return objects;
    }

}
