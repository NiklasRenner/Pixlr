package dk.renner.pixlr.game.objects;

import dk.renner.pixlr.game.graphics.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public non-sealed class Block extends GameObject {
    
    private final BufferedImage image;

    public Block(GameObjectType type, int width, int height, float x, float y) {
        super(type, width, height, x, y);
        image = switch (type) {
            case BLOCK, MOVING_BLOCK -> Sprite.block[0];
            case GRASS -> Sprite.block[2];
            default -> throw new IllegalArgumentException("Unsupported block type: " + type);
        };
    }

    public boolean isSolid() {
        return getType() == GameObjectType.BLOCK || getType() == GameObjectType.MOVING_BLOCK;
    }

    @Override
    public void runLogic(List<GameObject> blocks) {
        
    }

    @Override
    public void drawGraphics(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    @Override
    public Rectangle getBoundsTop() {
        throw new UnsupportedOperationException("Use getBounds on blocks."); 
    }
    
    @Override
    public Rectangle getBoundsBottom(){
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
