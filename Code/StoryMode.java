/*StoryMode class
 * Author: William Mucha
 * Class Created: 12/27/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to run all the necessary code to run the story mode portion of the game
 * Import used in the StoryMode class*/
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
//StoryMode class which is a child class to the Lobbies class and that also implements the MouseListener interface
public class StoryMode extends Lobbies  implements MouseListener{
//Variables
  Image background1;//background for level 1
  Image background2;//background for level 2
  Image background3;//background for level 3
  Image level1;//leve 1 title
  Image level2;//level 2 title
  Image level3;//level 3 title
  Image startlevel;//start level button
  Image pause;//pause button 
  Image paused;//pause title
  Image continued;//continue button
  Image restart;//restart button
  Image quit;//quit button
  Image gameover;//gamemover title
  Image restartlevel;//restart level button
  Image levelcomplete;//level complete title
  Image level1line1;//lines of text
  Image level1line2;
  Image level1line3;
  Image level1line4;
  Image level1line5;
  Image level2line1;
  Image level2line2;
  Image level2line3;
  Image level2line4;
  Image level2line5;
  Image level2line6;
  Image level3line1;
  Image level3line2;
  Image level3line3;
  Image level3line4;
  Image level3line5;
  Image level3line6;
  Image level3line7;
  Image congrats;//congrats title
  Image savedHumanity;//text
  Image thanks;//ty
  Image madeBy;//William Mucha
  Image musics;//music title
  Image next;//next button
  Image end;//end button
  //Initialization of Spaceship object
  Spaceship spaceship = new Spaceship();
  //settting spaceship coordinates to variables in this class
  int spaceshipY=spaceship.getY1();
  int spaceshipY2=spaceship.getY2();
  int spaceshipX=spaceship.getX1();
  int spaceshipX2=spaceship.getX2();
  //Initialization of Spaceship Animations 
  BufferedImage[] slow = {Spaceship.getSprite(0, 0),Spaceship.getSprite(0, 1)};
  BufferedImage[] fast = {Spaceship.getSprite(0, 0),Spaceship.getSprite(0, 1)};
  BufferedImage[] dead = {Spaceship.getSprite(0, 2)};
  Animation slowAnim = new Animation(slow, 40);
  Animation fastAnim = new Animation(fast, 6);
  Animation deadAnim = new Animation(dead, 1);
  Animation animation = slowAnim;   
  //Initialization of Alien Animations 
  BufferedImage[] alienAlive = {Alien.getSprite(0, 0),Alien.getSprite(0, 1)};
  BufferedImage[] alienDone = {Alien.getSprite(0, 2)};
  Animation alienMove = new Animation(alienAlive,20);
  Animation alienDead = new Animation(alienDone,1);
  Animation alienAnim[] = new Animation[3];
  Alien alien[] = new Alien[3];//making an array of Alien object
  //Initialization of GravityUp Animations 
  BufferedImage[] gravityMoveUP = {GravityUp.getSprite(0, 0),GravityUp.getSprite(0, 1),GravityUp.getSprite(0, 2)};
  Animation gravityUP = new Animation(gravityMoveUP,20);
  Animation gravityUp = gravityUP;
  GravityUp gravityU[] = new GravityUp[2];//making an array of GravityUp object
  //Initialization of GravityDown Animations 
  BufferedImage[] gravityMoveDOWN = {GravityDown.getSprite(0, 0),GravityDown.getSprite(0, 1),GravityDown.getSprite(0, 2)};
  Animation gravityDOWN = new Animation(gravityMoveDOWN,20);
  Animation gravityDown = gravityDOWN;
  GravityDown gravityD[] = new GravityDown[2];//making an array of GravityDown object
  AsteroidSmall small[] = new AsteroidSmall[10];//making an array of AsteroidSmall object
  AsteroidMed med[] = new AsteroidMed[5];//making an array of AsteroidMed object
  AsteroidLarge large[] = new AsteroidLarge[3];//making an array of AsteroidLarge object
  AsteroidHuge huge[] = new AsteroidHuge[2];//making an array of AsteroidHuge object
  TextFile textfile = new TextFile();//making textfile accessible in this class
  int level=1;//keeps track of levels
  int wave=-1;//keeps track of waves
  boolean fillArr = true;//boolean that fills the all arrays of objects once
  int levelcompleteX = 1400;//int that keeps track of the x position of the level complete image
  boolean gamePause=false;//boolean that changes depending if the game is paused or not
  boolean gameOver=false;//boolean that changes depending if the game is over or not
  boolean returnMenu=false;//boolean that changes if the user would like to return to the main menu
  //Music objects that run music while story mode is active
  Music level1Music = new Music();//level 1 music 
  Music level2Music = new Music();//level 2 music 
  Music level3Music = new Music();//level 3 music 
//Constructor
  public StoryMode() {
    Thread animationThread = new Thread(new Runnable() {//creates a new thread that runs in the background 
      public void run() {
        while(fillArr){//runs in the beginning once to fill arrays
          level1Music.getLevel1Music();//get the music files for level 1
          level2Music.getLevel2Music();//get the music files for level 2
          level3Music.getLevel3Music();//get the music files for level 3
          for(int i=0;i<small.length;i++){//fills the coordinates for all the AsteroidSmall objects
            int asx = 1400; 
            int asy = (i*76);
            small[i] = new AsteroidSmall(asx,asy);
          }
          for(int i=0;i<med.length;i++){//fills the coordinates for all the AsteroidMed objects
            int asx = 1400; 
            int asy = (i*159);
            med[i] = new AsteroidMed(asx,asy);
          }
          for(int i=0;i<large.length;i++){//fills the coordinates for all the AsteroidLarge objects
            int asx = 1400; 
            int asy = (i*140);
            large[i] = new AsteroidLarge(asx,asy);
          }
          for(int i=0;i<huge.length;i++){//fills the coordinates for all the AsteroidHuge objects
            int asx = 1400; 
            int asy = (i*100);
            huge[i] = new AsteroidHuge(asx,asy);
          }
          for(int i=0;i<alien.length;i++){//fills the coordinates for all the Alien objects
            int alx = 1400;
            int aly = 100+(i*200);
            alien[i] = new Alien(alx,aly);
            alienAnim[i] = alienMove;
          }
          for(int i=0;i<gravityD.length;i++){//fills the coordinates for all the GravityDown objects
            int gdx = 1400+(i*800);
            int gdy = 0;
            gravityD[i] = new GravityDown(gdx,gdy);
          }
          for(int i=0;i<gravityU.length;i++){//fills the coordinates for all the GravityUp objects
            int gux = 1400+(i*800);
            int guy = 0;
            gravityU[i] = new GravityUp(gux,guy);
          }
          fillArr=false;//makes fillArr false so it only runs once
        }
        while (true) {//runs all the time while the game is running
          boolean up = KeyPressing.goingUp;//boolean that checks and see if the up key was pressed
          boolean down = KeyPressing.goingDown;//boolean that checks and see if the down key was pressed
          if(spaceship.canMove()){//if the spaceship is allowed to move
            if(up && spaceshipY>=0){//if the up key is pressed and it is not off the screen
              //move y coordinates up
              spaceshipY-=5;
              spaceshipY2=(spaceshipY+80);
              animation = fastAnim;//speed up animation
            }else if(down && spaceshipY2<=760){//if the down key is pressed and it is not off the screen
              //move y coordinates down
              spaceshipY+=5;
              spaceshipY2=(spaceshipY+80);
              animation = fastAnim;//speed up animation
            } else{
              animation = slowAnim;//otherwise keep animation at regular speed
            }
          }//end of checking if the spaceship is allowed to move
          //Collision detection for AsteroidSmall
          for(int i=0;i<small.length;i++){//for all small asteroids
            if(small[i].getX1()<=spaceshipX2 && small[i].getX2()>= spaceshipX && small[i].getY1()<=spaceshipY2 && small[i].getY2()>= spaceshipY){//if asteroid hits
              gameOver = true;//make the gameOver boolean true
              animation = deadAnim;//change sprite to dead animation
              spaceship.getMove(false);//make the spaceship not able to move
            } 
          }
          //Collision detection for AsteroidMed
          for(int i=0;i<med.length;i++){
            if(med[i].getX1()<=spaceshipX2 && med[i].getX2()>= spaceshipX && med[i].getY1()<=spaceshipY2 && med[i].getY2()>= spaceshipY){//if asteroid hits
              gameOver = true;//make the gameOver boolean true
              animation = deadAnim;//change sprite to dead animation
              spaceship.getMove(false);//make the spaceship not able to move
            } 
          }
          //Collision detection for AsteroidLarge
          for(int i=0;i<large.length;i++){
            if(large[i].getX1()<=spaceshipX2 && large[i].getX2()>= spaceshipX && large[i].getY1()<=spaceshipY2 && large[i].getY2()>= spaceshipY){//if asteroid hits
              gameOver = true;//make the gameOver boolean true
              animation = deadAnim;//change sprite to dead animation
              spaceship.getMove(false);//make the spaceship not able to move
            } 
          }
          //Collision detection for AsteroidHuge
          for(int i=0;i<huge.length;i++){
            if(huge[i].getX1()<=spaceshipX2 && huge[i].getX2()>= spaceshipX && huge[i].getY1()<=spaceshipY2 && huge[i].getY2()>= spaceshipY){//if asteroid hits
              gameOver = true;//make the gameOver boolean true
              animation = deadAnim;//change sprite to dead animation
              spaceship.getMove(false);//make the spaceship not able to move
            } 
          }
          //Collision detection for Alien
          for(int i=0;i<alien.length;i++){
            if(alien[i].getX1()<=spaceshipX2 && alien[i].getX1()+5>= spaceshipX2 && alien[i].getY1()<=spaceshipY2 && alien[i].getY2()>= spaceshipY){//if alien hits head on
              gameOver = true;//make the gameOver boolean true
              animation = deadAnim;//change sprite to dead animation
              spaceship.getMove(false);//make the spaceship not able to move
            } 
            if(alien[i].getX1()+5<=spaceshipX2 && alien[i].getX2()>= spaceshipX && alien[i].getY1()<=spaceshipY2 && alien[i].getY2()>= spaceshipY){//if alien is hit from the side
              alienAnim[i] = alienDead;//change alien sprite to dead
            }
          }
          //Collision detection for GravityDown
          for(int i=0;i<gravityD.length;i++){
            if(gravityD[i].getX1()<=spaceshipX2 && gravityD[i].getX2()>= spaceshipX && gravityD[i].getY1()<=spaceshipY2 && gravityD[i].getY2()>= spaceshipY && spaceshipY2<=760 && gamePause==false && gameOver==false){//if spaceship is in gravity field
              //move the player down
              spaceshipY+=4;
              spaceshipY2=(spaceshipY+80);
            } 
          }
          //Collision detection for GravityUp
          for(int i=0;i<gravityU.length;i++){
            if(gravityU[i].getX1()<=spaceshipX2 && gravityU[i].getX2()>= spaceshipX && gravityU[i].getY1()<=spaceshipY2 && gravityU[i].getY2()>= spaceshipY && spaceshipY>=0 && gamePause==false && gameOver==false){//if spaceship is in gravity field
              //move the player up
              spaceshipY-=4;
              spaceshipY2=(spaceshipY+80);
            } 
          }
          repaint();//repaint the frame
          try {Thread.sleep(10);/*Check every 10 milliseconds*/} catch (Exception ex) {}
        }
      }//end of run method
    });   
    animationThread.start();//restart the thread
  }//end of constructor
//Methods
  /*mouseClicked method
   *Purpose: used to check and see if the user has clicked the mouse on a certain area on the screen*/
  public void mouseClicked(MouseEvent e) {
    int x=e.getX();//x coordinate of mouse on screen
    int y=e.getY();//y coordinate of mouse on screen
    if(wave==-1){//if wave is the level start screen
      if(x >= ((WIDTH/2)-(541/2)) && x<= (WIDTH/2)+(541/2) && y>= (HEIGHT-100) && y<= (HEIGHT-21)){//if the start level button is pressed
        //play music depending on which level user is on
        if(level==1) level1Music.playLevel1Music();
        if(level==2) level2Music.playLevel2Music();
        if(level==3) level3Music.playLevel3Music();
        wave=0;//start the first wave of level
      }
    }
    if(wave!=-1 && wave!=11){//if wave is not the level start screen or the level complete screen
      if(x >= (WIDTH-261) && x<= WIDTH && y>= 0 && y<= 78 && gameOver!=true){//if pause button is pressed
        gamePause = true;//pause game
        spaceship.getMove(false);//make spaceship unable to move
        repaint();//repaint the frame
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(gamePause){
        if(x >= ((WIDTH/2)-(371/2)) && x<= ((WIDTH/2)+(371/2)) && y>= 300 && y<= 378){//if continue button is pressed
          gamePause = false;//unpause the game
          spaceship.getMove(true);//make spaceship moveable
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(319/2)) && x<= ((WIDTH/2)+(319/2)) && y>= 400 && y<= 472){//if restart button is pressed
          gamePause = false;//unpause the game
          spaceship.getMove(true);//make spaceship moveable
          wave=0;//restart game by going to first wave
          //reset spaceship coordinates
          spaceshipX=spaceship.getX1();
          spaceshipX2=spaceship.getX2();
          spaceshipY=spaceship.getY1();
          spaceshipY2=spaceship.getY2();
          for(int i=0;i<small.length;i++){//resetting AsteroidSmall x coordinate
            small[i].setX(1400);
          }
          for(int i=0;i<med.length;i++){//resetting AsteroidMed x coordinate
            med[i].setX(1400);
          }
          for(int i=0;i<large.length;i++){//resetting AsteroidLarge x coordinate
            large[i].setX(1400);
          }
          for(int i=0;i<huge.length;i++){//resetting AsteroidHuge x coordinate
            huge[i].setX(1400);
          }
          for(int i=0;i<alien.length;i++){//resetting Alien x and y coordinates and animations
            alien[i].setX(1400);
            alien[i].setY(i);
            alienAnim[i] = alienMove;
          }   
          //resetting GravityUp x coordinate
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          //resetting GravityDown x coordinate
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(168/2)) && x<= ((WIDTH/2)+(168/2)) && y>= 500 && y<= 576){//if quit button is pressed
          returnMenu = true;//return to main menu
          //Stop music depending on level
          if(level==1) level1Music.stopLevel1Music();
          if(level==2) level2Music.stopLevel2Music();
          if(level==3) level3Music.stopLevel3Music();
        }
      }
      if(gameOver){//if the game is over
        if(x >= ((WIDTH/2)-(642/2)) && x<= ((WIDTH/2)+(642/2)) && y>= 400 && y<= 479){//if level restart button is pressed
          gameOver = false;//unpause the game
          spaceship.getMove(true);//make spaceship moveable
          wave=0;//restart game by going to first wave
          //reset spaceship coordinates
          spaceshipX=spaceship.getX1();
          spaceshipX2=spaceship.getX2();
          spaceshipY=spaceship.getY1();
          spaceshipY2=spaceship.getY2();
          for(int i=0;i<small.length;i++){//resetting AsteroidSmall x coordinate
            small[i].setX(1400);
          }
          for(int i=0;i<med.length;i++){//resetting AsteroidMed x coordinate
            med[i].setX(1400);
          }
          for(int i=0;i<large.length;i++){//resetting AsteroidLarge x coordinate
            large[i].setX(1400);
          }
          for(int i=0;i<huge.length;i++){//resetting AsteroidHuge x coordinate
            huge[i].setX(1400);
          }
          for(int i=0;i<alien.length;i++){//resetting Alien x and y coordinates and animations
            alien[i].setX(1400);
            alien[i].setY(i);
            alienAnim[i] = alienMove;
          }   
          //resetting GravityUp x coordinate
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          //resetting GravityDown x coordinate
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(168/2)) && x<= ((WIDTH/2)+(168/2)) && y>= 500 && y<= 576){//if quit button is pressed
          returnMenu = true;//return to main menu
          //Stop music depending on level
          if(level==1) level1Music.stopLevel1Music();
          if(level==2) level2Music.stopLevel2Music();
          if(level==3) level3Music.stopLevel3Music();
        }
      }
    }
    if(level==4){//if the user has completed the game
      if(wave==-1){//if thank you for playing page is up
        if(x >= ((WIDTH/2)-(192/2)) && x<= ((WIDTH/2)+(192/2)) && y>= 600 && y<= 672){//if next button is pressed
          wave=0;//go to credits 
          textfile.addOneCompletions();//add 1 to completions in text file
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
      }
      if(wave==0){//if credits
        if(x >= ((WIDTH/2)-(186/2)) && x<= ((WIDTH/2)+(186/2)) && y>= 600 && y<= 689){//if end button is pressed
          returnMenu = true;//return to main menu
        }
      }
    }
  }//end of mouseClicked method
  //Methods that had to be overriden but have not been used in the program
  public void mousePressed(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  public boolean backToMenu(){return returnMenu;}//backToMenu method that returns the returnMenu variable (as an boolean)
  /*getImages method
   * Postcondition: all Image variables have assigned png or gif file saved into them
   *Purpose: to get the image used in the MainMenu class and save it*/
  public void getImages(){
    BufferedImage img = null;//creating dummy variable
    try {
      img = ImageIO.read(new File("story1bg.gif"));//putting image into dummy variable
    } catch (IOException e) {}
    background1 = img;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img1 = null;//creating dummy variable
    try {
      img1 = ImageIO.read(new File("story2bg.png"));//putting image into dummy variable
    } catch (IOException e) {}
    background2 = img1;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img2 = null;//creating dummy variable
    try {
      img2 = ImageIO.read(new File("story3bg.gif"));//putting image into dummy variable
    } catch (IOException e) {}
    background3 = img2;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img3 = null;//creating dummy variable
    try {
      img3 = ImageIO.read(new File("level1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1 = img3;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img4 = null;//creating dummy variable
    try {
      img4 = ImageIO.read(new File("level2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2 = img4;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img5 = null;//creating dummy variable
    try {
      img5 = ImageIO.read(new File("level3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3 = img5;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img6 = null;//creating dummy variable
    try {
      img6 = ImageIO.read(new File("startlevel.png"));//putting image into dummy variable
    } catch (IOException e) {}
    startlevel = img6;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img7 = null;//creating dummy variable
    try {
      img7 = ImageIO.read(new File("pause.png"));//putting image into dummy variable
    } catch (IOException e) {}
    pause = img7;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img8 = null;//creating dummy variable
    try {
      img8 = ImageIO.read(new File("paused.png"));//putting image into dummy variable
    } catch (IOException e) {}
    paused = img8;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img9 = null;//creating dummy variable
    try {
      img9 = ImageIO.read(new File("continue.png"));//putting image into dummy variable
    } catch (IOException e) {}
    continued = img9;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img10 = null;//creating dummy variable
    try {
      img10 = ImageIO.read(new File("restart.png"));//putting image into dummy variable
    } catch (IOException e) {}
    restart = img10;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img11 = null;//creating dummy variable
    try {
      img11 = ImageIO.read(new File("quit.png"));//putting image into dummy variable
    } catch (IOException e) {}
    quit = img11;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img12 = null;//creating dummy variable
    try {
      img12 = ImageIO.read(new File("game-over.png"));//putting image into dummy variable
    } catch (IOException e) {}
    gameover = img12;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img13 = null;//creating dummy variable
    try {
      img13 = ImageIO.read(new File("restart-level.png"));//putting image into dummy variable
    } catch (IOException e) {}
    restartlevel = img13;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img14 = null;//creating dummy variable
    try {
      img14 = ImageIO.read(new File("levelcomplete.png"));//putting image into dummy variable
    } catch (IOException e) {}
    levelcomplete = img14;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img15 = null;//creating dummy variable
    try {
      img15 = ImageIO.read(new File("level1line1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1line1 = img15;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img16 = null;//creating dummy variable
    try {
      img16 = ImageIO.read(new File("level1line2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1line2 = img16;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img17 = null;//creating dummy variable
    try {
      img17 = ImageIO.read(new File("level1line3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1line3 = img17;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img18 = null;//creating dummy variable
    try {
      img18 = ImageIO.read(new File("level1line4.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1line4 = img18;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img19 = null;//creating dummy variable
    try {
      img19 = ImageIO.read(new File("level1line5.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level1line5 = img19;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img20 = null;//creating dummy variable
    try {
      img20 = ImageIO.read(new File("level2line1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line1 = img20;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img21 = null;//creating dummy variable
    try {
      img21 = ImageIO.read(new File("level2line2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line2 = img21;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img22 = null;//creating dummy variable
    try {
      img22 = ImageIO.read(new File("level2line3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line3 = img22;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img23 = null;//creating dummy variable
    try {
      img23 = ImageIO.read(new File("level2line4.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line4 = img23;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img24 = null;//creating dummy variable
    try {
      img24 = ImageIO.read(new File("level2line5.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line5 = img24;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img25 = null;//creating dummy variable
    try {
      img25 = ImageIO.read(new File("level2line6.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level2line6 = img25;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img26 = null;//creating dummy variable
    try {
      img26 = ImageIO.read(new File("level3line1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line1 = img26;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img27 = null;//creating dummy variable
    try {
      img27 = ImageIO.read(new File("level3line2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line2 = img27;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img28 = null;//creating dummy variable
    try {
      img28 = ImageIO.read(new File("level3line3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line3 = img28;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img29 = null;//creating dummy variable
    try {
      img29 = ImageIO.read(new File("level3line4.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line4 = img29;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img30 = null;//creating dummy variable
    try {
      img30 = ImageIO.read(new File("level3line5.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line5 = img30;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img31 = null;//creating dummy variable
    try {
      img31 = ImageIO.read(new File("level3line6.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line6 = img31;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img32 = null;//creating dummy variable
    try {
      img32 = ImageIO.read(new File("level3line7.png"));//putting image into dummy variable
    } catch (IOException e) {}
    level3line7 = img32;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img33 = null;//creating dummy variable
    try {
      img33 = ImageIO.read(new File("Congratulations.png"));//putting image into dummy variable
    } catch (IOException e) {}
    congrats = img33;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img34 = null;//creating dummy variable
    try {
      img34 = ImageIO.read(new File("You-saved-humanity.png"));//putting image into dummy variable
    } catch (IOException e) {}
    savedHumanity = img34;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img35 = null;//creating dummy variable
    try {
      img35 = ImageIO.read(new File("Thanks-for-playing.png"));//putting image into dummy variable
    } catch (IOException e) {}
    thanks = img35;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img36 = null;//creating dummy variable
    try {
      img36 = ImageIO.read(new File("Made-by-William-Mucha.png"));//putting image into dummy variable
    } catch (IOException e) {}
    madeBy = img36;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img37 = null;//creating dummy variable
    try {
      img37 = ImageIO.read(new File("Musics.png"));//putting image into dummy variable
    } catch (IOException e) {}
    musics = img37;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img38 = null;//creating dummy variable
    try {
      img38 = ImageIO.read(new File("next.png"));//putting image into dummy variable
    } catch (IOException e) {}
    next = img38;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img39 = null;//creating dummy variable
    try {
      img39 = ImageIO.read(new File("end.png"));//putting image into dummy variable
    } catch (IOException e) {}
    end = img39;//setting Image variable to dummy variable once image has been saved into dummy variable
    for(int i=0;i<small.length;i++){
      small[i].getAsteroid();//getting all the images for the small asteroid object
    }
    for(int i=0;i<med.length;i++){
      med[i].getAsteroid();//getting all the images for the medium asteroid object
    }
    for(int i=0;i<large.length;i++){
      large[i].getAsteroid();//getting all the images for the large asteroid object
    }
    for(int i=0;i<huge.length;i++){
      huge[i].getAsteroid();//getting all the images for the huge asteroid object
    }
  }//end of getImages method
  /*paintComponent method 
   * Postcondition: paints levels and waves of the game
   *Purpose: to paint all of the graphics onto the JFrame with all the images, objects and text*/
  public void paintComponent(Graphics g){
    if(level==1){//first level
      if(wave==-1){//intro wave
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(level1, (width/2)-(482/2),20,null);
        g.drawImage(level1line1, (width/2)-(697/2),150,null);
        g.drawImage(level1line2, (width/2)-(689/2),250,null);
        g.drawImage(level1line3, (width/2)-(692/2),350,null);
        g.drawImage(level1line4, (width/2)-(660/2),450,null);
        g.drawImage(level1line5, (width/2)-(765/2),550,null);
        g.drawImage(startlevel, (width/2)-(541/2),height-100,null);
      }//end of intro wave
      if(wave==0){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          huge[0].changeX();//allows the object to move accross the screen
        }
        if(huge[0].getX1()<200){
          g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[9].changeX();//allows the object to move accross the screen
            small[8].changeX();//allows the object to move accross the screen
            small[7].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(small[9].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[0].setX(1400);
          small[9].setX(1400);
          small[8].setX(1400);
          small[7].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 0
      if(wave==1){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[1].changeX();//allows the object to move accross the screen
        }
        if(large[1].getX2()<1200){
          g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[0].changeX();//allows the object to move accross the screen
          }
          if(large[0].getX2()<1100){
            g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              large[2].changeX();//allows the object to move accross the screen
            }
          }
        } 
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          large[0].setX(1400);
          large[1].setX(1400);
          large[2].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 1
      if(wave==2){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          huge[1].changeX();//allows the object to move accross the screen
        }
        if(huge[1].getX2()<900){
          g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            huge[0].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[0].setX(1400);
          huge[1].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 2
      if(wave==3){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture 
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          med[0].changeX();//allows the object to move accross the screen
          med[4].changeX();//allows the object to move accross the screen
        }
        if(med[4].getX2()<1100){
          g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
          g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[1].changeX();//allows the object to move accross the screen
            med[3].changeX();//allows the object to move accross the screen
          }
          if(med[3].getX2()<1200){
            g.drawImage(med[2].getPic(), med[2].getX1(), med[2].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              med[2].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(med[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          med[0].setX(1400);
          med[1].setX(1400);
          med[2].setX(1400);
          med[3].setX(1400);
          med[4].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 3
      if(wave==4){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
        g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
        g.drawImage(small[5].getPic(), small[5].getX1(), small[5].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          med[0].changeX();//allows the object to move accross the screen
          small[2].changeX();//allows the object to move accross the screen
          small[3].changeX();//allows the object to move accross the screen
          small[4].changeX();//allows the object to move accross the screen
          small[5].changeX();//allows the object to move accross the screen
        }
        if(small[4].getX2()<1100){
          g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
          g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[4].changeX();//allows the object to move accross the screen
            small[6].changeX();//allows the object to move accross the screen
            small[7].changeX();//allows the object to move accross the screen
            small[8].changeX();//allows the object to move accross the screen
          }
          if(small[6].getX2()<1100){
            g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              large[0].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[2].setX(1400);
          small[3].setX(1400);
          small[4].setX(1400);
          small[5].setX(1400);
          small[6].setX(1400);
          small[7].setX(1400);
          small[8].setX(1400);
          med[0].setX(1400);
          med[4].setX(1400);
          large[0].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 4
      if(wave==5){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture 
        g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[1].changeX();//allows the object to move accross the screen
        }
        if(large[1].getX2()<1250){
          g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
          g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[3].changeX();//allows the object to move accross the screen
            med[1].changeX();//allows the object to move accross the screen
          }
          if(med[1].getX2()<1250){
            g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              huge[1].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          large[1].setX(1400);
          med[1].setX(1400);
          med[3].setX(1400);
          huge[1].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 5
      if(wave==6){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
        g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          small[1].changeX();//allows the object to move accross the screen
          small[3].changeX();//allows the object to move accross the screen
          small[6].changeX();//allows the object to move accross the screen
          small[9].changeX();//allows the object to move accross the screen
        }
        if(small[1].getX2()<1200){
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
          g.drawImage(small[5].getPic(), small[5].getX1(), small[5].getY1(), null);
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[2].changeX();//allows the object to move accross the screen
            small[4].changeX();//allows the object to move accross the screen
            small[5].changeX();//allows the object to move accross the screen
            small[8].changeX();//allows the object to move accross the screen
          }
          if(small[2].getX2()<1200){
            g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
            g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
            g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
            g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              med[0].changeX();//allows the object to move accross the screen
              med[1].changeX();//allows the object to move accross the screen
              med[3].changeX();//allows the object to move accross the screen
              med[4].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(med[4].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[1].setX(1400);
          small[2].setX(1400);
          small[3].setX(1400);
          small[4].setX(1400);
          small[5].setX(1400);
          small[6].setX(1400);
          small[8].setX(1400);
          small[9].setX(1400);
          med[0].setX(1400);
          med[1].setX(1400);
          med[3].setX(1400);
          med[4].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 6
      if(wave==7){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[0].changeX();//allows the object to move accross the screen
          med[4].changeX();//allows the object to move accross the screen
        }
        if(large[0].getX2()<1100){
          g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[2].changeX();//allows the object to move accross the screen
            med[0].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          large[0].setX(1400);
          large[2].setX(1400);
          med[0].setX(1400);
          med[4].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 7
      if(wave==8){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[2].changeX();//allows the object to move accross the screen
          small[2].changeX();//allows the object to move accross the screen
        }
        if(small[2].getX2()<1300){
          g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[0].changeX();//allows the object to move accross the screen
          }
          if(small[0].getX2()<1300){
            g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              small[1].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[0].setX(1400);
          small[1].setX(1400);
          small[2].setX(1400);
          large[2].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 8
      if(wave==9){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          med[4].changeX();//allows the object to move accross the screen
        }
        if(med[4].getX2()<1200){
          g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            huge[0].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[0].setX(1400);
          med[4].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 9
      if(wave==10){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          med[3].changeX();//allows the object to move accross the screen
        }
        if(med[3].getX2()<1200){
          g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[1].changeX();//allows the object to move accross the screen
          }
          if(large[1].getX1()<1200){
            g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              small[9].changeX();//allows the object to move accross the screen
            }
          }
          if(large[1].getX2()<1300){
            g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
            g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              small[0].changeX();//allows the object to move accross the screen
              small[1].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(small[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[0].setX(1400);
          small[1].setX(1400);
          small[9].setX(1400);
          med[3].setX(1400);
          large[1].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 10
      if(wave==11){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background1, 0,0,null);//then puts the background picture
        spaceship.getMove(false);
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        g.drawImage(levelcomplete, levelcompleteX,(height/2)-(95/2),null);//draws the level complete title
        if(levelcompleteX>((width/2)-(901/2))){ 
          levelcompleteX-=5;//move the level complete title to center of screen
        } else{//once in center
          spaceshipX++;//move spaceship off the screen
          if(spaceshipX>400){
            spaceshipX++;
          }
          if(spaceshipX>800){//faster
            spaceshipX++;
          }
          if(spaceshipX>1000){//and faster
            spaceshipX+=5;
          }
        }
        if(spaceshipX>2000){//is spaceship is off the screen for 1 sec
          spaceship.getMove(true);//make the spaceship moveable
          level1Music.stopLevel1Music();//stop level 1 music
          level=2;//go to level 2
          wave=-1;//go back to intro wave
          levelcompleteX = 1400;//reset the x coordinate for level complete title
        }
      }//end of outrowave
    }//END OF LEVEL 1
    if(level==2){
      if(wave==-1){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(level2, (width/2)-(509/2),20,null);
        g.drawImage(level2line1, (width/2)-(677/2),175,null);
        g.drawImage(level2line2, (width/2)-(711/2),250,null);
        g.drawImage(level2line3, (width/2)-(745/2),325,null);
        g.drawImage(level2line4, (width/2)-(791/2),400,null);
        g.drawImage(level2line5, (width/2)-(778/2),475,null);
        g.drawImage(level2line6, (width/2)-(648/2),550,null);
        g.drawImage(startlevel, (width/2)-(541/2),height-100,null);
        spaceshipX=spaceship.getX1();
        spaceshipX2=spaceship.getX2();
        spaceshipY=spaceship.getY1();
        spaceshipY2=spaceship.getY2();
      }//end of intro wave
      if(wave==0){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          huge[1].changeX();//allows the object to move accross the screen
        }
        if(huge[1].getX2()<1000){
          g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[0].changeX();//allows the object to move accross the screen
          }
          if(large[0].getX2()<1000){
            g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              large[2].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[1].setX(1400);
          large[0].setX(1400);
          large[2].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 0
      if(wave==1){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        alienAnim[1].start();
        g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          alien[1].change();//allows the object to move accross the screen
        }
        alienAnim[1].update();
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 1
      if(wave==2){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture 
        g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[1].changeX();//allows the object to move accross the screen
        }
        if(large[1].getX2()<1400){
          alienAnim[2].start();
          g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[2].change();//allows the object to move accross the screen
          }
          alienAnim[2].update();
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          large[1].setX(1400);
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 2
      if(wave==3){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[2].changeX();//allows the object to move accross the screen
        }
        if(large[2].getX1()<1250){
          g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[0].changeX();//allows the object to move accross the screen
            small[2].changeX();//allows the object to move accross the screen
          }
          if(small[0].getX2()<1400){
            alienAnim[0].start();
            g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              alien[0].change();//allows the object to move accross the screen
            }
            alienAnim[0].update();
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[0].setX(1400);
          small[2].setX(1400);
          large[2].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 3
      if(wave==4){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[0].changeX();//allows the object to move accross the screen
        }
        if(large[0].getX2()<1200){
          alienAnim[2].start();
          g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[2].change();//allows the object to move accross the screen
          }
          alienAnim[2].update();
        }
        if(large[0].getX2()<1000){
          g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[2].changeX();//allows the object to move accross the screen
          }
        } 
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          large[0].setX(1400);
          large[2].setX(1400);
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 4
      if(wave==5){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        alienAnim[0].start();
        alienAnim[2].start();
        g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
        g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          alien[0].change();//allows the object to move accross the screen
          alien[2].change();//allows the object to move accross the screen
          med[0].changeX();//allows the object to move accross the screen
        }
        alienAnim[0].update();
        alienAnim[2].update();
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          med[0].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 5
      if(wave==6){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
        g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          small[0].changeX();//allows the object to move accross the screen
          small[3].changeX();//allows the object to move accross the screen
          small[4].changeX();//allows the object to move accross the screen
          small[8].changeX();//allows the object to move accross the screen
        }
        if(small[0].getX2()<1250){
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[2].changeX();//allows the object to move accross the screen
            small[6].changeX();//allows the object to move accross the screen
          }
          if(small[2].getX2()<1250){
            g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
            g.drawImage(small[5].getPic(), small[5].getX1(), small[5].getY1(), null);
            g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
            g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              small[1].changeX();//allows the object to move accross the screen
              small[5].changeX();//allows the object to move accross the screen
              small[7].changeX();//allows the object to move accross the screen
              small[9].changeX();//allows the object to move accross the screen
            }
            if(small[2].getX2()<1400){
              alienAnim[2].start();
              g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
              if(gamePause==false && gameOver==false){//while the game is not paused or over
                alien[2].change();//allows the object to move accross the screen
              }
              alienAnim[2].update();
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[0].setX(1400);
          small[1].setX(1400);
          small[2].setX(1400);
          small[3].setX(1400);
          small[4].setX(1400);
          small[5].setX(1400);
          small[6].setX(1400);
          small[7].setX(1400);
          small[8].setX(1400);
          small[9].setX(1400);
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 6
      if(wave==7){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          huge[1].changeX();//allows the object to move accross the screen
        }
        if(huge[1].getX2()<1150){
          alienAnim[1].start();
          g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[1].change();//allows the object to move accross the screen
          }
          alienAnim[1].update();
          if(alien[1].getX2()<1300){
            g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              huge[0].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[1].setX(1400);
          huge[0].setX(1400);
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 7
      if(wave==8){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(large[0].getPic(),large[0].getX1(), large[0].getY1(), null);
        g.drawImage(med[4].getPic(),med[4].getX1(), med[4].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          large[0].changeX();//allows the object to move accross the screen
          med[4].changeX();//allows the object to move accross the screen
        }
        if(large[0].getX2()<1000){
          alienAnim[1].start();
          g.drawImage(small[0].getPic(),small[0].getX1(), small[0].getY1(), null);
          g.drawImage(small[1].getPic(),small[1].getX1(), small[1].getY1(), null);
          g.drawImage(small[2].getPic(),small[2].getX1(), small[2].getY1(), null);
          g.drawImage(small[3].getPic(),small[3].getX1(), small[3].getY1(), null);
          g.drawImage(small[4].getPic(),small[4].getX1(), small[4].getY1(), null);
          g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[1].change();//allows the object to move accross the screen
            small[0].changeX();//allows the object to move accross the screen
            small[1].changeX();//allows the object to move accross the screen
            small[2].changeX();//allows the object to move accross the screen
            small[3].changeX();//allows the object to move accross the screen
            small[4].changeX();//allows the object to move accross the screen
          }
          alienAnim[1].update();
          if(alien[1].getX2()<1000){
            g.drawImage(large[2].getPic(),large[2].getX1(), large[2].getY1(), null);
            g.drawImage(med[0].getPic(),med[0].getX1(), med[0].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              large[2].changeX();//allows the object to move accross the screen
              med[0].changeX();//allows the object to move accross the screen
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(large[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[0].setX(1400);
          small[1].setX(1400);
          small[2].setX(1400);
          small[3].setX(1400);
          small[4].setX(1400);
          med[0].setX(1400);
          med[4].setX(1400);
          large[0].setX(1400);
          large[2].setX(1400);
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 8
      if(wave==9){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        g.drawImage(med[1].getPic(),med[1].getX1(), med[1].getY1(), null);
        g.drawImage(med[3].getPic(),med[3].getX1(), med[3].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          med[1].changeX();//allows the object to move accross the screen
          med[3].changeX();//allows the object to move accross the screen
        }
        if(med[3].getX2()<1400){
          alienAnim[0].start();
          alienAnim[2].start();
          g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
          g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[0].change();//allows the object to move accross the screen
            alien[2].change();//allows the object to move accross the screen
          }
          alienAnim[0].update();
          alienAnim[2].update();
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          med[1].setX(1400);
          med[3].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 9
      if(wave==10){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        alienAnim[0].start();
        alienAnim[1].start();
        alienAnim[2].start();
        g.drawImage(small[1].getPic(),small[1].getX1(), small[1].getY1(), null);
        g.drawImage(small[2].getPic(),small[2].getX1(), small[2].getY1(), null);
        g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
        g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
        g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          small[1].changeX();//allows the object to move accross the screen
          small[2].changeX();//allows the object to move accross the screen
          alien[0].change();//allows the object to move accross the screen
          alien[1].change();//allows the object to move accross the screen
          alien[2].change();//allows the object to move accross the screen
        }
        alienAnim[0].update();
        alienAnim[1].update();
        alienAnim[2].update();
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          small[1].setX(1400);
          small[2].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 10
      if(wave==11){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background2, 0,0,null);//then puts the background picture
        spaceship.getMove(false);
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        g.drawImage(levelcomplete, levelcompleteX,(height/2)-(95/2),null);//draws the level complete title
        if(levelcompleteX>((width/2)-(901/2))){ 
          levelcompleteX-=5;//move the level complete title to center of screen
        } else{//once in center
          spaceshipX++;//move spaceship off the screen
          if(spaceshipX>400){
            spaceshipX++;
          }
          if(spaceshipX>800){//faster
            spaceshipX++;
          }
          if(spaceshipX>1000){//and faster
            spaceshipX+=5;
          }
        }
        if(spaceshipX>2000){//once spaceship is 1 second off the screen
          spaceship.getMove(true);//make the spaceship movable
          level2Music.stopLevel2Music();//stop music
          level=3;//change to level 3
          wave=-1;//change to level start wave
          levelcompleteX=1400;//reset level complete title x coordinate
        }
      }//end of outrowave
    }//END OF LEVEL 2
    if(level==3){
      if(wave==-1){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        g.drawImage(level3, (width/2)-(504/2),20,null);
        g.drawImage(level3line1, (width/2)-(635/2),150,null);
        g.drawImage(level3line2, (width/2)-(702/2),225,null);
        g.drawImage(level3line3, (width/2)-(695/2),300,null);
        g.drawImage(level3line4, (width/2)-(763/2),375,null);
        g.drawImage(level3line5, (width/2)-(736/2),450,null);
        g.drawImage(level3line6, (width/2)-(660/2),525,null);
        g.drawImage(level3line7, (width/2)-(753/2),600,null);
        g.drawImage(startlevel, (width/2)-(541/2),height-100,null);
        spaceshipX=spaceship.getX1();
        spaceshipX2=spaceship.getX2();
        spaceshipY=spaceship.getY1();
        spaceshipY2=spaceship.getY2();
      }//end of intro wave
      if(wave==0){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[0].changeX();//allows the object to move accross the screen
        }
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[1].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        gravityUp.update();
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(gravityU[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 0
      if(wave==1){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        gravityDown.update();
        if(gravityU[0].getX1()<1350){
          g.drawImage(large[0].getPic(),large[0].getX1(), large[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[0].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<1350){
          g.drawImage(large[2].getPic(),large[2].getX1(), large[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[2].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(gravityD[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityD[1].setX(2200);
          large[2].setX(1400);
          large[0].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 1
      if(wave==2){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
          gravityU[1].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        if(gravityU[0].getX2()<1400){
          g.drawImage(med[0].getPic(),med[0].getX1(), med[0].getY1(), null);
          g.drawImage(med[4].getPic(),med[4].getX1(), med[4].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[0].changeX();//allows the object to move accross the screen
            med[4].changeX();//allows the object to move accross the screen
          }
          if(med[4].getX2()<1400){
            alienAnim[1].start();
            g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              alien[1].change();//allows the object to move accross the screen
            }
            alienAnim[1].update();
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(gravityU[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          med[0].setX(1400);
          med[4].setX(1400);
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 2
      if(wave==3){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        g.drawImage(huge[1].getPic(),huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[0].changeX();//allows the object to move accross the screen
          huge[1].changeX();//allows the object to move accross the screen
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        if(gravityD[1].getX1()<1150){
          g.drawImage(huge[0].getPic(),huge[0].getX1(), huge[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            huge[0].changeX();//allows the object to move accross the screen
          }
        } 
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[0].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          huge[0].setX(1400);
          huge[1].setX(1400);
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 3
      if(wave==4){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        if(gravityU[0].getX1()<1180){
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[0].changeX();//allows the object to move accross the screen
          }
          if(med[0].getX2()<1400){
            alienAnim[2].start();
            g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              alien[2].change();//allows the object to move accross the screen
            }
            alienAnim[2].update();
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          med[0].setX(1400);
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 4
      if(wave==5){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[0].changeX();//allows the object to move accross the screen
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        if(gravityD[0].getX1()<1300){
          g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[1].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<1300){
          alienAnim[0].start();
          g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
          g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[2].changeX();//allows the object to move accross the screen
            alien[0].change();//allows the object to move accross the screen
          }
          alienAnim[0].update();
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(gravityD[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          large[1].setX(1400);
          large[2].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 5
      if(wave==6){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        gravityDown.update();
        if(gravityU[0].getX1()<1180){
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
          g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
          g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[0].changeX();//allows the object to move accross the screen
            med[1].changeX();//allows the object to move accross the screen
            med[3].changeX();//allows the object to move accross the screen
            med[4].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<1180){
          g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
          g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
          g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
          g.drawImage(small[5].getPic(), small[5].getX1(), small[5].getY1(), null);
          g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[0].changeX();//allows the object to move accross the screen
            small[1].changeX();//allows the object to move accross the screen
            small[4].changeX();//allows the object to move accross the screen
            small[5].changeX();//allows the object to move accross the screen
            small[6].changeX();//allows the object to move accross the screen
            small[7].changeX();//allows the object to move accross the screen
            small[8].changeX();//allows the object to move accross the screen
            small[9].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(gravityD[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityD[1].setX(2200);
          med[0].setX(1400);
          med[1].setX(1400);
          med[3].setX(1400);
          med[4].setX(1400);
          small[0].setX(1400);
          small[1].setX(1400);
          small[4].setX(1400);
          small[5].setX(1400);
          small[6].setX(1400);
          small[7].setX(1400);
          small[8].setX(1400);
          small[9].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 6
      if(wave==7){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[0].changeX();//allows the object to move accross the screen
        }
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[1].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        gravityUp.update();
        g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          huge[1].changeX();//allows the object to move accross the screen
        }
        if(gravityU[1].getX2()<1400){
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
          g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[0].changeX();//allows the object to move accross the screen
            med[3].changeX();//allows the object to move accross the screen
            med[4].changeX();//allows the object to move accross the screen
            small[2].changeX();//allows the object to move accross the screen
          }
          if(med[4].getX2()<1300){
            alienAnim[1].start();
            g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              alien[1].change();//allows the object to move accross the screen
            }
            alienAnim[1].update();
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityD[0].setX(1400);
          gravityU[1].setX(2200);
          huge[1].setX(1400);
          med[0].setX(1400);
          med[3].setX(1400);
          med[4].setX(1400);
          small[2].setX(1400);
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 7
      if(wave==8){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        gravityDown.update();
        if(gravityU[0].getX1()<1300){
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[2].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityU[0].getX1()<1100){
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[7].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityU[0].getX1()<900){
          g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            large[0].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<900){
          alienAnim[2].start();
          g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            alien[2].change();//allows the object to move accross the screen
          }
          alienAnim[2].update();
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityD[1].setX(2200);
          small[2].setX(1400);
          small[7].setX(1400);
          large[0].setX(1400);
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 8
      if(wave==9){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[0].changeX();//allows the object to move accross the screen
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        if(gravityD[0].getX1()<1350){
          g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[9].changeX();//allows the object to move accross the screen
          }
          if(small[9].getX2()<1400){
            g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              med[4].changeX();//allows the object to move accross the screen
            }
            if(med[4].getX2()<1400){
              g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
              if(gamePause==false && gameOver==false){//while the game is not paused or over
                large[2].changeX();//allows the object to move accross the screen
              }
              if(large[2].getX2()<1400){
                g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
                if(gamePause==false && gameOver==false){//while the game is not paused or over
                  huge[1].changeX();//allows the object to move accross the screen
                }
              }
            }
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(huge[1].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          small[0].setX(1400);
          med[4].setX(1400);
          large[2].setX(1400);
          huge[1].setX(1400);
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 9
      if(wave==10){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
        if(gamePause==false && gameOver==false){//while the game is not paused or over
          gravityD[1].changeX();//allows the object to move accross the screen
        }
        if(gravityD[1].getX1()<1400){
          g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            gravityU[1].changeX();//allows the object to move accross the screen
          }
          if(gravityU[1].getX1()<1300){
            g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
            if(gamePause==false && gameOver==false){//while the game is not paused or over
              large[0].changeX();//allows the object to move accross the screen
            }
            if(large[0].getX2()<1100){
              alienAnim[2].start();
              g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
              if(gamePause==false && gameOver==false){//while the game is not paused or over
                alien[2].change();//allows the object to move accross the screen
              }
              alienAnim[2].update();
            }
          }
        }
        gravityUp.update();
        gravityDown.update();
        if(gravityU[0].getX1()<1300){
          g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[1].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityU[0].getX1()<1180){
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          alienAnim[0].start();
          g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[0].changeX();//allows the object to move accross the screen
            alien[0].change();//allows the object to move accross the screen
          }
          alienAnim[0].update();
        }
        if(gravityU[0].getX1()<900){
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[2].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<1300){
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[8].changeX();//allows the object to move accross the screen
          }
        }
        if(gravityD[1].getX1()<1180){
          g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
          alienAnim[1].start();
          g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            med[4].changeX();//allows the object to move accross the screen
            alien[1].change();//allows the object to move accross the screen
          }
          alienAnim[1].update();
        }
        if(gravityD[1].getX1()<900){
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          if(gamePause==false && gameOver==false){//while the game is not paused or over
            small[7].changeX();//allows the object to move accross the screen
          }
        }
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        if(alien[2].getX2()<0){       
          wave++;//if the wave is over, move on to next wave and reset all object variabels
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          gravityD[1].setX(2200);
          small[2].setX(1400);
          small[3].setX(1400);
          small[8].setX(1400);
          small[7].setX(1400);
          med[0].setX(1400);
          med[4].setX(1400);
          large[0].setX(1400);
          alien[0].setX(1400);
          alien[0].setY(0);
          alienAnim[0] = alienMove;
          alien[1].setX(1400);
          alien[1].setY(1);
          alienAnim[1] = alienMove;
          alien[2].setX(1400);
          alien[2].setY(2);
          alienAnim[2] = alienMove;
        }
        if(gamePause){//while game is pause
          g.drawImage(paused, (width/2)-(472/2), height/2-300, null);//draw paused title image
          g.drawImage(continued, (width/2)-(371/2), 300, null);//draw contine button
          g.drawImage(restart, (width/2)-(319/2), 400, null);//draw restart button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        } else {//otherwise
          if(gameOver==false){//if game is not over
            g.drawImage(pause, width-261, 0, null);//draw the pause button
          }
        }
        if(gameOver){//while game is over
          g.drawImage(gameover, (width/2)-(697/2), 200, null);//draw gameover title
          g.drawImage(restartlevel, (width/2)-(642/2), 400, null);//draw restart level button
          g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        }
      }//end of wave 10
      if(wave==11){
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        spaceship.getMove(false);
        animation.start();//starts Spaceship animation
        g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
        animation.update();//updates Spaceship animation
        g.drawImage(levelcomplete, levelcompleteX,(height/2)-(95/2),null);//draws the level complete title
        if(levelcompleteX>((width/2)-(901/2))){ 
          levelcompleteX-=5;//move the level complete title to center of screen
        } else{//once in center
          spaceshipX++;//move spaceship off the screen
          if(spaceshipX>400){
            spaceshipX++;
          }
          if(spaceshipX>800){//faster
            spaceshipX++;
          }
          if(spaceshipX>1000){//and faster
            spaceshipX+=5;
          }
        }
        if(spaceshipX>2000){//once spaceship is 1 second off the screen
          level3Music.stopLevel3Music();//stop music
          level=4;//change level to summary level
          wave=-1;//change wave to thank you panel
          levelcompleteX=1400;//make level complete title x to off the screen
        }
      }//end of outrowave
    }//END OF LEVEL 3
    if(level==4){
      if(wave==-1){//Thank you panel
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        g.drawImage(congrats, (width/2)-(914/2), 150, null);
        g.drawImage(savedHumanity, (width/2)-(807/2), 300, null);
        g.drawImage(thanks, (width/2)-(818/2), 450, null);
        g.drawImage(next, (width/2)-(192/2), 600, null);
      }//endof thank you panel
      if(wave==0){//credits
        int width = getWidth();//gets the width of the frame
        int height = getHeight();//gets the height of the frame
        super.paintComponent(g);//initially paints the background black
        g.drawImage(background3, 0,0,null);//then puts the background picture
        g.drawImage(madeBy, (width/2)-(948/2), 150, null);
        g.drawImage(musics, (width/4)-(242/2), 300, null);
        g.setFont(new Font("Tahoma", Font.BOLD, 35));
        g.setColor(Color.BLACK);
        g.drawString("Intro: Braken - To The Stars",500,350);
        g.drawString("Level 1: Tonyz - Road So Far",500,400);
        g.drawString("Level 2: Axollo - Moonstruck",500,450);
        g.drawString("Level 3: Nitro Fun & Hyper Potions - Checkpoint ",500,500);
        g.drawString("Survival: Teminite - Night Drive",500,550);
        g.drawImage(end, (width/2)-(186/2), 600, null);
      }//end of credits
    }//end of level 4 or summary level
  }//end of PaintComponet method
}//end of StoryMode class