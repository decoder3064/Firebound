//A RareGet is a rare kind of Get that spawns more infrequently than the regular Get
//When consumed, RareGets restores the Player's HP in addition to awarding points
//Otherwise, behaves the same as a regular Get
public class RareGet extends Get{
    
    //Location of image file to be drawn for a RareGet
    public static final String RARE_GET_IMAGE_FILE = "game_assets/rare_get.gif";

    public RareGet(){
        this(0, 0);        
    }
    
    public RareGet(int x, int y){
        super(x, y, RARE_GET_IMAGE_FILE);  
    }

    public RareGet(int x, int y, String imageFile){
        super(x, y, imageFile);  
    }

    
    
   @Override
   public int getDamage() {
       
       return 1;
   }

   public int getAmmo(){
    return 1; 
   }
   
}
