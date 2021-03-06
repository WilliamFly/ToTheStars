/*MainMenu class
 * Author: William Mucha
 * Class Created: 12/20/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to run all the necessary code to display the main menu lobby of the game
 * Import used in the MainMenu class*/
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
//MainMenu class which is a child class to the Lobbies class and that also implements the MouseListener interface
public class MainMenu extends Lobbies implements MouseListener{
//Variables
  //Images used in the MainMenu lobby
  Image background;
  Image title;//main menu title
  Image start;//start button
  Image instructions;//instructions button
  Image highscores;//highscores button
  Image exit;//exit button
  Image instructionstitle;//instructions title
  Image highscorestitle;//highscores title
  Image back;//back button
  Image gamemode;//gamemode title
  Image story;//story button
  Image survival;//survival button
  Image areyousure;//areyousure title
  Image yes;//yes button
  Image no;//no button
  Image one;//number 1
  Image two;//number 2
  Image three;//number 3
  Image completions;//word completions
  Image storyt1;//story text line 1
  Image storyt2;//story text line 2
  Image survivalt1;//survival text line 1
  Image survivalt2;//survival text line 2
  Image next;//next button
  Image last;//last button
  Image instrt1;//instructions text line 1
  Image instrt2;//instructions text line 2
  Image instrt3;//instructions text line 3
  Image instrt4;//instructions text line 4
  Image instrt5;//instructions text line 5
  Image instrt6;//instructions text line 6
  Image instrt7;//instructions text line 7
  Image instrt8;//instructions text line 8
  Image instrt9;//instructions text line 9
  Image instrt10;//instructions text line 10
  Image instrt11;//instructions text line 11
  Image instrt12;//instructions text line 12
  Image instrt13;//instructions text line 13
  int lobbyNum=0;//int to determine which lobby to draw
  boolean survivalMode=false;//boolean to determine whether to start survival mode
  boolean storyMode=false;//boolean to determine whether to start story mode
  //Initialization of Spaceship Animations 
  BufferedImage[] still = {Spaceship.getSprite(0, 0)};
  BufferedImage[] dead = {Spaceship.getSprite(0, 2)};
  Animation stillAnim = new Animation(still, 1);
  Animation deadAnim = new Animation(dead, 1);
  //Initialization of Asteroid Objects (of all sizes) 
  AsteroidSmall small = new AsteroidSmall(0,0);
  AsteroidMed med = new AsteroidMed(0,0);
  AsteroidLarge large = new AsteroidLarge(0,0);
  AsteroidHuge huge = new AsteroidHuge(0,0);
  //Initialization of Alien Animations 
  BufferedImage[] alienAlive = {Alien.getSprite(0, 0),};
  BufferedImage[] alienDone = {Alien.getSprite(0, 2)};
  Animation alienLive = new Animation(alienAlive,20);
  Animation alienDead = new Animation(alienDone,1);
  //Initialization of GravityUp Animations 
  BufferedImage[] gravityMoveUP = {GravityUp.getSprite(0, 0)};
  Animation gravityUP = new Animation(gravityMoveUP,20);
  //Initialization of GravityDown Animations 
  BufferedImage[] gravityMoveDOWN = {GravityDown.getSprite(0, 0)};
  Animation gravityDOWN = new Animation(gravityMoveDOWN,20);
//Methods
  /*mouseClicked method
   *Purpose: used to check and see if the user has clicked the mouse on a certain area on the screen*/
  public void mouseClicked(MouseEvent e) {
    int x=e.getX();//x coordinate of mouse on screen
    int y=e.getY();//y coordinate of mouse on screen
    if(lobbyNum==0){//if user is in main menu lobby
      if(x >= ((WIDTH / 2) - (176/ 2)) && x<= ((WIDTH / 2) + (176/ 2)) && y>= 310 && y<= 368){//if user clicks start
        lobbyNum=1;//go to gamemode lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= ((WIDTH / 2) - (414/ 2)) && x<= ((WIDTH / 2) + (414/ 2)) && y>= 410 && y<= 469){//if user clicks instructions
        lobbyNum=2;//go to instructions lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= ((WIDTH / 2) - (365/ 2)) && x<= ((WIDTH / 2) + (365/ 2)) && y>= 510 && y<= 569){//if user clicks highscores
        lobbyNum=3;//go to highscores lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= ((WIDTH / 2) - (134/ 2)) && x<= ((WIDTH / 2) + (134/ 2)) && y>= 610 && y<= 669){//if user clicks exit
        lobbyNum=4;//go to are you sure lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in main menu lobby
    if(lobbyNum==1){//if user is in gamemode lobby
      if(x >= (((WIDTH - (WIDTH/4))) - (395/ 2)) && x<= (((WIDTH - (WIDTH/4))) + (395/ 2)) && y>= 250 && y<= 328){//is user clicks survival mode
        survivalMode = true;//activate survival mode
      }
      if(x >= ((WIDTH/4) - (266/ 2)) && x<= ((WIDTH/4) + (266/ 2)) && y>= 250 && y<= 329){//is user clicks story mode
        storyMode = true;//activate story mode
      }
    }//end of if user is in gamemode lobby
    if(lobbyNum==1 || lobbyNum==2 || lobbyNum==3 || lobbyNum==6 || lobbyNum==7 || lobbyNum==8){//if user is in any lobby that has a back image
      if(x >= ((WIDTH / 2) - (211/ 2)) && x<= ((WIDTH / 2) + (211/ 2)) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+79)){//if user clicks back
        lobbyNum=0;//return to main meny lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in any lobby that has a back image
    if(lobbyNum==4){//if user is in are you sure lobby
      if(x >= (((WIDTH - (WIDTH/4))) - (173/ 2)) && x<= (((WIDTH - (WIDTH/4))) + (173/ 2)) && y>= (HEIGHT/2) && y<= ((HEIGHT/2)+110)){//if user clicks no
        lobbyNum=0;//return to main menu lobby
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= ((WIDTH/4) - (239/ 2)) && x<= ((WIDTH/4) + (239/ 2)) && y>= (HEIGHT/2) && y<= ((HEIGHT/2)+110)){//if user clicks yes
        lobbyNum=5;//exit the game
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in are you sure lobby
    if(lobbyNum==2){//if user is in instructions lobby 1
      if(x >= (((WIDTH / 2) - (211/ 2))+400) && x<= (((WIDTH / 2) - (211/ 2))+400+192) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+72)){//if user clicks next
        lobbyNum=6;//go to instructions lobby 2
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues  
      }
    }//end of if user is in instructions lobby 1
    if(lobbyNum==6){//if user is in instructions lobby 2
      if(x >= (((WIDTH / 2) - (211/ 2))+400) && x<= (((WIDTH / 2) - (211/ 2))+400+192) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+72)){//if user clicks next
        lobbyNum=7;//go to instructions lobby 3
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= (((WIDTH / 2) - (211/ 2))-400) && x<= (((WIDTH / 2) - (211/ 2))-400+180) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+73)){//if user clicks last
        lobbyNum=2;//return to instructions lobby 1
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in instructions lobby 2
    if(lobbyNum==7){//if user is in instructions lobby 3
      if(x >= (((WIDTH / 2) - (211/ 2))+400) && x<= (((WIDTH / 2) - (211/ 2))+400+192) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+72)){//if user clicks next
        lobbyNum=8;//go to instructions lobby 4
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
      if(x >= (((WIDTH / 2) - (211/ 2))-400) && x<= (((WIDTH / 2) - (211/ 2))-400+180) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+73)){//if user clicks last
        lobbyNum=6;//return to instructions lobby 2
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in instructions lobby 3
    if(lobbyNum==8){//if user is in instructions lobby 4
      if(x >= (((WIDTH / 2) - (211/ 2))-400) && x<= (((WIDTH / 2) - (211/ 2))-400+180) && y>= (HEIGHT-190) && y<= ((HEIGHT-190)+73)){//if user clicks last
        lobbyNum=7;//return to instructions lobby 3
        repaint();//repaint the screen with changes;
        x=0;//resetting x to 0 to minimize clicking issues
        y=0;//resetting y to 0 to minimize clicking issues
      }
    }//end of if user is in instructions lobby 4
  }//end of mouseClicked method
  //Methods that had to be overriden but have not been used in the program
  public void mousePressed(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
  /*getImages method
   * Postcondition: all Image variables have assigned png or gif file saved into them
   *Purpose: to get the image used in the MainMenu class and save it*/
  public void getImages(){ 
    BufferedImage img = null;//creating dummy variable
    try {
      img = ImageIO.read(new File("background.png"));//putting image into dummy variable
    } catch (IOException e) {}
    background = img;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img1 = null;//creating dummy variable
    try {
      img1 = ImageIO.read(new File("title.png"));//putting image into dummy variable
    } catch (IOException e) {}
    title = img1;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img2 = null;//creating dummy variable
    try {
      img2 = ImageIO.read(new File("start.png"));//putting image into dummy variable
    } catch (IOException e) {}
    start = img2;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img3 = null;//creating dummy variable
    try {
      img3 = ImageIO.read(new File("instructions.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instructions = img3;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img4 = null;//creating dummy variable
    try {
      img4 = ImageIO.read(new File("highscores.png"));//putting image into dummy variable
    } catch (IOException e) {}
    highscores = img4;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img5 = null;//creating dummy variable
    try {
      img5 = ImageIO.read(new File("exit.png"));//putting image into dummy variable
    } catch (IOException e) {}
    exit = img5;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img6 = null;//creating dummy variable
    try {
      img6 = ImageIO.read(new File("instructionstitle.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instructionstitle = img6;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img7 = null;//creating dummy variable
    try {
      img7 = ImageIO.read(new File("highscorestitle.png"));//putting image into dummy variable
    } catch (IOException e) {}
    highscorestitle = img7;
    BufferedImage img8 = null;//creating dummy variable
    try {
      img8 = ImageIO.read(new File("back.png"));//putting image into dummy variable
    } catch (IOException e) {}
    back = img8;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img9 = null;//creating dummy variable
    try {
      img9 = ImageIO.read(new File("gamemode.png"));//putting image into dummy variable
    } catch (IOException e) {}
    gamemode = img9;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img10 = null;//creating dummy variable
    try {
      img10 = ImageIO.read(new File("story.png"));//putting image into dummy variable
    } catch (IOException e) {}
    story = img10;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img11 = null;//creating dummy variable
    try {
      img11 = ImageIO.read(new File("survival.png"));//putting image into dummy variable
    } catch (IOException e) {}
    survival = img11;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img12 = null;//creating dummy variable
    try {
      img12 = ImageIO.read(new File("are-you-sure.png"));//putting image into dummy variable
    } catch (IOException e) {}
    areyousure = img12;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img13 = null;//creating dummy variable
    try {
      img13 = ImageIO.read(new File("yes.png"));//putting image into dummy variable
    } catch (IOException e) {}
    yes = img13;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img14 = null;//creating dummy variable
    try {
      img14 = ImageIO.read(new File("no.png"));//putting image into dummy variable
    } catch (IOException e) {}
    no = img14;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img15 = null;//creating dummy variable
    try {
      img15 = ImageIO.read(new File("1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    one = img15;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img16 = null;//creating dummy variable
    try {
      img16 = ImageIO.read(new File("2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    two = img16;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img17= null;//creating dummy variable
    try {
      img17 = ImageIO.read(new File("3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    three = img17;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img18= null;//creating dummy variable
    try {
      img18 = ImageIO.read(new File("completions.png"));//putting image into dummy variable
    } catch (IOException e) {}
    completions = img18;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img19= null;//creating dummy variable
    try {
      img19 = ImageIO.read(new File("survivalt1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    survivalt1 = img19;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img20= null;//creating dummy variable
    try {
      img20 = ImageIO.read(new File("survivalt2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    survivalt2 = img20;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img21= null;//creating dummy variable
    try {
      img21 = ImageIO.read(new File("storyt1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    storyt1 = img21;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img22= null;//creating dummy variable
    try {
      img22 = ImageIO.read(new File("storyt2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    storyt2 = img22;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img23= null;//creating dummy variable
    try {
      img23 = ImageIO.read(new File("instrt1.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt1 = img23;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img24= null;//creating dummy variable
    try {
      img24 = ImageIO.read(new File("instrt2.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt2 = img24;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img25= null;//creating dummy variable
    try {
      img25 = ImageIO.read(new File("next.png"));//putting image into dummy variable
    } catch (IOException e) {}
    next = img25;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img26= null;//creating dummy variable
    try {
      img26 = ImageIO.read(new File("last.png"));//putting image into dummy variable
    } catch (IOException e) {}
    last = img26;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img27= null;//creating dummy variable
    try {
      img27 = ImageIO.read(new File("instrt3.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt3 = img27;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img28= null;//creating dummy variable
    try {
      img28 = ImageIO.read(new File("instrt4.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt4 = img28;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img29= null;//creating dummy variable
    try {
      img29 = ImageIO.read(new File("instrt5.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt5 = img29;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img30= null;//creating dummy variable
    try {
      img30 = ImageIO.read(new File("instrt6.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt6 = img30;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img31= null;//creating dummy variable
    try {
      img31 = ImageIO.read(new File("instrt7.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt7 = img31;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img32= null;//creating dummy variable
    try {
      img32 = ImageIO.read(new File("instrt8.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt8 = img32;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img33= null;//creating dummy variable
    try {
      img33 = ImageIO.read(new File("instrt9.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt9 = img33;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img34= null;//creating dummy variable
    try {
      img34 = ImageIO.read(new File("instrt10.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt10 = img34;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img35= null;//creating dummy variable
    try {
      img35 = ImageIO.read(new File("instrt11.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt11 = img35;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img36= null;//creating dummy variable
    try {
      img36 = ImageIO.read(new File("instrt12.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt12 = img36;//setting Image variable to dummy variable once image has been saved into dummy variable
    BufferedImage img37= null;//creating dummy variable
    try {
      img37 = ImageIO.read(new File("instrt13.png"));//putting image into dummy variable
    } catch (IOException e) {}
    instrt13 = img37;//setting Image variable to dummy variable once image has been saved into dummy variable
    small.getAsteroid();//getting images from AsteroidSmall class
    med.getAsteroid();//getting images from AsteroidMed class
    large.getAsteroid();//getting images from AsteroidLarge class
    huge.getAsteroid();//getting images from AsteroidHuge class
  }//end of getImage() method
  /*getLobby method 
   * Precondition: lobby is an int that is between 0 and 8 inclusive (number of lobbies created)
   * Postcondition: lobbyNum = parameter given (lobby)
   *Purpose: to set the lobbyNum*/
  public void getLobby(int lobby){lobbyNum=lobby;}//end of getLobby method
  public int lobby(){return lobbyNum;}//lobby method that returns the lobbyNum1 variable (as an int)
  public boolean survivalMode(){return survivalMode;}//survivalMode method that returns the survivalMode variable (as an boolean)
  public boolean storyMode(){return storyMode;}//storyMode method that returns the storyMode variable (as an boolean)
  /*paintComponent method 
   * Postcondition: paints lobby depending on lobbyNum
   *Purpose: to paint all of the graphics onto the JFrame with all the images, objects and text*/
  public void paintComponent(Graphics g){  
    if(lobbyNum==0){//paint Main Menu Lobby
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(title, ((width / 2) - (897/ 2)), 150,null);//draws title
      g.drawImage(start, ((width / 2) - (176/ 2)), 310,null);//draws start button
      g.drawImage(instructions, ((width / 2) - (414/ 2)), 410,null);//draws instructions button
      g.drawImage(highscores, ((width / 2) - (365/ 2)), 510,null);//draws highscores button
      g.drawImage(exit, ((width / 2) - (134/ 2)), 610,null);//draws exit button
    }//end of paint Main Menu Lobby
    if(lobbyNum==1){//paint Gamemode Lobby
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(gamemode, ((width / 2) - (560/ 2)), 100,null);//draws gamemode title
      g.drawImage(story, ((width / 4) - (266/ 2)),250,null);//draws story button
      g.drawImage(storyt1, ((width / 4) - (425/ 2)),375,null);//draws story text line 1
      g.drawImage(storyt2, ((width / 4) - (407/ 2)),450,null);//draws story text line 2
      g.drawImage(survival, (((width - (width/4))) - (395/ 2)),250,null);//draws survival button
      g.drawImage(survivalt1, (((width - (width/4))) - (432/ 2)),375,null);//draws survival text line 1
      g.drawImage(survivalt2, (((width - (width/4))) - (344/ 2)),450,null);//draws survival text line 2
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
    }//end of paint Gamemode Lobby
    if(lobbyNum==2){//paint Instructions Lobby 1
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(instructionstitle, ((width / 2) - (747/ 2)), 100,null);//draws instructions title
      g.drawImage(instrt1, 200, 240,null);//draws instructions text line 1
      g.drawImage(instrt2, 200, 500,null);//draws instructions text line 2
      g.drawImage(stillAnim.getSprite(), 300, 350, null);//draws spaceship
      g.drawImage(instrt3, 700, 270,null);//draws instructions text line 3
      g.drawImage(instrt4, 700, 370,null);//draws instructions text line 4
      g.drawImage(instrt5, 700, 470,null);//draws instructions text line 5
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
      g.drawImage(next, ((width / 2) - (211/ 2))+400, height-190,null);//draws next button
    }//end of paint Instructions Lobby 1
    if(lobbyNum==3){//paint Highscores Lobby
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(highscorestitle, ((width / 2) - (644/ 2)), 100,null);//draws highscores title
      g.drawImage(story, ((width / 4) - (266/ 2)),250,null);//draws story subtitle
      g.drawImage(survival, (((width - (width/4))) - (395/ 2)),250,null);//draws survival subtitle
      g.drawImage(one, 800,350,null);//draws number 1
      g.drawImage(two, 800,425,null);//draws number 2
      g.drawImage(three, 800,500,null);//draws number 3
      g.drawImage(completions, 120,400,null);//draws completions word
      g.setFont(new Font("Tahoma", Font.PLAIN, 40));//set font
      g.setColor(Color.WHITE);//set color
      g.drawString(TextFile.getStoryCompletions(), 490, 439);//draws number of story completions
      g.setFont(new Font("Tahoma", Font.PLAIN, 30));//resets font
      g.setColor(Color.YELLOW);//resets color
      g.drawString(TextFile.getSurvivalName1()+": "+TextFile.getSurvivalScore1(), 875, 380);//draws highscore 1
      g.drawString(TextFile.getSurvivalName2()+": "+TextFile.getSurvivalScore2(), 875, 455);//draws highscore 2
      g.drawString(TextFile.getSurvivalName3()+": "+TextFile.getSurvivalScore3(), 875, 530);//draws highscore 3
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
    }//end of paint Highscores Lobby
    if(lobbyNum==4){//paint Are You Sure Lobby
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(areyousure, ((width / 2) - (795/ 2)), 150,null);//draws are you sure title
      g.drawImage(yes, ((width / 4) - (239/ 2)),height/2,null);//draws yes button
      g.drawImage(no, (((width - (width/4))) - (173/ 2)),height/2,null);//draws no button
    }//end of paint Are You Sure Lobby
    if(lobbyNum==5){System.exit(0);}//Closes the game if this lobby is selected
    if(lobbyNum==6){//paint Instructions Lobby 2
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(small.getPic(), 100, 200, null);//draws small asteroid
      g.drawImage(med.getPic(), 150, 200, null);//draws medium asteroid
      g.drawImage(large.getPic(), 320, 200, null);//draws large asteroid
      g.drawImage(huge.getPic(), 790, 200, null);//draws huge asteroid
      g.drawImage(instructionstitle, ((width / 2) - (747/ 2)), 100,null);//draws instructions title
      g.drawImage(instrt6, ((width / 2) - (780/ 2)), 390,null);//draws instructions text line 6
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
      g.drawImage(next, ((width / 2) - (211/ 2))+400, height-190,null);//draws next button
      g.drawImage(last, ((width / 2) - (211/ 2))-400, height-190,null);//draws last button
    }//end of paint Instructions Lobby 2
    if(lobbyNum==7){//paint Instructions Lobby 3
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(gravityDOWN.getSprite(), 50, 0, null);//draws gravitydown sprite
      g.drawImage(gravityUP.getSprite(), 750, 0, null);//draws gravityup sprite
      g.drawImage(instrt7, ((width / 2) - (766/ 2)), 290,null);//draws instructions text line 7
      g.drawImage(instrt8, ((width / 2) - (454/ 2)), 390,null);//draws instructions text line 8
      g.drawImage(instrt9, ((width / 2) - (417/ 2)), 490,null);//draws instructions text line 9
      g.drawImage(instructionstitle, ((width / 2) - (747/ 2)), 100,null);//draws instructions title
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
      g.drawImage(next, ((width / 2) - (211/ 2))+400, height-190,null);//draws next button
      g.drawImage(last, ((width / 2) - (211/ 2))-400, height-190,null);//draws last button
    }//end of paint Instructions Lobby 3
    if(lobbyNum==8){//paint Instructions Lobby 4
      int width = getWidth();//gets the width of the frame
      int height = getHeight();//gets the height of the frame
      super.paintComponent(g);//initialy paints the background black
      g.drawImage(background, 0,0,null);//then puts the background picture
      g.drawImage(deadAnim.getSprite(), 50, 370, null);//draws dead ship sprite
      g.drawImage(alienLive.getSprite(), 190, 320, null);//draws alive alien sprite
      g.drawImage(stillAnim.getSprite(), 950, 240, null);//draws alive ship sprite
      g.drawImage(alienDead.getSprite(), 900, 320, null);//draws dead alien sprite
      g.drawImage(instrt10, 250, 200,null);//draws instructions text line 10
      g.drawImage(instrt11, 220, 260,null);//draws instructions text line 11
      g.drawImage(instrt12, 600, 350,null);//draws instructions text line 12
      g.drawImage(instrt13, 600, 425,null);//draws instructions text line 13
      g.drawImage(instructionstitle, ((width / 2) - (747/ 2)), 100,null);//draws instructions title
      g.drawImage(back, ((width / 2) - (211/ 2)), height-190,null);//draws back button
      g.drawImage(last, ((width / 2) - (211/ 2))-400, height-190,null);//draws last button
    }//end of paint Instructions Lobby 4
  }//end of paintComponent() method  
}//end of Main menu class