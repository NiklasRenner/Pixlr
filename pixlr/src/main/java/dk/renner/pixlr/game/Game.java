package dk.renner.pixlr.game;

import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.ObjectEnum;
import dk.renner.pixlr.game.objects.player.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

/**
 * @author NiklasRenner
 */
public class Game extends Frame {

    public static final int X = 64;
    public static final int Y = 64;
    private long then;
    private int fps;
    private int frameCount;

    //tmp
    private final Player player;
    private final ArrayList<GameObject> blocks;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        player = new Player(ObjectEnum.PLAYER.ordinal(), 64, 64, X, Y);
        blocks = new Level("graphics/levelTest.png").loadLevel();

        then = System.currentTimeMillis();
        fps = 0;
        frameCount = 0;

        start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            player.setX(X);
            player.setY(Y);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.jump();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.walkLeft(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.walkRight(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.walkLeft(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.walkRight(false);
        }
    }

    @Override
    public void runLogic() {
        player.runLogic(blocks);
    }

    @Override
    public void drawGraphics(Graphics g) {
        g.setColor(new Color(0, 125, 255));
        g.fillRect(0, 0, Configuration.getInstance().getWidth(), Configuration.getInstance().getHeight());
        g.translate((int) player.getCamX(), (int) player.getCamY());
        player.drawGraphics(g);
        for (GameObject block : blocks) {
            block.runLogic(blocks);
            block.drawGraphics(g);
        }
        g.translate((int) -player.getCamX(), (int) -player.getCamY());
        getFps(g);
        g.drawString("Lives: " + player.getLives(), 8, 52);
        g.dispose();
    }

    @Override
    public void mousePressedAction(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseReleasedAction(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseMovedAction(MouseEvent e) {
        //TODO
    }

    @Override
    public void mouseWheelMovedAction(MouseWheelEvent e) {
        //TODO
    }

    public void getFps(Graphics g) {
        frameCount++;
        long now = System.currentTimeMillis();
        if (now - then >= 1000) {
            fps = frameCount;
            then = now;
            frameCount = 0;
        }
        g.setColor(Color.white);
        g.drawString("FPS: " + fps, 8, 40);
    }

}
