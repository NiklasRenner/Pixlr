package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author NiklasRenner
 */
public class Spikes extends GameObject {

    private final BufferedImage image;
    private final BufferedImage image2;

    public Spikes(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);
        image = Sprite.block[1];
        image2 = Sprite.block[2];
    }

    @Override
    public void runLogic(ArrayList<GameObject> blocks) {

    }

    @Override
    public void drawGraphics(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
        g.drawImage(image2,(int)x, (int)y, null);

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

}
