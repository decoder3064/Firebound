import java.io.File;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {

    public static void RunMusic(String song){

        try{
            AudioInputStream inputStream  = AudioSystem.getAudioInputStream(new File(song));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
        }
        catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(LineUnavailableException e){
            e.printStackTrace();
        }




    }
    
}
