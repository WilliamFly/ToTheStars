/*AsteroidMed class
 * Author: William Mucha
 * Class Created: 12/30/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to make an asteroid object of size medium and be able to manipulate its coordinates and speed
 * Imports used in the AsteroidMed class*/
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//AsteroidMed class
public class AsteroidMed{
//Variables
  Image asteroidMed;//Image variable for the AsteroidMed object
  //Initial dimensions of the AsteroidMed object
  private int asteroidx1=0;//left side of the AsteroidMed object
  private int asteroidx2=159;//right side of the AsteroidMed object
  private int asteroidy1=0;//top side of the AsteroidMed object
  private int asteroidy2=150;//bottom side of the AsteroidMed object
  private int speed=2;//speed of the AsteroidMed object
//Constructor
  public AsteroidMed(int x, int y){//x and y are parameters given to initially give the starting coordinates for an AsteroidMed object
    //Shifting AsteroidMed object to given coordinates
    asteroidx1+=x;
    asteroidx2+=x;
    asteroidy1+=y;
    asteroidy2+=y;
  }//end of constructor
//Methods
  /*getAsteroid method
   * Postcondition: Image asteroidMed will contain the image AsteroidMed.png
   *Purpose: to get the image used for the AsteroidMed object and save it*/
  public void getAsteroid(){
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("AsteroidMed.png"));
    } catch (IOException e) {}
    asteroidMed = img;
  }//end of getAsteroid method
  public Image getPic(){return asteroidMed;}//getPic method that returns the asteroidMed variable (as an Image)
  public int getX1(){return asteroidx1;}//getX1 method that returns the asteroidx1 variable (as an int)
  public int getX2(){return asteroidx2;}//getX2 method that returns the asteroidx2 variable (as an int)
  public int getY1(){return asteroidy1;}//getY1 method that returns the asteroidy1 variable (as an int)
  public int getY2(){return asteroidy2;}//getY2 method that returns the asteroidy2 variable (as an int)
  /*setX method 
   * Precondition: num >=1400 (1400 = width of the panel)
   * Postcondition: - asteroidx1 will take the value of the parameter num
   *                      - asteroidx2 will take the value of the parameter num + 159
   *Purpose: to reset the x values of AsteroidMed objects after a wave is over to reuse them for futur waves to come*/
  public void setX(int num){
    asteroidx1=num;
    asteroidx2=159+num;
  }//end of setX method
  /*changeX method
   * Postcondition: asteroidx1 and asteroidx2 will decrease by the speed
   *Purpose: to be called over and over to see the AsteroidMed move on the screen while a wave is running*/
  public void changeX(){
    asteroidx1-=speed;
    asteroidx2-=speed;
  }//end of changeX method
  public void addSpeed(){speed++;}//addSpeed method that adds 1 to the speed variable
  public void removeSpeed(){speed++;}//removeSpeed method that removes 1 to the speed variable
}//end of AsteroidMed class