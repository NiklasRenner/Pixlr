package dk.renner.pixlr.game;

import dk.renner.pixlr.game.config.Configuration;
import dk.renner.pixlr.game.objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Game extends Frame {
    private long then;
    private int fps;
    private int frameCount;

    private final LevelData level;

    static void main() {
        new Game();
    }

    public Game() {
        level = LevelFactory.loadLevel("graphics/generated-test-level.png");

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

        Player player = level.player();

        if (e.getKeyCode() == KeyEvent.VK_R) {
            player.moveToSpawn();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.jump();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.walkLeft(true);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.walkRight(true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Player player = level.player();

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.walkLeft(false);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.walkRight(false);
        }
    }

    @Override
    public void runLogic() {
        Player player = level.player();
        var blocks = level.objects();

        blocks.forEach(block -> block.runLogic(blocks));
        player.runLogic(blocks);
    }

    @Override
    public void drawGraphics(Graphics rootGraphics) {
        rootGraphics.setColor(new Color(0, 125, 255));
        rootGraphics.fillRect(0, 0, Configuration.getInstance().getWidth(), Configuration.getInstance().getHeight());

        Player player = level.player();
        var blocks = level.objects();

        var worldGraphics = rootGraphics.create();
        try {
            worldGraphics.translate(
                    (int) player.getCamX(),
                    (int) player.getCamY()
            );

            player.drawGraphics(worldGraphics);
            blocks.forEach(block -> block.drawGraphics(worldGraphics));
        } finally {
            worldGraphics.dispose();
        }

        drawHud(rootGraphics, player);
    }

    private void drawHud(Graphics g, Player player) {
        frameCount++;
        var now = System.currentTimeMillis();
        if (now - then >= 1000) {
            fps = frameCount;
            then = now;
            frameCount = 0;
        }
        g.setColor(Color.white);
        g.drawString("FPS: " + fps, 8, 40);
        g.drawString("Lives: " + player.getLives(), 8, 52);
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
}
