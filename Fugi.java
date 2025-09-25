public class Fugi extends Player{



    
    public static final String FUGI_IMAGE_FILE = "game_assets/real_flamefr.gif";
    public static final String FUGI_IMAGE_FILE2 = "game_assets/Flame2.gif";


    public static final String FUGI_IMAGE_DAMAGE = "game_assets/FlameDamage.gif";
    public static final String FUGI_IMAGE_DAMAGE2 = "game_assets/FlameDamage2.gif";


    public static final int FUGI_WIDTH = 30;
    public static final int FUGI_HEIGHT = 50;
    public static final int DEFAULT_MOVEMENT_SPEED = 7;

    public static final int STARTING_HP = 10;

    protected static final int INIT_AMMO = 0; 
    private int ammo = INIT_AMMO;

    private int TargsGot = 0; 



    
    public Fugi(){
       this(0, 0);        
    }
    
    public Fugi(int x, int y){
       super(x, y, FUGI_WIDTH, FUGI_HEIGHT, FUGI_IMAGE_FILE); 
       super.hp = STARTING_HP; 
    }
    

    public int getAmmo(){
        return this.ammo;
     }
  
     public void setAmmo(int newAmmo){
        this.ammo = newAmmo;
     }
  
     public int getTargs(){
        return this.TargsGot;
     }
  
     public void setTargs(int newScore){
        this.TargsGot = newScore;
     }
    
}
