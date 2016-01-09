package dk.renner.pixlr.game.objects.blocks;

import dk.renner.pixlr.game.graphics.Sprite;
import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author NiklasRenner
 */
public class Block extends GameObject {
    
    private final BufferedImage image;

    public Block(int id, int width, int height, float x, float y) {
        super(id, width, height, x, y);
        if(id == ObjectEnum.BLOCK.ordinal()){
        image = Sprite.block[0];
        } else if (id == ObjectEnum.GRASS.ordinal()){
            image = Sprite.block[2];
        } else {
            //TODO DEFAULT
            image = null;
        }
    }

    @Override
    public void runLogic() {
        
    }

    @Override
    public void drawGraphics(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
//        g.setColor(Color.red);
//        g.drawRect((int)x,(int)y,32,32);
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
