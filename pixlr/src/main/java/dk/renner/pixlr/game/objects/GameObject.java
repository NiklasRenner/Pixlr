package dk.renner.pixlr.game.objects;

import java.awt.*;
import java.util.List;

public abstract sealed class GameObject permits Block, Checkpoint, Laser, Player, Spikes {

    protected int width;
    protected int height;
    protected float x;
    protected float y;
    protected float velX;
    protected float velY;
    private final GameObjectType type;
    private boolean active;
    private boolean interactable;

    public GameObject(GameObjectType type, int width, int height, float x, float y) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        velX = 0;
        velY = 0;
        active = true;
        interactable = true;
    }

    public abstract void runLogic(List<GameObject> blocks);

    public abstract void drawGraphics(Graphics g);

    public GameObjectType getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isInteractable() {
        return active && interactable;
    }

    public void setInteractable(boolean interactable) {
        this.interactable = interactable;
    }

    public boolean canInteractWith(GameObject other) {
        return other != this && isInteractable() && other.isInteractable();
    }

    public abstract Rectangle getBounds();

    public abstract Rectangle getBoundsTop();

    public abstract Rectangle getBoundsBottom();

    public abstract Rectangle getBoundsLeft();

    public abstract Rectangle getBoundsRight();

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

}
