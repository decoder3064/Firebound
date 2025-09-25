import java.awt.*;
import java.awt.event.*;
import java.util.*;



//A Simple version of the scrolling game, featuring Avoids, Gets, and RareGets
//Players must reach a score threshold to win
//If player runs out of HP (via too many Avoid collisions) they lose
public class ScrollingGame extends GameEngine {
    
    
    //Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 0;
    protected static final int STARTING_PLAYER_Y = 400;
    
    //Score needed to win the game
    protected static final int SCORE_TO_WIN = 300;
    
    //Maximum that the game speed can be increased to
    //(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;
    //Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE_INTERVAL = 20;    
    
    public static final String INTRO_SPLASH_FILE = "game_assets/splash.gif";  
    

    //Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;
    
    //Interval that Entities get spawned in the game window
    //ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 45;

    protected static final int RAREGET_SPAWN_INTERVAL = 250; 

    
    //A Random object for all your random number generation needs!
    public static final Random rand = new Random();

    
    //Player's current score
    protected int score;

    
    //Stores a reference to game's Player object for quick reference
    //(This Player will also be in the displayList)
    protected Player player;

    
    
    public ScrollingGame(){
        super();
    }
    
    public ScrollingGame(int gameWidth, int gameHeight){ 
        super(gameWidth, gameHeight);
    }
    
    
    //Performs all of the initialization operations that need to be done before the game starts
    protected void pregame(){
        this.setBackgroundColor(Color.BLACK);
        this.setSplashImage(INTRO_SPLASH_FILE);
        player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        displayList.add(player); 
        score = 0;
    }
    
    //Called on each game tick
    protected void updateGame(){
        //scroll all scrollable Entities on the game board
        scrollEntities();  
        //Spawn new entities only at a certain interval
        if (super.getTicksElapsed() % SPAWN_INTERVAL == 0){
            spawnEntities();
        }
        if(super.getTicksElapsed() % (RAREGET_SPAWN_INTERVAL) == 0){
            handleOverlap(spawnRareGet());
        }
        for(Entity ent : displayList){
            if(ent instanceof Consumable){
                handlePlayerCollision((Consumable)ent);
            }

        }

        //Update the title text on the top of the window
        setTitleText("HP: " + player.getHP() + ",Score: " + score); 
    }
    
    
    //Scroll all scrollable entities per their respective scroll speeds
    protected void scrollEntities(){
        for(Entity ent : displayList){
            if (ent instanceof Scrollable)
                ((Scrollable)ent).scroll();
            if(ent.getX()< -100)
                toBeGC.add(ent); 
        }
    }
    
    
    
    //Called whenever it has been determined that the Player collided with a consumable
    protected void handlePlayerCollision(Consumable collidedWith){
        if(player.isCollidingWith((Entity)collidedWith)){
            player.setHP(player.getHP() + collidedWith.getDamage());
            score += collidedWith.getPoints();
            toBeGC.add((Entity)collidedWith); 
        }
    }

    //Spawn new Entities on the right edge of the game board
    protected void spawnEntities(){
        for(int i =0; i<rand.nextInt(4);i++){
            handleOverlap(spawnAvoid());
            handleOverlap(spawnGet());
        }
    }

    protected void handleOverlap(Entity over){
        if(findCollisions((over)).size() != 0 )
             toBeGC.add(over);
   }


    protected Entity spawnAvoid(){
        int x = getWindowWidth();
        int y = genRandY();
        Avoid nextAvoid = new Avoid(x,y);
        displayList.add(nextAvoid); 
        return nextAvoid; 

   }

    protected Entity spawnGet(){
        int x = getWindowWidth();
        int y = genRandY();
        Get nextGet = new Get(x,y);
        displayList.add(nextGet);
        return nextGet;

   }


    protected Entity spawnRareGet(){
        int x = getWindowWidth();
        int y = genRandY();
        RareGet nextRareGet = new RareGet(x,y);
        displayList.add(nextRareGet);
        return nextRareGet;
   }


   

    protected int genRandY(){
        int randY = rand.nextInt(super.getWindowHeight()-75);
        return randY;
    }




    //Called once the game is over, performs any end-of-game operations
    protected void postgame(){
        if(player.getHP() == 0)
            super.setTitleText("GAME OVER - You Loose!");
        else 
            super.setTitleText("GAME OVER - You Won!");
        
    }
    
    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
    protected boolean isGameOver(){
        if(score == SCORE_TO_WIN)
            return true; 
        else if(player.getHP() == 0) 
            return true;
        return false;   
       
    }
    
    //Reacts to a single key press on the keyboard
    protected void reactToKey(int key){
        //if a splash screen is up, only react to the advance splash key
        if (getSplashImage() != null){
            if (key == ADVANCE_SPLASH_KEY)
                super.setSplashImage(null);
        }
        else if (key == KEY_PAUSE_GAME)
            pauseGame(super.isPaused);
        else if(!isPaused) 
            playerMovement(key);
        if(key == SPEED_UP_KEY && getGameSpeed() < MAX_GAME_SPEED)
            setGameSpeed(getGameSpeed()+ SPEED_CHANGE_INTERVAL);
        else if(key == SPEED_DOWN_KEY && getGameSpeed() > SPEED_CHANGE_INTERVAL)
            setGameSpeed(getGameSpeed()- SPEED_CHANGE_INTERVAL);
    }  

    public void playerMovement(int key){

        if(key == UP_KEY && player.getY() > 0 )
                player.setY(player.getY()- player.getMovementSpeed());
            else if(key == DOWN_KEY && player.getY() < super.getWindowHeight()-(Player.PLAYER_HEIGHT+ player.getMovementSpeed()))
                player.setY(player.getY()+ player.getMovementSpeed());
            else if(key == LEFT_KEY && player.getX() > 0)
                player.setX(player.getX()-player.getMovementSpeed());
            else if(key == RIGHT_KEY && player.getX() < super.getWindowWidth()- (Player.PLAYER_WIDTH+ player.getMovementSpeed()))
                player.setX(player.getX()+player.getMovementSpeed());

    }


    protected void pauseGame(boolean isPaused){
        if (isPaused)
            super.isPaused = false;
        else 
            super.isPaused =true;
    }
  
    
    
    //Handles reacting to a single mouse click in the game window
    protected MouseEvent reactToMouseClick(MouseEvent click){
       
        //Mouse functionality is not used at all in the Simple game...
        //you may want to override this function for a CreativeGame feature though!

        return click;//returns the mouse event for any child classes overriding this method
    }    
        
}
