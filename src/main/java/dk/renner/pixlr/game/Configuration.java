package dk.renner.pixlr.game;

/**
 *
 * @author NiklasRenner
 */
public class Configuration {
    
    private static final int DEFAULT_HEIGHT = 768;
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_LOGIC_INTERVAL = 15;
    private static final int DEFAULT_DRAW_INTERVAL = 5;
    private static final int DEFAULT_BIT_DEPTH = 32;
    private static final int DEFAULT_REFRESH_RATE = 60;
    private static final int DEFAULT_BUFFER_SIZE = 2;
    private static final boolean DEFAULT_ENABLE_ACTIVE_RENDERING = true;
    private static final boolean DEFAULT_ENABLE_FULLSCREEN = false;

    private final int height;
    private final int width;
    private final int logicInterval;
    private final int drawInterval;
    private final int bitDepth;
    private final int refreshRate;
    private final int bufferSize;
    private final boolean enableActiveRendering;
    private final boolean enableFullscreen;

    private static Configuration instance;

    public static Configuration getInstance(){
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }
    
    private Configuration(){
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
        logicInterval = DEFAULT_LOGIC_INTERVAL;
        drawInterval = DEFAULT_DRAW_INTERVAL;
        bitDepth = DEFAULT_BIT_DEPTH;
        refreshRate = DEFAULT_REFRESH_RATE;
        bufferSize = DEFAULT_BUFFER_SIZE;
        enableActiveRendering = DEFAULT_ENABLE_ACTIVE_RENDERING;
        enableFullscreen = DEFAULT_ENABLE_FULLSCREEN;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getLogicInterval(){
        return logicInterval;
    }
    
    public int getDrawInterval(){
        return drawInterval;
    }
    
    public int getBitDepth(){
        throw new UnsupportedOperationException("Not supported yet.");
        //return bitDepth;
    }
    
    public int getRefreshRate(){
        throw new UnsupportedOperationException("Not supported yet.");
        //return refreshRate;
    }
    
    public int getBufferSize(){
        return bufferSize;
    }
    
    public boolean isActiveRendering(){
        return enableActiveRendering;
    }
    
    public boolean isFullscreen(){
        return enableFullscreen;
    }
}
