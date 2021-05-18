/*GravityUp class
 * Author: William Mucha
 * Class Created: 1/2/2017
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to make a GravityUp object and be able to manipulate its coordinates and speed
 * Imports used in the GravityUp class*/
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//GravityUp class
public class GravityUp{
//Variables
  //Initial dimensions of the GravityUp object
  private int gravityx1=0;//left side of GravityUp object
  private int gravityx2=600;//right side of GravityUp object
  private int gravityy1=0;//top side of GravityUp object
  private int gravityy2=800;//bottom side of GravityUp object
  private int speed=2;//speed of GravityUp object
  private static BufferedImage spriteSheet;//BufferedImage to save GravityUp sprite sheet
  private static final int WIDTH=600;//width of GravityUp sprite sheet
  private static final int HEIGHT=800;//height of GravityUp sprite sheet
//Constructor
  public GravityUp(int x, int y){//x and y are parameters given to initialy give the starting coordinates for a GravityUp object
    //Shifting GravityUp object to given coordinates
    gravityx1+=x;
    gravityx2+=x;
    gravityy1+=y;
    gravityy2+=y;
  }//end of constructor
//Methods
  public int getX1(){return gravityx1;}//getX1 method that returns the gravityx1 variable (as an int)
  public int getX2(){return gravityx2;}//getX2 method that returns the gravityx2 variable (as an int)
  public int getY1(){return gravityy1;}//getY1 method that returns the gravityy1 variable (as an int)
  public int getY2(){return gravityy2;}//getY2 method that returns the gravityy2 variable (as an int)
  /*setX method 
   * Precondition: num >=1400 (1400 = width of the panel)
   * Postcondition: - gravityx1 will take the value of the parameter num
   *                      - gravityx2 will take the value of the parameter num + 600
   *Purpose: to reset the x values of GravityUp objects after a wave is over to reuse them for futur waves to come*/
  public void setX(int num){
    gravityx1=num;
    gravityx2=600+num;
  }//end of setX method
  /*changeX method
   * Postcondition: gravityx1 and gravityx2 will decrease by the speed
   *Purpose: to be called over and over to see the GravityUp move on the screen while a wave is running*/
  public void changeX(){
    gravityx1-=speed;
    gravityx2-=speed;
  }//end of changeX method
  public void addSpeed(){speed++;}//addSpeed method that adds 1 to the speed variable
  public void removeSpeed(){speed++;}//removeSpeed method that removes 1 to the speed variable
  /*loadSprite method
   * Precondition: file = name of the sprite sheet (png file) for the GravityUp object
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
      spriteSheet = loadSprite("gravityup");//loads the GravityUp sprite sheet automatically if no other sprites have been used
    }
    return spriteSheet.getSubimage(xGrid * WIDTH, yGrid * HEIGHT, WIDTH, HEIGHT);
  }//end of getSprite method
}//end of GravityUp class