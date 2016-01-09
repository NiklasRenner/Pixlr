package dk.renner.pixlr.game.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    private final BufferedImage image;
    public static final SpriteSheet playerSheet = new SpriteSheet(BufferedImageLoader.loadImage("graphics/player.png"));

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage grabImage(int col, int row, int width, int height){
        return image.getSubimage((col * width) - width, (row * height) - height, width, height);
    }
    
}
