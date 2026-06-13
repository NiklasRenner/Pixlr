package dk.renner.pixlr.game.objects;

import java.awt.*;
import java.util.List;

public class MovingBlock extends Block {

    public MovingBlock(int width, int height, float x, float y) {
        super(GameObjectType.MOVING_BLOCK, width, height, x, y);
        velX = 2;
    }

    @Override
    public void runLogic(List<GameObject> blocks) {
        super.runLogic(blocks);
        for (GameObject block : blocks) {
            if (canInteractWith(block) && block instanceof Block && getFutureBounds().intersects(block.getBounds())) {
                if (block instanceof MovingBlock movingBlock) {
                    if (getFutureBounds().intersects(movingBlock.getFutureBounds())) {
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
    }

    public Rectangle getFutureBounds() {
        var now = getBounds();
        now.x = (int) (x + velX);
        return now;
    }
}
