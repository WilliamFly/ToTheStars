/*SurvivalMode class
 * Author: William Mucha
 * Class Created: 12/26/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to run all the necessary code to run the survvial mode portion of the game
 * Import used in the SurvivalMode class*/
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;  
//SurvivalMode class which is a child class to the Lobbies class and that also implements the MouseListener interface
public class SurvivalMode extends Lobbies implements MouseListener{
//Variables
  Image background;
  Image pause;//pause button
  Image paused;//pause title
  Image continued;//continue button
  Image restart;//restart button
  Image quit;//quit button
  Image gameover;//gameover title
  Image playagain;//play again button
  Image survivalMode;//title
  Image enterName;//title
  Image start;//start button
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
  TextFile text = new TextFile();//making textfile accessible in this class
  boolean fillArr = true;//boolean that fills the all arrays of objects once
  int wave=-1;//keeps track of waves
  Random rn = new Random();//random object to help randomize wave
  int score=0;//players score
  String playerName = KeyPressing.name;//takes the string for the playername from keypressing
  int scoreX=470;//x coordinate for the score text
  int scoreY=750;//y coordinate for the score text
  boolean once = true;//boolean that makes sure that the saving of highscores only runs once
  boolean gamePause=false;//boolean that changes depending if the game is paused or not
  boolean gameOver=false;//boolean that changes depending if the game is over or not
  boolean returnMenu=false;//boolean that changes if the user would like to return to the main menu
//Constructor
  public SurvivalMode() {
    Thread animationThread = new Thread(new Runnable() {//creates a new thread that runs in the background 
      public void run() {
        while(fillArr){//runs in the beginning once to fill arrays
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
        while(true){//runs all the time while the game is running
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
          if(wave==0){//if wave is 0
            wave = rn.nextInt(25) + 1;//randomize a wave between 1 and 25
          }
          if(wave==-1){//if wave is intro wave
            playerName = KeyPressing.name;//keep checking when the player types in their name and save it to local variable to be outputed
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
    if(wave==-1){//if wave is intro wave
      if(playerName.length()>0){
        if(x >= (WIDTH/2)-(255/2) && x<= (WIDTH/2)+(255/2) && y>= (HEIGHT-100) && y<= (HEIGHT-21)){//if start button is pressed
          wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave//start game
          spaceship.getMove(true);//make ship moveable
        }
      }
    }
    if(wave!=-1){//while the wave is not the intro wave
      if(x >= (WIDTH-261) && x<= WIDTH && y>= 0 && y<= 78 && gameOver!=true){//if pause button is pressed
        gamePause = true;//pause game
        spaceship.getMove(false);//make spaceship unable to move
        repaint();//repaint the frame
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(gamePause){//while the game is paused 
        if(x >= ((WIDTH/2)-(371/2)) && x<= ((WIDTH/2)+(371/2)) && y>= 300 && y<= 378){//if continue button is pressed
          gamePause = false;//unpause game
          spaceship.getMove(true);//make ships moveable
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(319/2)) && x<= ((WIDTH/2)+(319/2)) && y>= 400 && y<= 472){//if restart button is pressed
          gamePause = false;//unpause game
          spaceship.getMove(true);//make ships moveable
          wave=-1;//set back to intro wave
          KeyPressing.name = "";//reset name
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
            alienAnim[i] = alienMove;
          }   
          //resetting GravityUp x coordinate
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          //resetting GravityDown x coordinate
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          score=0;//reset score
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(168/2)) && x<= ((WIDTH/2)+(168/2)) && y>= 500 && y<= 576){//if quit button is pressed
          KeyPressing.name = "";//reset name
          returnMenu = true;//return to main menu
        }
      }
      if(gameOver){//while the game is over
        if(x >= ((WIDTH/2)-(485/2)) && x<= ((WIDTH/2)+(485/2)) && y>= 400 && y<= 479){//if player clicks play again button
          gameOver = false;//game over is false
          once = true;//resets once to true to make sure highscore will be saved next time
          KeyPressing.name = "";//resets name
          spaceship.getMove(true);//makes ship moveable
          wave=-1;//goes back to intro wave
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
            alienAnim[i] = alienMove;
          }   
          //resetting GravityUp x coordinate
          gravityU[0].setX(1400);
          gravityU[1].setX(2200);
          //resetting GravityDown x coordinate
          gravityD[0].setX(1400);
          gravityD[1].setX(2200);
          score=0;//reset score
          repaint();//repaint the frame
          x=0;//resetting x to 0 to minimize clicking issues
          y=0;//resetting y to 0 to minimize clicking issues
        }
        if(x >= ((WIDTH/2)-(168/2)) && x<= ((WIDTH/2)+(168/2)) && y>= 500 && y<= 576){//if quit button is pressed
          KeyPressing.name = "";//reset name
          returnMenu = true;//return back to main menu
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
      img = ImageIO.read(new File("survivalbg.png"));//putting image into dummy variable
    } catch (IOException e) {}
    background = img;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img1 = null;//creating dummy variable
    try {
      img1 = ImageIO.read(new File("pause.png"));//putting image into dummy variable
    } catch (IOException e) {}
    pause = img1;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img2 = null;//creating dummy variable
    try {
      img2 = ImageIO.read(new File("paused.png"));//putting image into dummy variable
    } catch (IOException e) {}
    paused = img2;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img3 = null;//creating dummy variable
    try {
      img3 = ImageIO.read(new File("continue.png"));//putting image into dummy variable
    } catch (IOException e) {}
    continued = img3;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img4 = null;//creating dummy variable
    try {
      img4 = ImageIO.read(new File("restart.png"));//putting image into dummy variable
    } catch (IOException e) {}
    restart = img4;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img5 = null;//creating dummy variable
    try {
      img5 = ImageIO.read(new File("quit.png"));//putting image into dummy variable
    } catch (IOException e) {}
    quit = img5;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img6 = null;//creating dummy variable
    try {
      img6 = ImageIO.read(new File("game-over.png"));//putting image into dummy variable
    } catch (IOException e) {}
    gameover = img6;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img7 = null;//creating dummy variable
    try {
      img7 = ImageIO.read(new File("play-again.png"));//putting image into dummy variable
    } catch (IOException e) {}
    playagain = img7;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img8 = null;//creating dummy variable
    try {
      img8 = ImageIO.read(new File("Survival-Mode.png"));//putting image into dummy variable
    } catch (IOException e) {}
    survivalMode = img8;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img9 = null;//creating dummy variable
    try {
      img9 = ImageIO.read(new File("Please-enter-your-name.png"));//putting image into dummy variable
    } catch (IOException e) {}
    enterName = img9;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img10 = null;//creating dummy variable
    try {
      img10 = ImageIO.read(new File("start2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    start = img10;//setting Image variable to dummy variable once image has been saved into dummy variable
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
    if(wave==-1){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(survivalMode, (width/2)-(845/2),20,null);
      g.drawImage(enterName, (width/2)-(868/2),200,null);
      g.drawImage(start, (width/2)-(255/2),height-100,null);
      spaceship.getMove(false);//make the spaceship not moveable
      g.setFont(new Font("Tahoma", Font.BOLD, 50));
      g.setColor(Color.WHITE);
      g.drawString("Start typing to see your name, max of 16 letters",100,350);
      g.setColor(Color.RED);
      g.drawString(playerName,450,450);
      g.setColor(Color.WHITE);
      g.drawString("Click Start when you are ready",300,550);
      g.drawString("Leaving Name blank won't let you start",200,650);
    }  
    if(wave==0){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 0
    if(wave==1){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        huge[1].changeX();//allows the object to move accross the screen
      }
      if(huge[1].getX1()<200){
        g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[0].changeX();//allows the object to move accross the screen
          small[1].changeX();//allows the object to move accross the screen
          small[2].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation     
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(small[1].getX2()<0){       
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        huge[1].setX(1400);
        small[0].setX(1400);
        small[1].setX(1400);
        small[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 1
    if(wave==2){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[1].changeX();//allows the object to move accross the screen
      }
      if(large[1].getX1()<300){
        g.drawImage(med[2].getPic(), med[2].getX1(), med[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[2].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(med[2].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[1].setX(1400);
        med[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 2
    if(wave==3){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        small[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
      if(gamePause==false && gameOver==false){
        small[9].changeX();//allows the object to move accross the screen
      }
      if(small[0].getX1()<1200){
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[1].changeX();//allows the object to move accross the screen
        }
        g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[8].changeX();//allows the object to move accross the screen
        }
        if(small[1].getX1()<1200){
          g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
          if(gamePause==false && gameOver==false){
            small[2].changeX();//allows the object to move accross the screen
          }
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          if(gamePause==false && gameOver==false){
            small[7].changeX();//allows the object to move accross the screen
          }
          if(small[2].getX1()<1200){
            g.drawImage(med[2].getPic(), med[2].getX1(), med[2].getY1(), null);
            if(gamePause==false && gameOver==false){
              med[2].changeX();//allows the object to move accross the screen
            }
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(med[2].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        small[0].setX(1400);
        small[1].setX(1400);
        small[2].setX(1400);
        small[7].setX(1400);
        small[8].setX(1400);
        small[9].setX(1400);
        med[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 3
    if(wave==4){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      alienAnim[1].start();
      g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        alien[1].change();//allows the object to move accross the screen
      }
      alienAnim[1].update();
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(alien[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        alien[1].setX(1400);
        alienAnim[1] = alienMove;
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 4
    if(wave==5){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(),  gravityD[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(),  gravityD[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[1].changeX();//allows the object to move accross the screen
      }
      gravityDown.update();
      if(gravityD[0].getX1()<1350){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[1].getX1()<1350){
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[0].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityD[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityD[0].setX(1400);
        gravityD[1].setX(2200);
        large[0].setX(1400);
        large[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 5
    if(wave==6){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture  
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(),  gravityU[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(),  gravityU[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[1].changeX();//allows the object to move accross the screen
      }
      gravityUp.update();
      if(gravityU[0].getX1()<1350){
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[0].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[1].getX1()<1350){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityU[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityU[0].setX(1400);
        gravityU[1].setX(2200);
        large[0].setX(1400);
        large[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 6
    if(wave==7){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      alienAnim[0].start();
      alienAnim[2].start();
      g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
      g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
      if(gamePause==false && gameOver==false){
        alien[0].change();//allows the object to move accross the screen
        alien[2].change();//allows the object to move accross the screen
      }
      alienAnim[0].update();
      alienAnim[2].update();
      if(alien[0].getX1()<900){
        g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
        g.drawImage(med[4].getPic(), med[3].getX1(), med[3].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[1].changeX();//allows the object to move accross the screen
          med[3].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(med[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        alien[0].setX(1400);
        alienAnim[0] = alienMove;
        alien[2].setX(1400);
        alienAnim[2] = alienMove;
        med[1].setX(1400);
        med[3].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 7
    if(wave==8){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[0].changeX();//allows the object to move accross the screen
      }
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[1].changeX();//allows the object to move accross the screen
      }
      gravityUp.update();
      gravityDown.update();
      if(gravityU[0].getX1()<1350){
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[0].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[1].getX1()<1350){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityD[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityU[0].setX(1400);
        gravityD[1].setX(2200);
        large[0].setX(1400);
        large[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 8
    if(wave==9){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[0].changeX();//allows the object to move accross the screen
      }
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[1].changeX();//allows the object to move accross the screen
      }
      gravityDown.update();
      gravityUp.update();
      if(gravityD[0].getX1()<1350){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[1].getX1()<1350){
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[0].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityU[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityD[0].setX(1400);
        gravityU[1].setX(2200);
        large[0].setX(1400);
        large[2].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 9
    if(wave==10){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[1].changeX();//allows the object to move accross the screen
      }
      if(large[1].getX1()<950){
        g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          huge[0].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(huge[0].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[1].setX(1400);
        huge[0].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 10
    if(wave==11){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(),  gravityD[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(),  gravityD[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[1].changeX();//allows the object to move accross the screen
      }
      gravityDown.update();
      if(gravityD[0].getX1()<1350){
        g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[8].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[0].getX1()<1150){
        g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[7].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[0].getX1()<950){
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
        g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[0].changeX();//allows the object to move accross the screen
          small[6].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[1].getX1()<1350){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityD[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityD[0].setX(1400);
        gravityD[1].setX(2200);
        small[8].setX(1400);
        small[7].setX(1400);
        small[6].setX(1400);
        large[2].setX(1400);
        med[0].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 11
    if(wave==12){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture  
      g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
      g.drawImage(small[5].getPic(), small[5].getX1(), small[5].getY1(), null);
      if(gamePause==false && gameOver==false){
        small[0].changeX();//allows the object to move accross the screen
        small[5].changeX();//allows the object to move accross the screen
      }
      if(small[0].getX1()<1200){
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[3].changeX();//allows the object to move accross the screen
          small[8].changeX();//allows the object to move accross the screen
        }
        if(small[3].getX1()<1200){
          g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
          g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
          g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
          if(gamePause==false && gameOver==false){
            small[1].changeX();//allows the object to move accross the screen
            small[7].changeX();//allows the object to move accross the screen
            small[9].changeX();//allows the object to move accross the screen
          }
          if(small[1].getX1()<1200){
            g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
            g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
            g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
            if(gamePause==false && gameOver==false){
              small[2].changeX();//allows the object to move accross the screen
              small[4].changeX();//allows the object to move accross the screen
              small[6].changeX();//allows the object to move accross the screen
            }
            if(small[2].getX1()<1200){
              alienAnim[1].start();
              g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
              if(gamePause==false && gameOver==false){
                alien[1].change();//allows the object to move accross the screen
              }
              alienAnim[1].update(); 
            }
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(alien[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        alien[1].setX(1400);
        alienAnim[1] = alienMove;
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
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 12
    if(wave==13){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[1].changeX();//allows the object to move accross the screen
      }
      if(large[1].getX1()<950){
        g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          huge[1].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(huge[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[1].setX(1400);
        huge[1].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 13
    if(wave==14){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[1].changeX();//allows the object to move accross the screen
      }
      gravityUp.update();
      if(gravityU[0].getX1()<1350){
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[1].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[0].getX1()<1150){
        g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[2].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[0].getX1()<950){
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[4].changeX();//allows the object to move accross the screen
          small[3].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[1].getX1()<1350){
        g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[0].changeX();//allows the object to move accross the screen
        }
      }
      
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityU[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityU[0].setX(1400);
        gravityU[1].setX(2200);
        small[1].setX(1400);
        small[2].setX(1400);
        small[3].setX(1400);
        large[0].setX(1400);
        med[4].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 14
    if(wave==15){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      
      g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        med[0].changeX();//allows the object to move accross the screen
      }
      if(med[0].getX1()<1000){
        g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[3].changeX();//allows the object to move accross the screen
        }
        if(med[3].getX1()<1000){
          g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
          if(gamePause==false && gameOver==false){
            med[1].changeX();//allows the object to move accross the screen
          }
          if(med[1].getX1()<1000){
            g.drawImage(med[2].getPic(), med[2].getX1(), med[2].getY1(), null);
            g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
            if(gamePause==false && gameOver==false){
              med[2].changeX();//allows the object to move accross the screen
              med[4].changeX();//allows the object to move accross the screen
            }
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(med[4].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        med[0].setX(1400);
        med[1].setX(1400);
        med[2].setX(1400);
        med[3].setX(1400);
        med[4].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 15
    if(wave==16){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      
      g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[1].changeX();//allows the object to move accross the screen
      }
      if(large[1].getX1()<600){
        alienAnim[2].start();        
        g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);       
        g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[1].changeX();//allows the object to move accross the screen
          alien[2].change();//allows the object to move accross the screen
          small[1].changeX();//allows the object to move accross the screen
        }
        alienAnim[2].update(); 
        if(med[1].getX1()<600){
          g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
          if(gamePause==false && gameOver==false){
            large[2].changeX();//allows the object to move accross the screen
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(large[2].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[1].setX(1400);
        med[1].setX(1400);
        small[1].setX(1400);
        large[2].setX(1400);
        alien[2].setX(1400);
        alienAnim[2] = alienMove;
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 16
    if(wave==17){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        huge[0].changeX();//allows the object to move accross the screen
      }
      if(huge[0].getX2()<1259){
        gravityUp.start();
        g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          gravityU[0].changeX();//allows the object to move accross the screen
        }
        gravityUp.update();
        if(gravityU[0].getX1()<1150){
          g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
          if(gamePause==false && gameOver==false){
            huge[1].changeX();//allows the object to move accross the screen
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(huge[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        huge[0].setX(1400);
        huge[1].setX(1400);
        gravityU[0].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 17
    if(wave==18){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(huge[1].getPic(), huge[1].getX1(), huge[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        huge[1].changeX();//allows the object to move accross the screen
      }
      if(huge[1].getX2()<1259){
        gravityDown.start();
        g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          gravityD[0].changeX();//allows the object to move accross the screen
        }
        gravityDown.update();
        if(gravityD[0].getX1()<1150){
          g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
          if(gamePause==false && gameOver==false){
            huge[0].changeX();//allows the object to move accross the screen
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(huge[0].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        huge[0].setX(1400);
        huge[1].setX(1400);
        gravityD[0].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 18
    if(wave==19){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      alienAnim[0].start();
      alienAnim[1].start();
      alienAnim[2].start();
      g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
      g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
      g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
      if(gamePause==false && gameOver==false){
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
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(alien[2].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        alien[0].setX(1400);
        alien[1].setX(1400);
        alien[2].setX(1400);
        alienAnim[0] = alienMove;
        alienAnim[1] = alienMove;
        alienAnim[2] = alienMove;
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 19
    if(wave==20){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
      g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[0].changeX();//allows the object to move accross the screen
        med[4].changeX();//allows the object to move accross the screen
      }
      if(med[4].getX2()<1160){
        alienAnim[1].start();
        g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          alien[1].change();//allows the object to move accross the screen
        }
        alienAnim[1].update();
      }
      if(large[0].getX2()<1000){
        g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[1].changeX();//allows the object to move accross the screen
        }
        if(large[1].getX2()<1400){
          g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
          g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
          if(gamePause==false && gameOver==false){
            small[8].changeX();//allows the object to move accross the screen
            small[9].changeX();//allows the object to move accross the screen
          }
        }
      }   
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(small[9].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[0].setX(1400);
        med[4].setX(1400);
        large[1].setX(1400);
        small[8].setX(1400);
        small[9].setX(1400);
        alien[1].setX(1400);
        alienAnim[1] = alienMove;
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 20
    if(wave==21){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
      g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
      if(gamePause==false && gameOver==false){
        large[0].changeX();//allows the object to move accross the screen
        med[4].changeX();//allows the object to move accross the screen
      }
      if(med[4].getX2()<1160){
        alienAnim[1].start();
        g.drawImage(alienAnim[1].getSprite(), alien[1].getX1(), alien[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          alien[1].change();//allows the object to move accross the screen
        }
        alienAnim[1].update();
      }
      if(large[0].getX2()<1000){
        g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[1].changeX();//allows the object to move accross the screen
        }
        if(large[1].getX2()<1400){
          g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
          g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
          if(gamePause==false && gameOver==false){
            small[0].changeX();//allows the object to move accross the screen
            small[1].changeX();//allows the object to move accross the screen
          }
        }
      }  
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(small[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        large[0].setX(1400);
        med[4].setX(1400);
        large[1].setX(1400);
        small[0].setX(1400);
        small[1].setX(1400);
        alien[1].setX(1400);
        alienAnim[1] = alienMove;
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 21
    if(wave==22){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[0].getX1(), gravityD[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[0].changeX();//allows the object to move accross the screen
      }
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(), gravityU[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[1].changeX();//allows the object to move accross the screen
      }
      gravityDown.update();
      gravityUp.update();
      if(gravityD[0].getX1()<1350){
        alienAnim[0].start();
        g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          alien[0].change();//allows the object to move accross the screen
        }
        alienAnim[0].update();
      }
      if(gravityD[0].getX1()<1200){
        g.drawImage(large[2].getPic(), large[2].getX1(), large[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          large[2].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[1].getX2()<1259){
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
        g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null);
        g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null);
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);
        if(gamePause==false && gameOver==false){
          med[0].changeX();//allows the object to move accross the screen
          med[1].changeX();//allows the object to move accross the screen
          med[3].changeX();//allows the object to move accross the screen
          med[4].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(med[4].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityD[0].setX(1400);
        gravityU[1].setX(2200);
        alien[0].setX(1400);
        alienAnim[0] = alienMove;
        large[2].setX(1400);
        med[0].setX(1400);
        med[1].setX(1400);
        med[3].setX(1400);
        med[4].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 22
    if(wave==23){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture    
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(), gravityU[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[0].changeX();//allows the object to move accross the screen
      }
      gravityDown.start();
      g.drawImage(gravityDown.getSprite(), gravityD[1].getX1(), gravityD[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityD[1].changeX();//allows the object to move accross the screen
      }      
      gravityUp.update();
      gravityDown.update();
      if(gravityU[0].getX1()<1350){
        alienAnim[2].start();
        g.drawImage(alienAnim[2].getSprite(), alien[2].getX1(), alien[2].getY1(), null);
        if(gamePause==false && gameOver==false){
          alien[2].change();//allows the object to move accross the screen
        }
        alienAnim[2].update();
      }
      if(gravityU[0].getX1()<1200){
        g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[1].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityU[0].getX1()<1100){
        g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[6].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[1].getX1()<1350){
        alienAnim[0].start();
        g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          alien[0].change();//allows the object to move accross the screen
        }
        alienAnim[0].update();
      }
      if(gravityD[1].getX1()<1200){
        g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[3].changeX();//allows the object to move accross the screen
        }
      }
      if(gravityD[1].getX1()<1100){
        g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[9].changeX();//allows the object to move accross the screen
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(gravityD[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityU[0].setX(1400);
        gravityD[1].setX(2200);
        alien[0].setX(1400);
        alienAnim[0] = alienMove;
        alien[2].setX(1400);
        alienAnim[2] = alienMove;
        small[1].setX(1400);
        small[6].setX(1400);
        small[3].setX(1400);
        small[9].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 23
    if(wave==24){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      gravityUp.start();
      g.drawImage(gravityUp.getSprite(), gravityU[0].getX1(),  gravityU[0].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[0].changeX();//allows the object to move accross the screen
      }
      g.drawImage(gravityUp.getSprite(), gravityU[1].getX1(),  gravityU[1].getY1(), null);
      if(gamePause==false && gameOver==false){
        gravityU[1].changeX();//allows the object to move accross the screen
      }
      gravityUp.update();
      if(gravityU[0].getX1()<1300){
        g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
        if(gamePause==false && gameOver==false){
          small[0].changeX();//allows the object to move accross the screen
        }
        if(small[0].getX2()<1400){
          g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null);
          if(gamePause==false && gameOver==false){
            med[0].changeX();//allows the object to move accross the screen
          }
          if(med[0].getX2()<1400){
            g.drawImage(large[0].getPic(), large[0].getX1(), large[0].getY1(), null);
            if(gamePause==false && gameOver==false){
              large[0].changeX();//allows the object to move accross the screen
            }
            if(large[0].getX2()<1400){
              g.drawImage(huge[0].getPic(), huge[0].getX1(), huge[0].getY1(), null);
              if(gamePause==false && gameOver==false){
                huge[0].changeX();//allows the object to move accross the screen
              }
            }
          }
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(huge[0].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        gravityU[0].setX(1400);
        gravityU[1].setX(2200);
        small[0].setX(1400);
        med[0].setX(1400);
        large[0].setX(1400);
        huge[0].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 24
    if(wave==25){
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initially paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(small[0].getPic(), small[0].getX1(), small[0].getY1(), null);
      g.drawImage(small[1].getPic(), small[1].getX1(), small[1].getY1(), null);
      g.drawImage(small[2].getPic(), small[2].getX1(), small[2].getY1(), null);
      g.drawImage(small[3].getPic(), small[3].getX1(), small[3].getY1(), null);
      g.drawImage(small[4].getPic(), small[4].getX1(), small[4].getY1(), null);
      g.drawImage(small[6].getPic(), small[6].getX1(), small[6].getY1(), null);
      g.drawImage(small[7].getPic(), small[7].getX1(), small[7].getY1(), null);
      g.drawImage(small[8].getPic(), small[8].getX1(), small[8].getY1(), null);
      g.drawImage(small[9].getPic(), small[9].getX1(), small[9].getY1(), null); 
      if(gamePause==false && gameOver==false){
        small[0].changeX();//allows the object to move accross the screen
        small[1].changeX();//allows the object to move accross the screen
        small[2].changeX();//allows the object to move accross the screen
        small[3].changeX();//allows the object to move accross the screen
        small[4].changeX();//allows the object to move accross the screen
        small[6].changeX();//allows the object to move accross the screen
        small[7].changeX();//allows the object to move accross the screen
        small[8].changeX();//allows the object to move accross the screen
        small[9].changeX();//allows the object to move accross the screen
      }
      if(small[9].getX2()<1300){
        g.drawImage(med[0].getPic(), med[0].getX1(), med[0].getY1(), null); 
        g.drawImage(med[1].getPic(), med[1].getX1(), med[1].getY1(), null); 
        g.drawImage(med[3].getPic(), med[3].getX1(), med[3].getY1(), null); 
        g.drawImage(med[4].getPic(), med[4].getX1(), med[4].getY1(), null);  
        if(gamePause==false && gameOver==false){
          med[0].changeX();//allows the object to move accross the screen
          med[1].changeX();//allows the object to move accross the screen
          med[3].changeX();//allows the object to move accross the screen
          med[4].changeX();//allows the object to move accross the screen
        }
        if(med[4].getX2()<1100){
          alienAnim[0].start();
          g.drawImage(alienAnim[0].getSprite(), alien[0].getX1(), alien[0].getY1(), null);
          g.drawImage(large[1].getPic(), large[1].getX1(), large[1].getY1(), null);
          if(gamePause==false && gameOver==false){
            large[1].changeX();//allows the object to move accross the screen
            alien[0].change();//allows the object to move accross the screen
          }
          alienAnim[0].update();
        }
      }
      animation.start();//starts Spaceship animation
      g.drawImage(animation.getSprite(), spaceshipX, spaceshipY, null);//draws Spaceship object
      animation.update();//updates Spaceship animation
      g.setFont(new Font("Tahoma", Font.BOLD, 50));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString("Waves Completed: "+ Integer.toString(score),scoreX,scoreY);//display score
      if(large[1].getX2()<0){
        wave=0;//if the wave is over, reset all object variabels and go to wave 0 to then move on to a random wave
        small[0].setX(1400);
        small[1].setX(1400);
        small[2].setX(1400);
        small[3].setX(1400);
        small[4].setX(1400);
        small[6].setX(1400);
        small[7].setX(1400);
        small[8].setX(1400);
        small[9].setX(1400);
        med[0].setX(1400);
        med[1].setX(1400);
        med[3].setX(1400);
        med[4].setX(1400);
        alien[0].setX(1400);
        alienAnim[0] = alienMove;
        large[1].setX(1400);
        score++;//add 1 to score
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
        g.drawImage(playagain, (width/2)-(487/2), 400, null);//draw restart level button
        g.drawImage(quit, (width/2)-(168/2), 500, null);//draw quit button
        if(once){//get the highscore of the program once
          text.getHighscore(playerName,score);
          once = false;//change to false to stop repetition
        }
      }
    }//end of Wave 25
  }//end of PaintComponet method
}//end of SurvivalMode class