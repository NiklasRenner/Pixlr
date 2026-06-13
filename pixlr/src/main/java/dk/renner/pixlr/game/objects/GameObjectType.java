package dk.renner.pixlr.game.objects;

import java.awt.Color;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameObjectType {

    NONE(Color.BLACK),
    PLAYER(Color.ORANGE),
    BLOCK(Color.BLUE),
    SPIKES(Color.RED),
    GRASS(Color.GREEN),
    LASER_TOP(Color.GRAY),
    LASER_BOTTOM(Color.WHITE),
    CHECKPOINT(Color.YELLOW),
    MOVING_BLOCK(new Color(128, 128, 0));

    private final int rgb;
    private static final Map<Integer, GameObjectType> BY_RGB = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(type -> type.rgb, Function.identity()));

    GameObjectType(Color color) {
        rgb = color.getRGB();
    }

    public int rgb() {
        return rgb;
    }

    public static Optional<GameObjectType> fromRgb(int rgb) {
        return Optional.ofNullable(BY_RGB.get(rgb));
    }
}
