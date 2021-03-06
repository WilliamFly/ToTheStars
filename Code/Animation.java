/*Animation class
 * Author: Savlon
 * Class Created: Apr 12 '13 was posted on http://gamedev.stackexchange.com/questions/53705/how-can-i-make-a-sprite-sheet-based-animation-system
 * Class Last Updated: 1/12/2017 by myslef
 * Purpose of Class: to be able to store and manipulate many different sprites animations
 * I used someones elses class due to my lack in knowledge in how to make sprite animations in java, though it was fully commented by William Mucha
 * Import used in the Animation class*/
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
//Animaitons class
public class Animation {
//Variables
  private int frameCount;//counts ticks for change
  private int frameDelay;//frame delay
  private int currentFrame;//animations current frame
  private int animationDirection;//animation direction (i.e counting forward or backward)
  private int totalFrames;//total amount of frames in animation
  private boolean stopped;//has animations stopped
  private List<Frame> frames = new ArrayList<Frame>();// Arraylist of frames 
//Constructor
  public Animation(BufferedImage[] frames, int frameDelay) {//parameters take in an array of frames and the delay between frames
    this.frameDelay = frameDelay;//making parameter equal variable created above
    this.stopped = true;//starting the animation of stopped
    for (int i = 0; i < frames.length; i++) {
      addFrame(frames[i], frameDelay);
    }//for all frames given in parameter array frames, adds all frames in parameter to ArrayList frames
    this.frameCount = 0;//standardize ticks count to 0
    this.currentFrame = 0;//starts animation with first frame
    this.animationDirection = 1;//makes animation go in forward direction
    this.totalFrames = this.frames.size();//makes the total frames the size of frames in ArrayList
  }//end of constructor
//Methods
  /*start method
   * Postcondition: if conditions meet, boolean stopped is false 
   *Purpose: to start the animation of a sprite*/
  public void start() {
    if (!stopped) {return;}//if stop is false, exit method
    if (frames.size() == 0) {return;}//if there are no sprite images in frame arraylist, exit array
    stopped = false;//start animation
  }//end of start method
  /*stop method
   * Postcondition: if conditions meet, boolean stopped is true 
   *Purpose: to stop the animation of a sprite*/
  public void stop() {
    if (frames.size() == 0) { return;}//if there are no sprite images in frame arraylist, exit array
    stopped = true;//stop animation
  }//end of stop method
  public BufferedImage getSprite(){return frames.get(currentFrame).getFrame();}//getSprite method that returns current frame from the ArrayList (as a BufferedImage)
  /*addFrame method
   * Precondition: buffered image (sprite) and an int (whole number positive)
   * Postcondition: saves it to frames ArrayList
   *Purpose: to add a sprite frame into the fframes ArrayList*/
  private void addFrame(BufferedImage frame, int duration) {
    if (duration <= 0) {//error checking
      System.err.println("Invalid duration: " + duration);
      throw new RuntimeException("Invalid duration: " + duration);
    }
    frames.add(new Frame(frame, duration));//adding frame to ArrayList
    currentFrame = 0;//setting animation to first frame
  }//end of addFrame method
  /*update method
   * Postcondition: changes the frameCount depending on situation
   *Purpose: to run through all the animations in frames ArrayList*/
  public void update() {
    if (!stopped){//while stopped boolean is false 
      frameCount++;//increase the frame count by 1
      if (frameCount > frameDelay) {//if frame count is greater than the frame delay
        frameCount = 0;//reset back to first frame
        currentFrame += animationDirection;//add the value of animationDirection to currentFrame
        if (currentFrame > totalFrames - 1) {//if currentFrame is less than the totalFrames subtract 1
          currentFrame = 0;//reset back to first frame
        }
        else if (currentFrame < 0) {//if currentFrame is less than 0
          currentFrame = totalFrames - 1;//currentFrame equals the totalFrames subtract 1
        }
      }
    }  
  }//end of update method  
}//end of Animation class