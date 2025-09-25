import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class FireBound extends ScrollingGame{



    public static final String BACKGROUND_IMAGE = "game_assets/dungeon.gif";
    public static final String MUSIC = "sound_effects/intro.wav";
    public static final String SPLASH_IMAGE = "game_assets/FireBoundSplash.gif";;
    
    // Initialize Player
    
    public static final int SPEED_UP_INTERVAL = 1000;
    public static final int INC_CONS_SPEED = 1;
    public static final int INIT_SPEED = 60;
    public static final int GAME_SPEED_CHECK_VAL= 240;

    public static final int TARGET_SPAWN_INTERVAL = 1000;

    protected static final int SCORE_TO_WIN = 2000;
    protected static final int TARGET_SCORE_TO_WIN = 4;
    protected static final int GROWTH_TO_WIN = 100;
    protected static final int GROWTH_CHANGE = 40;

    protected static final int CHANGE_INTERVAL = 20;




    private int gu= 0; 


    protected Set<Projectile> shootSet = new HashSet<Projectile>(); 

    public FireBound(){
        super();
    }
    
    public FireBound(int gameWidth, int gameHeight){ 
        super(gameWidth, gameHeight);
    }

    @Override
    protected void pregame(){
        this.setBackgroundColor(Color.black);
        this.setBackgroundImage(BACKGROUND_IMAGE);
        this.setSplashImage(SPLASH_IMAGE);
        player = new Fugi(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        PlayMusic.RunMusic(MUSIC);
        displayList.add(player); 
        score = 0;
    }


    protected void incUnivSpeed(){
        Enemy.setUnivScrollSpeed(Enemy.getUnivScrollSpeed()+1);
        Log.setUnivScrollSpeed(Log.getUnivScrollSpeed()+1);
        Coal.setUnivScrollSpeed(Coal.getUnivScrollSpeed()+1);
    }




    @Override
    protected void updateGame(){
        //scroll all scrollable Entities on the game board
        scrollEntities();  
        //Spawn new entities only at a certain interval
        if (super.getTicksElapsed() % SPAWN_INTERVAL == 0){
            spawnEntities();
        }
        if(super.getTicksElapsed() % (RAREGET_SPAWN_INTERVAL) == 0){
            handleOverlap(this.spawnRareGet());
        }

        if(super.getTicksElapsed() % (TARGET_SPAWN_INTERVAL) == 0){  
            handleOverlap(spawnTarget());
        }
        if(super.getTicksElapsed() % (SPEED_UP_INTERVAL) == 0){  
            incUnivSpeed();
        } 
        
        if(gu >= GROWTH_CHANGE && player.getImageName() !=Fugi.FUGI_IMAGE_DAMAGE2){
            ((Entity)player).setImageName(Fugi.FUGI_IMAGE_FILE2);
        }
        else if(gu < GROWTH_CHANGE && player.getImageName() !=Fugi.FUGI_IMAGE_DAMAGE){
            ((Entity)player).setImageName(Fugi.FUGI_IMAGE_FILE);
        }
        if(super.getTicksElapsed() % CHANGE_INTERVAL == 0 && player.getImageName() == Fugi.FUGI_IMAGE_DAMAGE)
            player.setImageName(Fugi.FUGI_IMAGE_FILE);
        if(super.getTicksElapsed() % CHANGE_INTERVAL== 0 && player.getImageName() == Fugi.FUGI_IMAGE_DAMAGE2)
            player.setImageName(Fugi.FUGI_IMAGE_FILE2);

        for(Entity ent : displayList){
            if(ent instanceof Consumable){
                handlePlayerCollision((Consumable)ent);
                handleProjectileCollision((Entity)ent);
            }

        }
        //Update the title text on the top of the window
        setTitleText("HP: " + player.getHP() + " | Ammo: " + ((Fugi)player).getAmmo()+ " | Score: " + score + " | Growth Units: " + gu + " | Targets Shooten: " + ((Fugi)player).getTargs()  ); 
    }



    protected Entity spawnTarget(){
        int x = getWindowWidth();
        int y = genRandY();
        Target target = new Target(x,y);
        displayList.add(target);
        return target;

    }

    //Handleing overlaps 
    protected void handleProjectileCollision(Entity collidedWith){
        for(Projectile shoot : shootSet)
            if (shoot == null)
                 return;
            else if(collidedWith instanceof Target && shoot.isCollidingWith(collidedWith)){
                ((Fugi)player).setTargs(((Fugi)player).getTargs()+1); 
                toBeGC.add(collidedWith);
                
            }
            else if(collidedWith instanceof Avoid && shoot.isCollidingWith(collidedWith)){
                toBeGC.add(collidedWith);
            }
            
    }

    @Override
    protected void handlePlayerCollision(Consumable collidedWith) {
        if(player.isCollidingWith((Entity)collidedWith)){
            player.setHP(player.getHP() + collidedWith.getDamage());
            score += collidedWith.getPoints();
            changeSkin(collidedWith); 
            checkRareGetCol(collidedWith);
            checkGetCol(collidedWith);
            checkAvoidCol(collidedWith);
            toBeGC.add((Entity)collidedWith); 
        
        }
    }

   
    @Override
    protected void spawnEntities() {
        for(int i =0; i<rand.nextInt(4);i++){
            handleOverlap(this.spawnAvoid());
            handleOverlap(this.spawnGet());
        }
    }

    @Override
    protected Entity spawnAvoid() {
        int x = getWindowWidth();
        int y = genRandY();
        Enemy nextEnemy = new Enemy(x,y);
        displayList.add(nextEnemy); 
        return nextEnemy; 
    }

    @Override
    protected Entity spawnGet() {
        int x = getWindowWidth();
        int y = genRandY();
        Log nextLog = new Log(x,y);
        displayList.add(nextLog); 
        return nextLog;
    }

    @Override
    protected Entity spawnRareGet(){
        int x = getWindowWidth();
        int y = genRandY();
        Coal nextCoal = new Coal(x,y);
        displayList.add(nextCoal); 
        return nextCoal;



    }


    protected void checkAvoidCol(Consumable collidedWith){
        if (collidedWith instanceof Avoid && (player.getHeight()>50 && player.getWidth()>30)){
            player.setHeight(player.getHeight()-5);
            player.setWidth(player.getWidth()-5);
            gu-=2; 
        }
    }

    protected void checkRareGetCol(Consumable collidedWith){
        if (collidedWith instanceof RareGet && gu<100){
            ((Fugi)player).setAmmo(((Fugi)player).getAmmo()+3);
            player.setHeight(player.getHeight()+5);
            player.setWidth(player.getWidth()+5);
            gu+=2; 
        }
        else if (collidedWith instanceof RareGet)
            ((Fugi)player).setAmmo(((Fugi)player).getAmmo()+3);
    }

    protected void checkGetCol(Consumable collidedWith){
        if (collidedWith instanceof Get && gu<100){
            ((Fugi)player).setAmmo(((Fugi)player).getAmmo()+1);
            player.setHeight(player.getHeight()+1);
            player.setWidth(player.getWidth()+1);
            gu++;
        }
        else if (collidedWith instanceof Get)
            ((Fugi)player).setAmmo(((Fugi)player).getAmmo()+1);
    }



    protected void changeSkin(Consumable collidedWith){
        if(collidedWith instanceof Avoid && player.getImageName() == Fugi.FUGI_IMAGE_FILE)
                player.setImageName(Fugi.FUGI_IMAGE_DAMAGE);
        if(collidedWith instanceof Avoid && player.getImageName() == Fugi.FUGI_IMAGE_FILE2)
            player.setImageName(Fugi.FUGI_IMAGE_DAMAGE2);
    }
 

    @Override
    public void playerMovement(int key){

        if(key == UP_KEY && player.getY() > 0 )
            player.setY(player.getY()- player.getMovementSpeed());
        else if(key == DOWN_KEY && player.getY() < super.getWindowHeight()-(Fugi.FUGI_HEIGHT+ player.getMovementSpeed()))
            player.setY(player.getY()+ player.getMovementSpeed());
        else if(key == LEFT_KEY && player.getX() > 0)
            player.setX(player.getX()-player.getMovementSpeed());
        else if(key == RIGHT_KEY && player.getX() < super.getWindowWidth()- (Fugi.FUGI_WIDTH+ player.getMovementSpeed()))
            player.setX(player.getX()+player.getMovementSpeed());
        else if(key == KEY_SHOOT && ((Fugi)player).getAmmo() > 0){
            Projectile shoot = new Projectile(player.getX(), player.getY());
            this.shootSet.add(shoot);
            displayList.add(shoot);
            ((Fugi)player).setAmmo(((Fugi)player).getAmmo()-1);
            }
    }

    @Override
    protected boolean isGameOver() {
    if(score >= SCORE_TO_WIN && ((Fugi)player).getTargs() >= TARGET_SCORE_TO_WIN && gu >= GROWTH_TO_WIN)
        return true; 
    else if(player.getHP() == 0) 
        return true;
    return false;   

    }

    @Override
    protected void scrollEntities() {
        for(Entity ent : displayList){
            if (ent instanceof Scrollable)
                ((Scrollable)ent).scroll();
             if(ent.getX()< -100)
                toBeGC.add(ent); 

             if((ent instanceof Projectile && ent.getX() > getWindowWidth())){
               toBeGC.add(ent);
               shootSet.remove(ent);
            }
    


        }
        
    }

   



    
}
