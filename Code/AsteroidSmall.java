/*AsteroidSmall class
 * Author: William Mucha
 * Class Created: 12/30/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to make an asteroid object of size small and be able to manipulate its coordinates and speed
 * Imports used in the AsteroidSmall class*/
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//AsteroidSmall clas
public class AsteroidSmall{
//Variables
  Image asteroidSmall;//Image variable for the AsteroidSmall object
  //Initial dimensions of the AsteroidSmall object
  private int asteroidx1 =0;//left side of the AsteroidSmall object
  private int asteroidx2 = 40;//right side of the AsteroidSmall object
  private int asteroidy1 =0;//top side of the AsteroidSmall object
  private int asteroidy2 = 40;//bottom side of the AsteroidSmall object
  private int speed = 2;//speed the AsteroidSmall object
//Constructor
  public AsteroidSmall(int x, int y){//x and y are parameters given to initially give the starting coordinates for an AsteroidSmall object
    //Shifting AsteroidSmall object to given coordinates
    asteroidx1+=x;
    asteroidx2+=x;
    asteroidy1+=y;
    asteroidy2+=y;
  }//end of constructor
//Methods
  /*getAsteroid method
   * Postcondition: Image asteroidSmall will contain the image AsteroidSmall.png
   *Purpose: to get the image used for the AsteroidSmall object and save it*/
  public void getAsteroid(){
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("AsteroidSmall.png"));
    } catch (IOException e) {}
    asteroidSmall = img;
  }//end of getAsteroid method
  public Image getPic(){return asteroidSmall;}//getPic method that returns the asteroidSmall variable (as an Image)
  public int getX1(){return asteroidx1;}//getX1 method that returns the asteroidx1 variable (as an int)
  public int getX2(){return asteroidx2;}//getX2 method that returns the asteroidx2 variable (as an int)
  public int getY1(){return asteroidy1;}//getY1 method that returns the asteroidy1 variable (as an int)
  public int getY2(){return asteroidy2;}//getY2 method that returns the asteroidy2 variable (as an int)
  /*setX method 
   * Precondition: num >=1400 (1400 = width of the panel)
   * Postcondition: - asteroidx1 will take the value of the parameter num
   *                      - asteroidx2 will take the value of the parameter num + 40
   *Purpose: to reset the x values of AsteroidSmall objects after a wave is over to reuse them for futur waves to come*/
  public void setX(int num){
    asteroidx1=num;
    asteroidx2=40+num;
  }//end of setX method
  /*changeX method
   * Postcondition: asteroidx1 and asteroidx2 will decrease by the speed
   *Purpose: to be called over and over to see the AsteroidSmall move on the screen while a wave is running*/
  public void changeX(){
    asteroidx1-=speed;
    asteroidx2-=speed;
  }//end of changeX method
  public void addSpeed(){speed++;}//addSpeed method that adds 1 to the speed variable
  public void removeSpeed(){speed++;}//removeSpeed method that removes 1 to the speed variable
}//end of AsteroidSmall class