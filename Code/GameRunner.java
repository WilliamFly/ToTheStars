/*GameRunner class
 * Author: William Mucha
 * Class Created: 12/20/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to hold the public static void main method to start and run the program
 * Import used in the GameRunner class*/
import javax.swing.JFrame;
//GameRunner Class
public class GameRunner{
  public static int gamemodeTracker=0;//Variable outside of the main method to keep track on which lobby to open
  public static void main(String[] args){//Main Method that runs the program
    int gamemodeType = 0;//Variable inside of the main method to keep track on which gamemode user wants to open
    boolean survivalMode=false;//boolean that is received from MainMenu class to determine if the user wants to open survival mode
    boolean storyMode=false;//boolean that is received from MainMenu class to determine if the user wants to open story mode
    boolean backMenu;//boolean that is received from either StoryMode or SurvivalMode class to determine if user wants to return to the main menu
    boolean repeat=true;//boolean used to repeat all the code to help change between gamemodes
    while(repeat){
      if(gamemodeType==0){ 
        repeat = false;//change repeat to false since in MainMenu lobby
        TextFile score = new TextFile();//Inizializing the TextFile class to retrieve scores file
        score.getTextFile();//retreiving scores file
        Music intro = new Music();//Inizializing the Music class to retrieve intro music
        intro.getIntroMusic();//retreiving intro music
        intro.playIntroMusic();//playing intro music
        MainMenu menuPanel = new MainMenu();//making a panel of class MainMenu
        JFrame menu = new JFrame();//creates a new JFrame used with the MainMenu panel
        menuPanel.getLobby(0);//retrieves the menu lobby from the MainMenu class
        menuPanel.getImages();//retrieves the images used from the MainMenu class 
        menuPanel.lobby(menu,menuPanel);//creates the lobby of MainMenu class type
        menuPanel.addMouseListener(menuPanel);//adds MouseListener to the frame 
        while(true){//Infinite while loop to run in the background while the MainMenu lobby is up
          try{
            survivalMode = menuPanel.survivalMode();//checks to see if user has requested to start survival mode
            storyMode = menuPanel.storyMode();//checks to see if user has requested to start story mode
            if(survivalMode==true){//if user has requested to start survival mode
              gamemodeType++;//add 1 to gamemodeType variable 
              gamemodeTracker=gamemodeType;//makes gamemodeTracker variable the same as gamemodeType
              intro.stopIntroMusic();//stops intro music
              menu.dispose();//closes the JFrame
              break;//exit while loop
            }
            if(storyMode==true){//if user has requested to start story mode
              gamemodeType+=2;//add 2 to gamemodeType variable 
              gamemodeTracker=gamemodeType;//makes gamemodeTracker variable the same as gamemodeType
              intro.stopIntroMusic();//stops intro music
              menu.dispose();//closes the JFrame
              break;//exit while loop
            }
            Thread.sleep(1000);//runs this code every second
          }catch(InterruptedException ex){}}
      }//end of MainMenu lobby
      if(gamemodeType==1){
        Music survivalMusic = new Music();//Inizializing the Music class to retrieve survival music
        survivalMusic.getSurvivalMusic();//retreiving survival music
        survivalMusic.playSurvivalMusic();//playing survival music
        SurvivalMode survivalPanel = new SurvivalMode();//making a panel of class SurvivalMode
        KeyPressing survival = new KeyPressing();//creates a new JFrame used with the SurvivalMode panel that had the ability for keys to be pressed from the KeyPressing class
        survival.setFocusable(true);//makes the component focusable for keypressing
        survival.add(new SurvivalMode());//appends the specified component to the end of this jframe
        survivalPanel.getImages();//retrieves the images used from the SurvivalMode class 
        survivalPanel.lobby(survival,survivalPanel);//creates the lobby of SurvivalMode class type
        survivalPanel.addMouseListener(survivalPanel);//adds MouseListener to the frame
        while(true){//Infinite while loop to run in the background while the SurvivalMode lobby is up
          try{
            backMenu = survivalPanel.backToMenu();//checks to see if user has requested to return to main menu
            if(backMenu==true){//if user has requested to return to main menu
              gamemodeType=0;//change gamemodeType variable back to 0
              gamemodeTracker=gamemodeType;//makes gamemodeTracker variable the same as gamemodeType
              survivalMusic.stopSurvivalMusic();//stops survival music
              survival.dispose();//closes the JFrame
              repeat = true;//repeats code to re-run the main menu
              break;//exit while loop
            }
            Thread.sleep(1000);//runs this code every second
          }catch(InterruptedException ex){}}
      }//end of SurvivalMode lobby
      if(gamemodeType==2){  
        StoryMode storyPanel = new StoryMode();//making a panel of class StoryMode
        KeyPressing story = new KeyPressing();//creates a new JFrame used with the StoryMode panel that had the ability for keys to be pressed from the KeyPressing class
        story.setFocusable(true);//makes the component focusable for keypressing
        story.add(new StoryMode());//appends the specified component to the end of this jframe
        storyPanel.getImages();//retrieves the images used from the StoryMode class 
        storyPanel.lobby(story,storyPanel);//creates the lobby of StoryMode class type
        storyPanel.addMouseListener(storyPanel);//adds MouseListener to the frame
        while(true){//Infinite while loop to run in the background while the StoryMode lobby is up
          try{
            backMenu = storyPanel.backToMenu();//checks to see if user has requested to return to main menu
            if(backMenu==true){//if user has requested to return to main menu
              gamemodeType=0;//change gamemodeType variable back to 0
              gamemodeTracker=gamemodeType;//makes gamemodeTracker variable the same as gamemodeType
              story.dispose();//closes the JFrame
              repeat = true;//repeats code to re-run the main menu
              break;//exit while loop
            }
            Thread.sleep(1000);//runs this code every second
          }catch(InterruptedException ex){}}
      }//end of StoryMode lobby  
    }//end of while loop repeat
  }//end of Main method
}//end of GameRunner class