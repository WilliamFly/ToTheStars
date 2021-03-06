/*Spaceship class
 * Author: William Mucha
 * Class Created: 1/2/2017
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to make a Spaceship object and be able to manipulate its coordinates
 * Imports used in the Spaceship class*/
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//Spaceship class
public class Spaceship{
//Variables
  //Initial coordinates of the Spaceship object
  public int spaceshipX=235;//left side of Spaceship object
  public int spaceshipX2=(spaceshipX+140);//right side of Spaceship object
  public int spaceshipY=265;//top side of Spaceship object
  public int spaceshipY2=(spaceshipY+80);//bottom side of Spaceship object
  public boolean canMove=true;//boolean to be able to start and stop the spaceship from being able to move    
  private static BufferedImage spriteSheet;//BufferedImage to save Spaceship sprite sheet
  private static final int WIDTH=140;//width of Spaceship sprite sheet
  private static final int HEIGHT=80;//height of Spaceship sprite sheet
//Methods
  public int getX1(){return spaceshipX;}//getX1 method that returns the spaceshipX variable (as an int)
  public int getX2(){return spaceshipX2;}//getX2 method that returns the spaceshipX2 variable (as an int)
  public int getY1(){return spaceshipY;}//getY1 method that returns the spaceshipY variable (as an int)
  public int getY2(){return spaceshipY2;}//getY2 method that returns the spaceshipY2 variable (as an int)
  /*getMove method
   * Precondition: able = boolean received
   * Postcondition: canMove = boolean received, changing the variable and the ships ability to move
   *Purpose: To receive and change the ability for the ship to be able to move by the user*/
  public void getMove(boolean able){canMove = able;}//end of getMove method
  public boolean canMove(){return canMove;}//canMove method that returns the canMove variable (as an boolean)
  /*loadSprite method
   * Precondition: file = name of the sprite sheet (png file) for the SpaceShip object
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
      spriteSheet = loadSprite("spaceship");//loads the Spaceship sprite sheet automatically if no other sprites have been used
    }
    return spriteSheet.getSubimage(xGrid * WIDTH, yGrid * HEIGHT, WIDTH, HEIGHT);
  }//end of getSprite method
}//end of Spaceship class