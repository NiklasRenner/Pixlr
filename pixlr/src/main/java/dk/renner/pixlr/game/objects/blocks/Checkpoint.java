package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Daniel on 09-01-2016.
 */
public class Checkpoint extends GameObject {

    private static final BufferedImage[] checkpointSprite = Sprite.checkpoint;

    private boolean active;
    private Rectangle hitbox;

    public Checkpoint(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);
        hitbox = new Rectangle((int)x,(int)y,32,32);
        active = false;
    }

    @Override
    public void runLogic() {

    }

    @Override
    public void drawGraphics(Graphics g) {
        if (active) {
            g.drawImage(checkpointSprite[1], (int) x, (int) y, null);
        } else {
            g.drawImage(checkpointSprite[0], (int) x, (int) y, null);
        }
//        g.setColor(Color.BLACK);
//        g.drawRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);
    }

    @Override
    public Rectangle getBounds() {
        return hitbox;
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

    public void checkpointReached(Player player){
        player.setSpawnPoint((int)x-16,(int)y-32);
        active = true;
    }
}
