package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Laser extends GameObject {

    private BufferedImage[] laserSprite;
    private int timer;
    private int counter;
    private boolean active;

    public Laser(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);

        if (id == ObjectEnum.LASER_TOP.ordinal()) {
            laserSprite = Sprite.laserTop;
        } else if (id == ObjectEnum.LASER_BOTTOM.ordinal()) {
            laserSprite = Sprite.laserBottom;
        }

        timer = 150;
        active = true;
    }

    @Override
    public void runLogic() {
        counter++;
        if (counter >= timer) {
            active = !active;
            counter = 0;
        }
    }

    @Override
    public void drawGraphics(Graphics g) {
        if (active) {
            g.drawImage(laserSprite[0], (int) x, (int) y, null);
        } else {
            g.drawImage(laserSprite[1], (int) x, (int) y, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle getBoundsTop() {
        return null;
    }

    @Override
    public Rectangle getBoundsBottom() {
        return null;
    }

    @Override
    public Rectangle getBoundsLeft() {
        return null;
    }

    @Override
    public Rectangle getBoundsRight() {
        return null;
    }
}
