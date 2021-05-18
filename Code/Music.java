/*Music class
 * Author: William Mucha
 * Class Created: 12/24/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to contain, play and stop all the music files of the program
 * Imports used in the Music class*/
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
//Music class
public class Music{ 
//Varibles
  File intro = new File("intro.wav");//sets the intro music to a File variable
  AudioInputStream introAudio;//creates an AudioInputStream for the intro music
  Clip introClip;//creates a Clip for the intro music
  File survival = new File("survival.wav");//sets the survival music to a File variable
  AudioInputStream survivalAudio;//creates an AudioInputStream for the survival music
  Clip survivalClip;//creates a Clip for the survival music
  File level1 = new File("level1.wav");//sets the level1 music to a File variable
  AudioInputStream level1Audio;//creates an AudioInputStream for the level1 music
  Clip level1Clip;//creates a Clip for the level1 music
  File level2 = new File("level2.wav");//sets the level2 music to a File variable
  AudioInputStream level2Audio;//creates an AudioInputStream for the level2 music
  Clip level2Clip;//creates a Clip for the level2 music
  File level3 = new File("level3.wav");//sets the level3 music to a File variable
  AudioInputStream level3Audio;//creates an AudioInputStream for the level3 music
  Clip level3Clip;//creates a Clip for the level3 music
  //Methods
  /*getIntroMusic method
   *Purpose: initializes the AudioInputStream and the Clip for the intro music*/
  public void getIntroMusic(){
    try{
      introAudio = AudioSystem.getAudioInputStream(intro);//gets AudioInputStream
      introClip = AudioSystem.getClip();//gets Clip
    } catch (Exception ex){}
  }//end of getIntroMusic method
  /*playIntroMusic method
   *Purpose: to play the intro music*/
  public void playIntroMusic(){
    try{
      introClip.open(introAudio);//play intro music
      introClip.loop(Clip.LOOP_CONTINUOUSLY);//puts it on a loop so it repeats itself once the song finishes
    } catch (Exception ex){}
  }//end of playIntroMusic method
  /*stopIntroMusic method
   *Purpose: to stop the intro music*/
  public void stopIntroMusic(){
    try{
      introClip.close();//stop intro music
    } catch (Exception ex){}
  }//end of stopIntroMusic method
  /*getSurvivalMusic method
   *Purpose: initializes the AudioInputStream and the Clip for the survival music*/
  public void getSurvivalMusic(){
    try{
      survivalAudio = AudioSystem.getAudioInputStream(survival);//gets AudioInputStream
      survivalClip = AudioSystem.getClip();//gets Clip
    } catch (Exception ex){}
  }//end of getSurvivalMusic method
  /*playSurvivalMusic method
   *Purpose: to play the survival music*/
  public void playSurvivalMusic(){
    try{
      survivalClip.open(survivalAudio);//play survival music
      survivalClip.loop(Clip.LOOP_CONTINUOUSLY);//puts it on a loop so it repeats itself once the song finishes
    } catch (Exception ex){}
  }//end of playSurvivalMusic method
  /*stopSurvivalMusic method
   *Purpose: to stop the survival music*/
  public void stopSurvivalMusic(){
    try{
      survivalClip.close();//stop survival music
    } catch (Exception ex){}
  }//end of stopSurvivalMusic method
  /*getLevel1Music method
   *Purpose: initializes the AudioInputStream and the Clip for the level1 music*/
  public void getLevel1Music(){
    try{
      level1Audio = AudioSystem.getAudioInputStream(level1);//gets AudioInputStream
      level1Clip = AudioSystem.getClip();//gets Clip
    } catch (Exception ex){}
  }//end of getLevel1Music method
  /*playLevel1Music method
   *Purpose: to play the level1 music*/
  public void playLevel1Music(){
    try{
      level1Clip.open(level1Audio);//play level1 music
      level1Clip.loop(Clip.LOOP_CONTINUOUSLY);//puts it on a loop so it repeats itself once the song finishes
    } catch (Exception ex){}
  }//end of playLevel1Music method
  /*stopLevel1Music method
   *Purpose: to stop the level1 music*/
  public void stopLevel1Music(){
    try{
      level1Clip.close();//stop level1 music
    } catch (Exception ex){}
  }//end of stopLevel1Music method
  /*getLevel2Music method
   *Purpose: initializes the AudioInputStream and the Clip for the level2 music*/
  public void getLevel2Music(){
    try{
      level2Audio = AudioSystem.getAudioInputStream(level2);//gets AudioInputStream
      level2Clip = AudioSystem.getClip();//gets Clip
    } catch (Exception ex){}
  }//end of getLevel2Music method
  /*playLevel2Music method
   *Purpose: to play the level2 music*/
  public void playLevel2Music(){
    try{
      level2Clip.open(level2Audio);//play level2 music
      level2Clip.loop(Clip.LOOP_CONTINUOUSLY);//puts it on a loop so it repeats itself once the song finishes
    } catch (Exception ex){}
  }//end of playLevel2Music
  /*stopLevel2Music method
   *Purpose: to stop the level2 music*/
  public void stopLevel2Music(){
    try{
      level2Clip.close();//stop level2 music
    } catch (Exception ex){}
  }//end of stopLevel2Music
  /*getLevel3Music method
   *Purpose: initializes the AudioInputStream and the Clip for the level3 music*/
  public void getLevel3Music(){
    try{
      level3Audio = AudioSystem.getAudioInputStream(level3);//gets AudioInputStream
      level3Clip = AudioSystem.getClip();//gets Clip
    } catch (Exception ex){}
  }//end of getLevel3Music
  /*playLevel3Music method
   *Purpose: to play the level3 music*/
  public void playLevel3Music(){
    try{
      level3Clip.open(level3Audio);//play level3 music
      level3Clip.loop(Clip.LOOP_CONTINUOUSLY);//puts it on a loop so it repeats itself once the song finishes
    } catch (Exception ex){}
  }//end of playLevel3Music
  /*stopLevel3Music method
   *Purpose: to stop the level3 music*/
  public void stopLevel3Music(){
    try{
      level3Clip.close();//stop level3 music
    } catch (Exception ex){}
  }//end of stopLevel3Music
}//end of Music class