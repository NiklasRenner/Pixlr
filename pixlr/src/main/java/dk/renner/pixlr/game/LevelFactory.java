package dk.renner.pixlr.game;

import dk.renner.pixlr.game.graphics.BufferedImageLoader;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.GameObjectFactory;
import dk.renner.pixlr.game.objects.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelFactory {

    public static LevelData loadLevel(BufferedImage levelImage) {
        var players = new ArrayList<Player>();
        var objects = new ArrayList<GameObject>();

        for (int x = 0; x < levelImage.getWidth(); x++) {
            for (int y = 0; y < levelImage.getHeight(); y++) {
                GameObjectFactory.create(levelImage.getRGB(x, y), x, y)
                        .ifPresent(object -> {
                            switch (object) {
                                case Player player -> players.add(player);
                                case GameObject gameObject -> objects.add(gameObject);
                            }
                        });
            }
        }

        if (players.size() != 1) {
            throw new IllegalStateException("Too many or too few players.");
        }

        return new LevelData(players.getFirst(), objects);
    }

    public static LevelData loadLevel(String levelSource) {
        var levelImage = BufferedImageLoader.loadImage(levelSource);

        return loadLevel(levelImage);
    }
}
