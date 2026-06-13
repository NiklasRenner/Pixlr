package dk.renner.pixlr.game.config;

public class Configuration {

    private static final int DEFAULT_HEIGHT = 768;
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_LOGIC_INTERVAL = 15;
    private static final int DEFAULT_DRAW_INTERVAL = 16;
    private static final int DEFAULT_BUFFER_SIZE = 3;
    private static final boolean DEFAULT_ENABLE_ACTIVE_RENDERING = true;
    private static final boolean DEFAULT_ENABLE_FULLSCREEN = false;

    private final int height;
    private final int width;
    private final int logicInterval;
    private final int drawInterval;
    private final int bufferSize;
    private final boolean enableActiveRendering;
    private final boolean enableFullscreen;

    private static Configuration instance;

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    private Configuration() {
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
        logicInterval = DEFAULT_LOGIC_INTERVAL;
        drawInterval = DEFAULT_DRAW_INTERVAL;
        bufferSize = DEFAULT_BUFFER_SIZE;
        enableActiveRendering = DEFAULT_ENABLE_ACTIVE_RENDERING;
        enableFullscreen = DEFAULT_ENABLE_FULLSCREEN;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLogicInterval() {
        return logicInterval;
    }

    public int getDrawInterval() {
        return drawInterval;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public boolean isActiveRendering() {
        return enableActiveRendering;
    }

    public boolean isFullscreen() {
        return enableFullscreen;
    }
}
