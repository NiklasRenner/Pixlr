package dk.renner.pixlr.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int id;
    protected int width;
    protected int height;
    protected float x;
    protected float y;
    protected float velX;
    protected float velY;

    public GameObject(int id, int width, int height, float x, float y) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        velX = 0;
        velY = 0;
    }

    public abstract void runLogic();

    public abstract void drawGraphics(Graphics g);

    public int getId() {
        return id;
    }

    public abstract Rectangle getBounds();

    public abstract Rectangle getBoundsTop();
    
    public abstract Rectangle getBoundsBottom();

    public abstract Rectangle getBoundsLeft();

    public abstract Rectangle getBoundsRight();
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
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
