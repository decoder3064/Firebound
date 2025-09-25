public class Projectile extends Entity implements Scrollable {


    public static final String PROJ_IMAGE_FILE = "game_assets/Lazer.gif";


    public static final int PROJ_WIDTH = 35;
    public static final int PROJ_HEIGHT = 15;

    public static final int PROJ_DEFAULT_SCROLL_SPEED = 4;

    private int scrollSpeed = PROJ_DEFAULT_SCROLL_SPEED;

    public static int overScrollSpeed = PROJ_DEFAULT_SCROLL_SPEED;




    public Projectile(){
        this(0, 0);        
    }
    
    public Projectile(int x, int y){
        super(x, y, PROJ_WIDTH, PROJ_HEIGHT, PROJ_IMAGE_FILE);  
    }


    public int getScrollSpeed(){
        return this.scrollSpeed; 
    }
    
    //Sets the scroll speed to the argument amount
    public void setScrollSpeed(int newSpeed){
        this.scrollSpeed = newSpeed;
    }

    public static void setOverScrollSpeed(int newSpeed){
        overScrollSpeed = newSpeed;
    }
        
    //Move the scrollable by its respective scroll speed
    public void scroll(){
        setX(getX() + overScrollSpeed);
    }




    
}
