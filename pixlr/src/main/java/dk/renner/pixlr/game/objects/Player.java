package dk.renner.pixlr.game.objects;

import dk.renner.pixlr.game.config.Configuration;
import dk.renner.pixlr.game.graphics.Animation;
import dk.renner.pixlr.game.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public final class Player extends GameObject {
    private static final float GRAVITY = 0.50f;
    private static final float JUMP_HEIGHT = -8f;
    private static final float WALK_SPEED = 3;
    private static final float MAX_SPEED = 25f;
    private static final boolean DOUBLE_JUMPING_ALLOWED = true;
    private static final int START_LIVES = 5;
    private static final int FACING_IDLE = 0;
    private static final int FACING_RIGHT = 1;
    private static final int FACING_LEFT = 2;
    private static final int HITBOX_X = 18;
    private static final int HITBOX_Y = 10;
    private static final int HITBOX_WIDTH = 26;
    private static final int HITBOX_HEIGHT = 38;
    private static final int TOP_HITBOX_X = 22;
    private static final int TOP_HITBOX_Y = 10;
    private static final int TOP_HITBOX_WIDTH = 16;
    private static final int TOP_HITBOX_HEIGHT = 4;
    private static final int BOTTOM_HITBOX_X = 22;
    private static final int BOTTOM_HITBOX_Y = 44;
    private static final int BOTTOM_HITBOX_WIDTH = 16;
    private static final int BOTTOM_HITBOX_HEIGHT = 4;
    private static final int SIDE_HITBOX_Y = 14;
    private static final int SIDE_HITBOX_WIDTH = 4;
    private static final int SIDE_HITBOX_HEIGHT = 30;
    private static final int LEFT_HITBOX_X = 18;
    private static final int RIGHT_HITBOX_X = 38;
    private static final int HEAD_BUMP_Y_OFFSET = 42;
    private static final int LANDING_Y_OFFSET = 47;

    private final Animation idleAnim = new Animation(10, Sprite.player[0], Sprite.player[1]);
    private final Animation leftAnim = new Animation(3, Sprite.player[2], Sprite.player[3], Sprite.player[2]);
    private final Animation rightAnim = new Animation(3, Sprite.player[4], Sprite.player[5], Sprite.player[4]);
    private final BufferedImage idleFallSprite = Sprite.player[6];
    private final BufferedImage leftFallSprite = Sprite.player[8];
    private final BufferedImage rightFallSprite = Sprite.player[7];

    private int facing = FACING_IDLE;
    private boolean jumping = false;
    private boolean doubleJumping = false;
    private boolean falling = false;
    private boolean walkingLeft = false;
    private boolean walkingRight = false;

    private int lives = START_LIVES;
    private float spawnX;
    private float spawnY;

    public Player(int width, int height, float x, float y) {
        super(GameObjectType.PLAYER, width, height, x, y);

        spawnX = x;
        spawnY = y;
    }

    public void jump() {
        if (!jumping && !falling) {
            velY = JUMP_HEIGHT;
            jumping = true;
        } else if (DOUBLE_JUMPING_ALLOWED && jumping && !doubleJumping) {
            velY = JUMP_HEIGHT;
            doubleJumping = true;
        }
    }

    public void walkLeft(boolean walking) {
        walkingLeft = walking;
        setXMovement();
    }

    public void walkRight(boolean walking) {
        walkingRight = walking;
        setXMovement();
    }

    private void setXMovement() {
        if (walkingLeft == walkingRight) {
            velX = 0;
        } else if (walkingLeft) {
            velX = -WALK_SPEED;
        } else {
            velX = WALK_SPEED;
        }
    }

    @Override
    public void runLogic(List<GameObject> blocks) {
        var collided = collisionCheck(blocks);
        updateFacingAnimation();

        if (!collided) {
            x += velX;
        }
        y += velY;
        velY += GRAVITY;

        if (velY > MAX_SPEED) {
            velY = MAX_SPEED;
        } else if (velY < -MAX_SPEED) {
            velY = -MAX_SPEED;
        }
    }

    public boolean collisionCheck(List<GameObject> blocks) {
        boolean hit = false;
        boolean botHit = false;

        for (GameObject object : blocks) {
            if (!canInteractWith(object)) {
                continue;
            }

            switch (object) {
                case Block block when block.isSolid() -> {
                    CollisionType collision = collideWithBlock(block);
                    hit = hit || collision.blocksHorizontalMovement();
                    botHit = botHit || collision.landedOnGround();
                }
                case Spikes spikes -> {
                    if (intersectsFutureBounds(spikes)) {
                        damageAndRespawn();
                        spikes.setBloody();
                    }
                }
                case Laser laser -> {
                    if (intersectsFutureBounds(laser)) {
                        damageAndRespawn();
                    }
                }
                case Checkpoint checkpoint -> {
                    if (intersectsFutureBounds(checkpoint)) {
                        checkpoint.checkpointReached(this);
                    }
                }
                default -> {
                    // Non-solid objects and decorative blocks do not affect the player.
                }
            }
        }

        if (!botHit) {
            falling = true;
        }

        return hit;
    }

    private void updateFacingAnimation() {
        if (velX < 0) {
            facing = FACING_LEFT;
            leftAnim.runAnimation();
        } else if (velX > 0) {
            facing = FACING_RIGHT;
            rightAnim.runAnimation();
        } else {
            facing = FACING_IDLE;
            idleAnim.runAnimation();
        }
    }

    private CollisionType collideWithBlock(GameObject block) {
        if (getFutureBoundsLeft().intersects(block.getBounds()) || getFutureBoundsRight().intersects(block.getBounds())) {
            return CollisionType.HORIZONTAL;
        }

        if (getFutureBoundsTop().intersects(block.getBounds())) {
            hitHeadOn(block);
            return CollisionType.TOP;
        }

        if (getFutureBoundsBottom().intersects(block.getBounds())) {
            landOn(block);
            return CollisionType.BOTTOM;
        }

        return CollisionType.NONE;
    }

    private void hitHeadOn(GameObject block) {
        y = block.getY() + block.getHeight() - HEAD_BUMP_Y_OFFSET;
        velY = 0;
    }

    private void landOn(GameObject block) {
        y = block.getY() - LANDING_Y_OFFSET;
        jumping = false;
        doubleJumping = false;
        falling = false;
        x += block.getVelX();
        if (velY >= MAX_SPEED) {
            damageAndRespawn();
        }
        velY = 0;
    }

    private boolean intersectsFutureBounds(GameObject object) {
        return getFutureBoundsLeft().intersects(object.getBounds())
                || getFutureBoundsRight().intersects(object.getBounds())
                || getFutureBoundsTop().intersects(object.getBounds())
                || getFutureBoundsBottom().intersects(object.getBounds());
    }

    private void damageAndRespawn() {
        lives--;
        moveToSpawn();
    }

    public void moveToSpawn() {
        x = spawnX;
        y = spawnY;
    }

    @Override
    public void drawGraphics(Graphics g) {
        if (!falling) {
            switch (facing) {
                case FACING_IDLE -> idleAnim.drawAnimation(g, (int) x, (int) y, width, height);
                case FACING_RIGHT -> rightAnim.drawAnimation(g, (int) x, (int) y, width, height);
                case FACING_LEFT -> leftAnim.drawAnimation(g, (int) x, (int) y, width, height);
            }
        } else {
            switch (facing) {
                case FACING_IDLE -> g.drawImage(idleFallSprite, (int) x, (int) y, width, height, null);
                case FACING_RIGHT -> g.drawImage(rightFallSprite, (int) x, (int) y, width, height, null);
                case FACING_LEFT -> g.drawImage(leftFallSprite, (int) x, (int) y, width, height, null);
            }
        }
    }

    public int getLives() {
        return lives;
    }

    @Override
    public Rectangle getBounds() {
        return bounds(HITBOX_X, HITBOX_Y, HITBOX_WIDTH, HITBOX_HEIGHT);
    }

    @Override
    public Rectangle getBoundsTop() {
        return bounds(TOP_HITBOX_X, TOP_HITBOX_Y, TOP_HITBOX_WIDTH, TOP_HITBOX_HEIGHT);
    }

    @Override
    public Rectangle getBoundsBottom() {
        return bounds(BOTTOM_HITBOX_X, BOTTOM_HITBOX_Y, BOTTOM_HITBOX_WIDTH, BOTTOM_HITBOX_HEIGHT);
    }

    @Override
    public Rectangle getBoundsRight() {
        return bounds(RIGHT_HITBOX_X, SIDE_HITBOX_Y, SIDE_HITBOX_WIDTH, SIDE_HITBOX_HEIGHT);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return bounds(LEFT_HITBOX_X, SIDE_HITBOX_Y, SIDE_HITBOX_WIDTH, SIDE_HITBOX_HEIGHT);
    }

    public Rectangle getFutureBoundsTop() {
        var bounds = getBoundsTop();
        bounds.y += (int) velY;
        return bounds;
    }

    public Rectangle getFutureBoundsBottom() {
        var bounds = getBoundsBottom();
        bounds.y += (int) velY;
        return bounds;
    }

    public Rectangle getFutureBoundsRight() {
        var bounds = getBoundsRight();
        bounds.x += (int) velX;
        return bounds;
    }

    public Rectangle getFutureBoundsLeft() {
        var bounds = getBoundsLeft();
        bounds.x += (int) velX;
        return bounds;
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

    public void setSpawnPoint(int x, int y) {
        spawnX = x;
        spawnY = y;
    }

    private Rectangle bounds(int offsetX, int offsetY, int width, int height) {
        return new Rectangle((int) x + offsetX, (int) y + offsetY, width, height);
    }
}
