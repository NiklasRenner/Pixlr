package dk.renner.pixlr.game.graphics;

import java.awt.image.BufferedImage;

public class Sprite {
    
    public static final BufferedImage[] player = new BufferedImage[9];
    public static final BufferedImage[] block = new BufferedImage[3];
    public static final BufferedImage[] apple = new BufferedImage[1];

    static {
        load();
    }

    private static final void load() {
        player[0] = SpriteSheet.playerSheet.grabImage(1, 1, 32, 32);
        player[1] = SpriteSheet.playerSheet.grabImage(1, 2, 32, 32);
        player[2] = SpriteSheet.playerSheet.grabImage(2, 1, 32, 32);
        player[3] = SpriteSheet.playerSheet.grabImage(2, 2, 32, 32);
        player[4] = SpriteSheet.playerSheet.grabImage(3, 1, 32, 32);
        player[5] = SpriteSheet.playerSheet.grabImage(3, 2, 32, 32);
        player[6] = SpriteSheet.playerSheet.grabImage(4, 1, 32, 32);
        player[7] = SpriteSheet.playerSheet.grabImage(4, 2, 32, 32);
        player[8] = SpriteSheet.playerSheet.grabImage(4, 3, 32, 32);
        
        block[0] = SpriteSheet.playerSheet.grabImage(1, 4, 32, 32);
        block[1] = SpriteSheet.playerSheet.grabImage(2, 4, 32, 32);
        block[2] = SpriteSheet.playerSheet.grabImage(3, 4, 32, 32);
        
        apple[0] = SpriteSheet.playerSheet.grabImage(5, 4, 32, 32);
    }

}
