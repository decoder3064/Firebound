public class Log extends Get {


    public static final String LOG_IMAGE_FILE = "game_assets/LOG.gif";
    public static final int LOG_DEFAULT_SCROLL_SPEED = 1;


    private static int uniScrollSpeed = LOG_DEFAULT_SCROLL_SPEED;

    public Log(){
        this(0, 0);        
    }
    
    public Log(int x, int y){
        super(x, y, GET_WIDTH, GET_HEIGHT, LOG_IMAGE_FILE);  
    }
    
    public Log(int x, int y, String imageFileName){
        super(x, y, GET_WIDTH, GET_HEIGHT, imageFileName);
    }


    public static int getUnivScrollSpeed(){
        return uniScrollSpeed;
    }

    public static void setUnivScrollSpeed(int newSpeed){
        uniScrollSpeed = newSpeed;
     }

     @Override
     public void scroll(){
        setX(getX() - uniScrollSpeed);
    }
    
}
