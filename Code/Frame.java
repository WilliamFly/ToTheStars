/*Frame class
 * Author: Savlon
 * Class Created: Apr 12 '13 was posted on http://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
 * Class Last Updated: 1/12/2017 by myslef
 * Purpose of Class: to be able to make many specific sprites
 * I used someones elses class due to my lack in knowledge in how to make sprites in java
 * Import used in the Frame class*/
import java.awt.image.BufferedImage;
//Frame class
public class Frame {
//Variables
  private BufferedImage frame;//variable used to save sprite image
  private int duration;//variable to set the duration between different animations
//Constructor
  public Frame(BufferedImage frame, int duration){//sets the parameters given to the variables created above
    this.frame = frame;
    this.duration = duration;
  }//end of constructor
//Methods
  public BufferedImage getFrame() {return frame;}//getFrame method that returns the frame variable (as a BufferedImage)
  public void setFrame(BufferedImage frame) {this.frame = frame;}//setFrame method that manually changes frame variable to given parameter
  public int getDuration() {return duration;}//getDuration method that returns the duration variable (as an int)
  public void setDuration(int duration) {this.duration = duration;}//setDuration method that manually changes duration variable to given parameter
}//end of Frame class