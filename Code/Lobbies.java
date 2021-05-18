/*Lobbies class
 * Author: William Mucha
 * Class Created: 12/20/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: Parent class of the MainMenu class, the StoryMode class and the SurvivalMode class.
 *    Used to make a standard JFrame/JPanel that give the same characteristics to all panels
 * Imports used in the Lobbies class*/
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
//public abstract class Lobbies that is a child class to JPanel
public abstract class Lobbies extends JPanel{
  //Variables
  int WIDTH = 1400;//standard width of each panel
  int HEIGHT = 800;//standard height of each panel
  //Constructor
  public Lobbies(){
    super();//calling to receive from parent class JPanel
    setBackground(Color.BLACK);//setting the background to black
  }
  //Methods
  public abstract void getImages();//abstract method used to get all the Images used in that panel
  /*lobby method
   * Precondition: - menu = a JFrame given, created in the main method of program
   * Postcondition: - panel = one of the child classes of this class
   *Purpose: To create all the lobbies used in the game*/
  public void lobby(JFrame menu,Lobbies panel){
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Closes program if one of the fame is closed by clicking the X button
    menu.add(panel);//creates a panel
    menu.pack();//sizes the frame so that all its contents are at or above their preferred sizes
    menu.setSize(WIDTH, HEIGHT);//creates size of the frame
    menu.setLocationRelativeTo(null);//places the frame at the center of the screen
    menu.setVisible(true);//makes the frame visible
    menu.setResizable(false);//make it so the user cannot change the size of the frame
  }//end of lobby method
}//end of Lobbies class