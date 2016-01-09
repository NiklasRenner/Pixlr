package dk.renner.pixlr.game.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

    private final int frames;
    private final BufferedImage[] images;
    
    private int speed;
    private int index = 0;
    private int count = 1;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        System.arraycopy(args, 0, images, 0, args.length);
        frames = args.length;
        currentImg = images[0];
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i) {
                currentImg = images[i];
            }
        }
        count++;

        if (count > frames) {
            count = 0;
        }
    }
    
    public void reset(){
        index = 0;
        count = 1;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void drawAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }
    
    public BufferedImage getFrame(){
        return currentImg;
    }
}
