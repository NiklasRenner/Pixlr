package dk.renner.pixlr.game.graphics;

import java.awt.image.BufferedImage;

public class Sprite {
    
    public static final BufferedImage[] player = new BufferedImage[9];
    public static final BufferedImage[] block = new BufferedImage[4];
    public static final BufferedImage[] apple = new BufferedImage[1];
    public static final BufferedImage[] laserTop = new BufferedImage[2];
    public static final BufferedImage[] laserBottom = new BufferedImage[2];
    public static final BufferedImage[] checkpoint = new BufferedImage[10];

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
        block[3] = SpriteSheet.playerSheet.grabImage(2, 3, 32, 32);

        apple[0] = SpriteSheet.playerSheet.grabImage(5, 4, 32, 32);

        laserTop[0] = SpriteSheet.playerSheet.grabImage(6, 5, 32, 32);
        laserTop[1] = SpriteSheet.playerSheet.grabImage(8, 5, 32, 32);

        laserBottom[0] = SpriteSheet.playerSheet.grabImage(5, 5, 32, 32);
        laserBottom[1] = SpriteSheet.playerSheet.grabImage(7, 5, 32, 32);

        checkpoint[0] = SpriteSheet.playerSheet.grabImage(7, 3, 32, 32);
        checkpoint[1] = SpriteSheet.playerSheet.grabImage(8, 3, 32, 32);
        checkpoint[3] = SpriteSheet.playerSheet.grabImage(9, 3, 32, 32);
        checkpoint[4] = SpriteSheet.playerSheet.grabImage(10, 3, 32, 32);
        checkpoint[5] = SpriteSheet.playerSheet.grabImage(11, 3, 32, 32);
        checkpoint[6] = SpriteSheet.playerSheet.grabImage(12, 3, 32, 32);
        checkpoint[7] = SpriteSheet.playerSheet.grabImage(13, 3, 32, 32);
        checkpoint[8] = SpriteSheet.playerSheet.grabImage(14, 3, 32, 32);
        checkpoint[9] = SpriteSheet.playerSheet.grabImage(15, 3, 32, 32);
    }

}
