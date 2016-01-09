package dk.renner.pixlr.game;

import dk.renner.pixlr.utils.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public abstract class Frame extends JFrame implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {

    private BufferStrategy bufferStrategy;
    private boolean running;

    public Frame() {
        bufferStrategy = null;
        running = false;

        init();
    }

    public final void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        removeFocus(this);
        setFocusable(true);
    }

    public void runLogic() {

    }

    private void startLogicThread() {
        Thread t = new Thread(() -> {
            while (running) {
                runLogic();
                Utility.sleep(Configuration.getInstance().getLogicInterval());
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void drawGraphics(Graphics g) {

    }

    private void startGraphicsThread() {
        Thread t = new Thread(() -> {
            while (running) {
                if (Configuration.getInstance().isActiveRendering() && bufferStrategy != null) {
                    Graphics g = bufferStrategy.getDrawGraphics();
                    drawGraphics(g);
                    bufferStrategy.show();
                    g.dispose();
                } else {
                    repaint();
                }
                Utility.sleep(Configuration.getInstance().getDrawInterval());
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void fullscreen(DisplayMode displayMode) {
        setUndecorated(true);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphicsEnvironment.getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        device.setDisplayMode(displayMode);
        setResizable(false);
        device.getFullScreenWindow().createBufferStrategy(Configuration.getInstance().getBufferSize());
        bufferStrategy = device.getFullScreenWindow().getBufferStrategy();
        setIgnoreRepaint(true);
    }

    public void windowed() {
        setSize(Configuration.getInstance().getWidth(), Configuration.getInstance().getHeight());
        setResizable(false);
        setVisible(true);
        createBufferStrategy(Configuration.getInstance().getBufferSize());
        bufferStrategy = getBufferStrategy();
        setIgnoreRepaint(true);
    }

    public void fullscreen() {
        DisplayMode displayMode = new DisplayMode(
                Configuration.getInstance().getWidth(),
                Configuration.getInstance().getHeight(),
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getBitDepth(),
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate());
        fullscreen(displayMode);
    }

    public void start() {
        if (Configuration.getInstance().isFullscreen()) {
            fullscreen();
        } else {
            windowed();
        }

        if (!running) {
            running = true;
            startLogicThread();
            startGraphicsThread();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public final void removeFocus(Component comp) {
        comp.setFocusable(false);
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                removeFocus(child);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.consume();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedAction(e);
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleasedAction(e);
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovedAction(e);
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMovedAction(e);
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseMovedAction(e);
        e.consume();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseMovedAction(e);
        e.consume();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseWheelMovedAction(e);
        e.consume();
    }

    public abstract void mousePressedAction(MouseEvent e);

    public abstract void mouseReleasedAction(MouseEvent e);

    public abstract void mouseMovedAction(MouseEvent e);

    public abstract void mouseWheelMovedAction(MouseWheelEvent e);
}
