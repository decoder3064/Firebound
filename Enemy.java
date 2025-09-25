public class Enemy extends Avoid {


    public static final String ENEMY_IMAGE_FILE = "game_assets/Enemy.gif";
    public static final int ENEMY_DEFAULT_SCROLL_SPEED = 1;


    private static int uniScrollSpeed = ENEMY_DEFAULT_SCROLL_SPEED;

    public Enemy(){
        this(0, 0);        
    }
    
    public Enemy(int x, int y){
        super(x, y, AVOID_WIDTH, AVOID_HEIGHT, ENEMY_IMAGE_FILE);  
    }

    public Enemy(int x, int y,int width, int height, String image){
        super(x, y, width, height, image);  
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
