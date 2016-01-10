package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author NiklasRenner
 */
public class Spikes extends GameObject {

    private final BufferedImage spike;
    private final BufferedImage spikeBloody;
    private final BufferedImage grass;

    private boolean bloody;

    public Spikes(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);
        bloody = false;
        spike = Sprite.block[1];
        grass = Sprite.block[2];
        spikeBloody = Sprite.block[3];
    }

    @Override
    public void runLogic(ArrayList<GameObject> blocks) {

    }

    @Override
    public void drawGraphics(Graphics g) {
        if (bloody) {
            g.drawImage(spikeBloody, (int) x, (int) y, null);
        } else {
            g.drawImage(spike, (int) x, (int) y, null);
        }
        g.drawImage(grass, (int) x, (int) y, null);

//        g.setColor(Color.red);
//        g.drawRect((int) x + 4, (int) y + 24, 24, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 4, (int) y + 24, 24, 8);
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

    public void setBloody() {
        bloody = true;
    }

}
