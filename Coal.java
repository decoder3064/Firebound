public class Coal extends RareGet{


    public static final String COAL_IMAGE_FILE = "game_assets/coal.gif";
    public static final int COAL_DEFAULT_SCROLL_SPEED = 1;


    private static int uniScrollSpeed = COAL_DEFAULT_SCROLL_SPEED;

    public Coal(){
        this(0, 0);        
    }
    
    public Coal(int x, int y){
        super(x, y, COAL_IMAGE_FILE);  
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
