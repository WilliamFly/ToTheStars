/*Alien class
 * Author: William Mucha
 * Class Created: 1/2/2017
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to make an Alien object and be able to manipulate its coordinates and speed
 * Imports used in the Alien class*/
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//Alien class
public class Alien{
//Variables
  //Initial dimensions of the Alien
  private int alienx1=0;//left side of Alien object
  private int alienx2=400;//right side of Alien object 
  private int alieny1=0;//top side of Alien object
  private int alieny2=250;//bottom side of Alien object
  public int speed=2;//speed of Alien 
  private boolean up=true;//Up boolean to check and change if alien is moving up and down
  private static BufferedImage spriteSheet;//BufferedImage to save Alien sprite sheet
  private static final int WIDTH=400;//width of Alien sprite sheet
  private static final int HEIGHT=250;//height of Alien sprite sheet
//Constructor
  public Alien(int x, int y){//x and y are parameters given to initialy give the starting coordinates for an Alien object
    //Shifting Alien object to given coordinates
    alienx1+=x;
    alienx2+=x;
    alieny1+=y;
    alieny2+=y;
  }//end of constructor
//Methods
  public int getX1(){return alienx1;}//getX1 method that returns the alienx1 variable (as an int)
  public int getX2(){return alienx2;}//getX2 method that returns the alienx2 variable (as an int)
  public int getY1(){return alieny1;}//getY1 method that returns the alieny1 variable (as an int)
  public int getY2(){return alieny2;}//getY2 method that returns the alieny2 variable (as an int)
  /*setX method 
   * Precondition: num >=1400 (1400 = width of the panel)
   * Postcondition: - alienx1 will take the value of the parameter num
   *                      - alienx2 will take the value of the parameter num + 400
   *Purpose: to reset the x values of Alien objects after a wave is over to reuse them for futur waves to come*/
  public void setX(int num){
    alienx1= num;
    alienx2=400+num;
  }//end of setX method
  /*setY method
   * Precondition: 0<=num<=2 (number of Alien objects created,3)
   * Postcondition: - alieny1 will take the value of the parameter (num*200) +100
   *                       - alieny2 will take the value of the parameter (num*200) + 350
   *                       - boolean up will be true to reset the direction the Alien will be moving
   *Purpose: to reset the y values of Alien objects after a wave is over to reuse them for futur waves to come*/
  public void setY(int num){
    alieny1=100+(num*200);
    alieny2=350+(num*200);
    up = true;
  }//end of setY method;
  /*change method
   * Postcondition: - alienx1, alienx2, alieny1 and alieny2 will decrease by the speed
   *                       - alieny1 and alieny2 will increase by the speed only if boolean up is false
   *                       - boolean up will turn false if Alien is at top of the screen and turn true if Alien is at bottom of the screen
   *Purpose: to be called over and over to see the Alien move on the screen while a wave is running*/
  public void change(){
    alienx1-=speed;
    alienx2-=speed;
    if(up){
      alieny1-=speed;
      alieny2-=speed;    
    } else{
      alieny1+=speed;
      alieny2+=speed;
    }
    if(alieny1<=0){
      up=false;
    }
    if(alieny2>=800){
      up=true;
    }
  }//end of change method;
  public void addSpeed(){speed++;}//addSpeed method that adds 1 to the speed variable
  public void removeSpeed(){speed++;}//removeSpeed method that removes 1 to the speed variable
  /*loadSprite method
   * Precondition: file = name of the sprite sheet (png file) for the Alien object
   * Postcondition: - returns the sprite sheet as a BufferedImage to be useable in the program
   *Purpose: To load sprite sheets to be used for moving objects*/
  public static BufferedImage loadSprite(String file) {
    BufferedImage sprite = null;
    try {
      sprite = ImageIO.read(new File(file + ".png"));//automatically assumes its a png file
    } catch (IOException e) {
      e.printStackTrace();//prints out errror stream to determine where error can be found
    }
    return sprite;//returns sprite sheet
  }//end of loadSprite method
  /*getSprite method
   * Precondition: - xGrid = x section of the sprite sheet
   *                     - yGrid = y section of the sprite sheet
   * Postcondition: - returns the specific sprite animation on the sprite sheet from the loadSprite method
   *Purpose: To retrieve specific animations on a sprite sheet*/
  public static BufferedImage getSprite(int xGrid, int yGrid) {
    if (spriteSheet == null) {
      spriteSheet = loadSprite("alien");//loads the Alien sprite sheet automatically if no other sprites have been used
    }
    return spriteSheet.getSubimage(xGrid * WIDTH, yGrid * HEIGHT, WIDTH, HEIGHT);
  }//end of getSprite method
}//end of Alien class