package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author NiklasRenner
 */
public class Apple extends GameObject {

    private final BufferedImage appleSprite;

    public Apple(int id, int width, int height, float x, float y, int direction) {
        super(id, width, height, x + 16, y + 8);
        appleSprite = Sprite.apple[0];
        velY = -5;
        if (direction == 1) {
            velX = +10;
        } else {
            velX = -10;
        }
    }

    @Override
    public void runLogic() {
        if(velY<25){
            velY+=0.5f;
        }
        x += velX;
        y += velY;
        if(velX>0){
            velX-=0.15f;
            if(velX<0){
                velX = 0;
            }
        } else if (velX<0) {
            velX+=0.15f;
            if(velX>0){
                velX = 0;
            }
        }
    }

    public void collision(ArrayList<GameObject> objects) {
        for (GameObject object : objects) {
            if (object.getId() == ObjectEnum.BLOCK.ordinal()) {

                if (getFutureBoundsLeft().intersects(object.getBounds()) && velX<0) {
                    x = object.getX() + object.getWidth() - 40;
                    velX = -velX/1.5f;
                } else if (getFutureBoundsRight().intersects(object.getBounds()) && velX >0) {
                    x = object.getX() - 26;
                    velX = -velX/1.5f;
                } else if (getFutureBoundsTop().intersects(object.getBounds()) && velY < 0) {
                    y = object.getY() + object.getHeight() - 20;
                    velY = -velY/1.5f;
                } else if (getFutureBoundsBottom().intersects(object.getBounds()) && velY >0) {
                    y = object.getY() - 21;
                    velY = -velY/1.5f;
                }

            }
        }
    }

    @Override
    public void drawGraphics(Graphics g) {
        g.drawImage(appleSprite, (int) x, (int) y, null);
//        g.setColor(Color.black);
//        //top
//        g.drawRect((int) x + 11, (int) y + 8, 6, 4);
//        //bottom
//        g.drawRect((int) x + 11, (int) y + 18, 6, 4);
//        //left
//        g.drawRect((int) x + 8, (int) y + 12, 4, 6);
//        //right
//        g.drawRect((int) x + 14, (int) y + 12, 6, 6);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 18, (int) y + 10, 26, 38);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + 11, (int) y + 8, 6, 4);
    }

    @Override
    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + 11, (int) y + 18, 6, 4);
    }

    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + 14, (int) y + 12, 6, 6);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + 8, (int) y + 12, 4, 6);
    }

    public Rectangle getFutureBoundsTop() {
        return new Rectangle((int) x + 11, (int) y + 8 + (int)velY, 6, 4);
    }

    public Rectangle getFutureBoundsBottom() {
        return new Rectangle((int) x + 11, (int) y + 18 + (int)velY, 6, 4);
    }

    public Rectangle getFutureBoundsRight() {
        return new Rectangle((int) x + 14 + (int)velX, (int) y + 12, 6, 6);
    }

    public Rectangle getFutureBoundsLeft() {
        return new Rectangle((int) x + 8 + (int) velX, (int) y + 12, 4, 6);
    }

}
