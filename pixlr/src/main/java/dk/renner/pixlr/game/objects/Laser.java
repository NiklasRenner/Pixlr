package dk.renner.pixlr.game.objects;

import dk.renner.pixlr.game.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public final class Laser extends GameObject {

    private final BufferedImage[] laserSprite;
    private final int timer;
    private int counter;

    private final Rectangle rec = new Rectangle((int) x + 15, (int) y, 2, 32);

    public Laser(GameObjectType type, int width, int height, float x, float y) {
        this(type, width, height, x, y, 150);
    }

    Laser(GameObjectType type, int width, int height, float x, float y, int timer) {
        super(type, width, height, x, y);
        laserSprite = switch (type) {
            case LASER_TOP -> Sprite.laserTop;
            case LASER_BOTTOM -> Sprite.laserBottom;
            default -> throw new IllegalArgumentException("Unsupported laser type: " + type);
        };
        this.timer = timer;
    }

    @Override
    public void runLogic(List<GameObject> blocks) {
        counter++;
        if (counter >= timer) {
            setActive(!isActive());
            counter = 0;
        }
    }

    @Override
    public void drawGraphics(Graphics g) {
        if (isActive()) {
            g.drawImage(laserSprite[0], (int) x, (int) y, null);
        } else {
            g.drawImage(laserSprite[1], (int) x, (int) y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return rec;
    }

    @Override
    public Rectangle getBoundsTop() {
        throw new UnsupportedOperationException("Use getBounds on blocks.");
    }

    @Override
    public Rectangle getBoundsBottom() {
        throw new UnsupportedOperationException("Use getBounds on blocks.");
    }

    @Override
    public Rectangle getBoundsLeft() {
        throw new UnsupportedOperationException("Use getBounds on blocks.");
    }

    @Override
    public Rectangle getBoundsRight() {
        throw new UnsupportedOperationException("Use getBounds on blocks.");
    }

}
