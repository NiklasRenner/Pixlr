package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by NiklasRenner on 10-01-2016.
 */
public class MovingBlock extends Block {

    public MovingBlock(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);
        velX = 2;
    }

    @Override
    public void runLogic(ArrayList<GameObject> blocks) {
        super.runLogic(blocks);
        for (GameObject block : blocks) {
            if (block instanceof Block && block != this && getFutureBounds().intersects(block.getBounds())) {
                if (block instanceof MovingBlock) {
                    if (getFutureBounds().intersects(((MovingBlock) block).getFutureBounds())) {
                        velX = -velX;
                        break;
                    }
                } else {
                    velX = -velX;
                    break;
                }
            }
        }
        x += velX;
    }

    @Override
    public void drawGraphics(Graphics g) {
        super.drawGraphics(g);
//        Rectangle future = getFutureBounds();
//        g.setColor(Color.BLACK);
//        g.drawRect(future.x, future.y, future.width, future.height);
    }

    public Rectangle getFutureBounds() {
        Rectangle now = getBounds();
        now.x = (int) (x + velX);
        return now;
    }

}
