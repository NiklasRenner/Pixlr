package dk.renner.pixlr.game.objects;

import java.util.Optional;

public final class GameObjectFactory {

    private static final int TILE_SIZE = 32;
    private static final int OBJECT_WIDTH = 64;
    private static final int OBJECT_HEIGHT = 64;

    private GameObjectFactory() {
    }

    public static Optional<GameObject> create(int rgb, int tileX, int tileY) {
        return GameObjectType.fromRgb(rgb)
                .flatMap(type -> create(type, tileX, tileY));
    }

    private static Optional<GameObject> create(GameObjectType type, int tileX, int tileY) {
        float x = tileX * TILE_SIZE;
        float y = tileY * TILE_SIZE;

        GameObject object = switch (type) {
            case BLOCK -> new Block(GameObjectType.BLOCK, OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case SPIKES -> new Spikes(OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case GRASS -> new Block(GameObjectType.GRASS, OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case LASER_TOP -> new Laser(GameObjectType.LASER_TOP, OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case LASER_BOTTOM -> new Laser(GameObjectType.LASER_BOTTOM, OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case CHECKPOINT -> new Checkpoint(OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case MOVING_BLOCK -> new MovingBlock(OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case PLAYER -> new Player(OBJECT_WIDTH, OBJECT_HEIGHT, x, y);
            case NONE -> null;
        };

        return Optional.ofNullable(object);
    }
}
