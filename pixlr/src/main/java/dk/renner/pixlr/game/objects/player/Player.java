package dk.renner.pixlr.game.objects.player;

import dk.renner.pixlr.game.Configuration;
import dk.renner.pixlr.game.graphics.Animation;
import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;
import dk.renner.pixlr.game.objects.blocks.Apple;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {

    private static final float GRAVITY = 0.50f;
    private static final float JUMP_HEIGHT = -10f;
    private static final float WALK_SPEED = 3;
    private static final float MAX_SPEED = 25f;
    private static final boolean DOUBLE_JUMPING_ALLOWED = true;
    private static final int START_LIVES = 5;

    private final Animation idleAnim;
    private final Animation leftAnim;
    private final Animation rightAnim;
    private final BufferedImage idleFallSprite;
    private final BufferedImage leftFallSprite;
    private final BufferedImage rightFallSprite;

    /*
     direction player is facing
     0 - idle
     1 - right
     2 - left
     3 - up
     4 - down
     */
    private int facing;
    private boolean jumping;
    private boolean doubleJumping;
    private boolean falling;
    private boolean walkingLeft;
    private boolean walkingRight;
    private int lives = START_LIVES;
    private final ArrayList<Apple> apples;
    private int last;

    public Player(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);

        idleAnim = new Animation(10, Sprite.player[0], Sprite.player[1]);
        leftAnim = new Animation(3, Sprite.player[2], Sprite.player[3], Sprite.player[2]);
        rightAnim = new Animation(3, Sprite.player[4], Sprite.player[5], Sprite.player[4]);
        idleFallSprite = Sprite.player[6];
        leftFallSprite = Sprite.player[8];
        rightFallSprite = Sprite.player[7];

        jumping = false;
        doubleJumping = false;
        falling = false;
        walkingLeft = false;
        walkingRight = false;
        facing = 0;
        apples = new ArrayList<>();

        last = 0;
    }

    public void shoot() {
        int direction;
        if (velX == 0) {
            direction = last;
        } else if (velX < 0) {
            direction = 2;
        } else {
            direction = 1;
        }
        apples.add(new Apple(ObjectEnum.PLAYER.ordinal(), 32, 32, (int) x, (int) y, direction));
    }

    public void jump() {
        if (!jumping && !falling) {
            velY = JUMP_HEIGHT;
            jumping = true;
        } else if (jumping && !doubleJumping && DOUBLE_JUMPING_ALLOWED) {
            velY = JUMP_HEIGHT;
            doubleJumping = true;
        }
    }

    public void walkLeft(boolean walking) {
        last = 2;
        walkingLeft = walking;
        setXMovement();
    }

    public void walkRight(boolean walking) {
        last = 1;
        walkingRight = walking;
        setXMovement();
    }

    private void setXMovement() {
        if ((walkingLeft && walkingRight) || (!walkingLeft && !walkingRight)) {
            velX = 0;
        } else if (walkingLeft && !walkingRight) {
            velX = -WALK_SPEED;
        } else if (!walkingLeft && walkingRight) {
            velX = WALK_SPEED;
        }
    }

    @Override
    public void runLogic() {

        if (velX < 0) {
            facing = 2;
            leftAnim.runAnimation();
        } else if (velX > 0) {
            facing = 1;
            rightAnim.runAnimation();
        } else {
            facing = 0;
            idleAnim.runAnimation();
        }

        x += velX;
        y += velY;

        velY += GRAVITY;

        if (velY > MAX_SPEED) {
            velY = MAX_SPEED;
        } else if (velY < -MAX_SPEED) {
            velY = -MAX_SPEED;
        }

        for (Apple apple : apples) {
            apple.runLogic();
        }

    }

    public void collisionCheck(ArrayList<GameObject> objects) {

        boolean botHit = false;

        for (GameObject object : objects) {

            if (object.getId() == ObjectEnum.BLOCK.ordinal()) {

                if (getFutureBoundsLeft().intersects(object.getBounds())) {
                    x = object.getX() + object.getWidth() - 50;
                    velX = 0;
                } else if (getFutureBoundsRight().intersects(object.getBounds())) {
                    x = object.getX() - 44;
                    velX = 0;
                } else if (getFutureBoundsTop().intersects(object.getBounds())) {
                    y = object.getY() + object.getHeight() - 42;
                    velY = 0;
                } else if (getFutureBoundsBottom().intersects(object.getBounds())) {
                    y = object.getY() - 47;
                    botHit = true;
                    jumping = false;
                    doubleJumping = false;
                    falling = false;
                    velY = 0;
                }

            } else if (object.getId() == ObjectEnum.SPIKES.ordinal()) {
                if (getFutureBoundsLeft().intersects(object.getBounds())
                        || getFutureBoundsRight().intersects(object.getBounds())
                        || getFutureBoundsTop().intersects(object.getBounds())
                        || getFutureBoundsBottom().intersects(object.getBounds())) {
                    lives--;
                    if (lives <= 0) {
                        //System.exit(0);
                    }
                    x = 25;
                    y = 25;
                }
            }

        }

        if (!botHit) {
            falling = true;
        }

        for (Apple apple : apples) {
            apple.collision(objects);
        }

    }

    @Override
    public void drawGraphics(Graphics g) {

        if (!falling) {
            switch (facing) {
                case 0:
                    idleAnim.drawAnimation(g, (int) x, (int) y, width, height);
                    break;
                case 1:
                    rightAnim.drawAnimation(g, (int) x, (int) y, width, height);
                    break;
                case 2:
                    leftAnim.drawAnimation(g, (int) x, (int) y, width, height);
                    break;
            }
        } else {
            switch (facing) {
                case 0:
                    g.drawImage(idleFallSprite, (int) x, (int) y, width, height, null);
                    break;
                case 1:
                    g.drawImage(rightFallSprite, (int) x, (int) y, width, height, null);
                    break;
                case 2:
                    g.drawImage(leftFallSprite, (int) x, (int) y, width, height, null);
                    break;
            }
        }

        for (Apple apple : apples) {
            apple.drawGraphics(g);
        }

//        g.setColor(Color.blue);
//        //full
//        g.drawRect((int) x + 18, (int) y + 10, 26, 38);
//        g.setColor(Color.black);
//        //top
//        g.drawRect((int) x + 22, (int) y + 10, 16, 4);
//        //bottom
//        g.drawRect((int) x + 22, (int) y + 44, 16, 4);
//        //left
//        g.drawRect((int) x + 18, (int) y + 14, 4, 30);
//        //right
//        g.drawRect((int) x + 38, (int) y + 14, 4, 30);
    }

    public int getLives() {
        return lives;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 18, (int) y + 10, 26, 38);
    }

    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + 22, (int) y + 10, 16, 4);
    }

    @Override
    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + 22, (int) y + 44, 16, 4);
    }

    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + 38, (int) y + 14, 4, 30);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + 18, (int) y + 14, 4, 30);
    }

    public Rectangle getFutureBoundsTop() {
        return new Rectangle((int) x + 22, (int) y + 10 + (int) velY, 16, 4);
    }

    public Rectangle getFutureBoundsBottom() {
        return new Rectangle((int) x + 22, (int) y + 44 + (int) velY, 16, 4);
    }

    public Rectangle getFutureBoundsRight() {
        return new Rectangle((int) x + 38 + (int) velX, (int) y + 14, 4, 30);
    }

    public Rectangle getFutureBoundsLeft() {
        return new Rectangle((int) x + 18 + (int) velX, (int) y + 14, 4, 30);
    }

    public float getCamX() {
        float camX = -x - width / 2 + Configuration.getInstance().getWidth() / 2;
        if (camX > 0) {
            camX = 0;
        }
        return camX;
    }

    public float getCamY() {
        float camY = -y - height / 2 + Configuration.getInstance().getHeight() / 2;
        if (camY > 26) {
            camY = 26;
        }
        if (Configuration.getInstance().isFullscreen()) {
            camY -= 26;
        }
        return camY;
    }

}
