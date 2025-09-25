public class Target extends Avoid {

    public static final String TARGET_IMAGE_FILE = "game_assets/Targs.gif";

    public static final int TARGET_WIDTH = 75;
    public static final int TARGET_HEIGHT = 75;

    public static final int TARGET_DEFAULT_SCROLL_SPEED = 2;

    private int scrollSpeed = TARGET_DEFAULT_SCROLL_SPEED;

        
    public Target(){
        this(0, 0);        
    }
    
    public Target(int x, int y){
        super(x, y, TARGET_WIDTH, TARGET_HEIGHT, TARGET_IMAGE_FILE);  
    }

    
    public int getScrollSpeed(){
        return this.scrollSpeed;
    }
    
    //Sets the scroll speed to the argument amount
    public void setScrollSpeed(int newSpeed){
       this.scrollSpeed = newSpeed;
    }
    
}
