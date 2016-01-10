package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Laser extends GameObject {

    private BufferedImage[] laserSprite;
    private int timer;
    private int counter;
    private boolean active;

    private Rectangle rec = new Rectangle((int) x + 15, (int) y, 2, 32);

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
    public void runLogic(ArrayList<GameObject> blocks) {
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
//        g.setColor(Color.RED);
//        g.drawRect(rec.x, rec.y, rec.width, rec.height);
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

    public boolean isActive() {
        return active;
    }
}
