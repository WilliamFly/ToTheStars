/*KeyPressing class
 * Author: William Mucha
 * Class Created: 12/29/2016
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to contain all the key pressed commands for the program
 * Import used in the KeyPressing class*/
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
//KeyPressing class which is a child class to JFrame
public class KeyPressing extends JFrame {
//Variables
  static boolean goingUp = false;//static boolean that changes whether the up key is pressed or not
  static boolean goingDown = false;//static boolean that changes whether the down key is pressed or not
  static String name="";//static String that changes depending what the user types in
  static boolean caps = false;//static boolean that changes if the shift key is pressed
//Constructor with Methods
  public KeyPressing() {
    addKeyListener(new KeyListener() {//creating a new KeyListener
      /*keyPressed method
       * Postcondition: returns values depending on what key is pressed
       *Purpose: checks to see if certain keys were pressed*/
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){//if the up arrow key was pressed
          goingUp = true;//change goingUp to true
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){//if the down arrow key was pressed
          goingDown = true;//change goingDown to true
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){//if the shift key was pressed
          caps = true;//return all values in capital form
        }
        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE ){//if someone is backspacing
          if (name != null && name.length() > 0) {//check to see there are letters to delete
            name = name.substring(0, name.length()-1);//delete last letter
          }
        }
        if(e.getKeyCode()==KeyEvent.VK_A && name.length()<16){//if letter a has been pressed and the name length is less than 16
          if(caps) name+="A";//print letter in capital form if caps boolean is true 
          else name+="a";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_B && name.length()<16){//if letter b has been pressed and the name length is less than 16
          if(caps) name+="B";//print letter in capital form if caps boolean is true 
          else name+="b";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_C && name.length()<16){//if letter c has been pressed and the name length is less than 16
          if(caps) name+="C";//print letter in capital form if caps boolean is true 
          else name+="c";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_D && name.length()<16){//if letter d has been pressed and the name length is less than 16
          if(caps) name+="D";//print letter in capital form if caps boolean is true 
          else name+="d";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_E && name.length()<16){//if letter e has been pressed and the name length is less than 16
          if(caps) name+="E";//print letter in capital form if caps boolean is true 
          else name+="e";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_F && name.length()<16){//if letter f has been pressed and the name length is less than 16
          if(caps) name+="F";//print letter in capital form if caps boolean is true 
          else name+="f";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_G && name.length()<16){//if letter g has been pressed and the name length is less than 16
          if(caps) name+="G";//print letter in capital form if caps boolean is true 
          else name+="g";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_H && name.length()<16){//if letter h has been pressed and the name length is less than 16
          if(caps) name+="H";//print letter in capital form if caps boolean is true 
          else name+="h";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_I && name.length()<16){//if letter i has been pressed and the name length is less than 16
          if(caps) name+="I";//print letter in capital form if caps boolean is true 
          else name+="i";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_J && name.length()<16){//if letter j has been pressed and the name length is less than 16
          if(caps) name+="J";//print letter in capital form if caps boolean is true 
          else name+="j";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_K && name.length()<16){//if letter k has been pressed and the name length is less than 16
          if(caps) name+="K";//print letter in capital form if caps boolean is true 
          else name+="k";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_L && name.length()<16){//if letter l has been pressed and the name length is less than 16
          if(caps) name+="L";//print letter in capital form if caps boolean is true
          else name+="l";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_M && name.length()<16){//if letter m has been pressed and the name length is less than 16
          if(caps) name+="M";//print letter in capital form if caps boolean is true 
          else name+="m";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_N && name.length()<16){//if letter n has been pressed and the name length is less than 16
          if(caps) name+="N";//print letter in capital form if caps boolean is true 
          else name+="n";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_O && name.length()<16){//if letter o has been pressed and the name length is less than 16
          if(caps) name+="O";//print letter in capital form if caps boolean is true 
          else name+="o";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_P && name.length()<16){//if letter p has been pressed and the name length is less than 16
          if(caps) name+="P";//print letter in capital form if caps boolean is true 
          else name+="p";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_Q && name.length()<16){//if letter q has been pressed and the name length is less than 16
          if(caps) name+="Q";//print letter in capital form if caps boolean is true 
          else name+="q";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_R && name.length()<16){//if letter r has been pressed and the name length is less than 16
          if(caps) name+="R";//print letter in capital form if caps boolean is true 
          else name+="r";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_S && name.length()<16){//if letter s has been pressed and the name length is less than 16
          if(caps) name+="S";//print letter in capital form if caps boolean is true 
          else name+="s";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_T && name.length()<16){//if letter t has been pressed and the name length is less than 16
          if(caps) name+="T";//print letter in capital form if caps boolean is true 
          else name+="t";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_U && name.length()<16){//if letter u has been pressed and the name length is less than 16
          if(caps) name+="U";//print letter in capital form if caps boolean is true 
          else name+="u";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_V && name.length()<16){//if letter v has been pressed and the name length is less than 16
          if(caps) name+="V";//print letter in capital form if caps boolean is true 
          else name+="v";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_W && name.length()<16){//if letter w has been pressed and the name length is less than 16
          if(caps) name+="W";//print letter in capital form if caps boolean is true 
          else name+="w";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_X && name.length()<16){//if letter x has been pressed and the name length is less than 16
          if(caps) name+="X";//print letter in capital form if caps boolean is true 
          else name+="x";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_Y && name.length()<16){//if letter y has been pressed and the name length is less than 16
          if(caps) name+="Y";//print letter in capital form if caps boolean is true 
          else name+="y";//otherwise print letter in lowercase form
        }
        if(e.getKeyCode()==KeyEvent.VK_Z && name.length()<16){//if letter z has been pressed and the name length is less than 16
          if(caps) name+="Z";//print letter in capital form if caps boolean is true 
          else name+="z";//otherwise print letter in lowercase form
        }
      }//end of keyPressed
      /*keyReleased method
       * Postcondition: returns values depending if a key was released
       *Purpose: checks to see if certain keys were released*/
      public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP){//if the up arrow key was released
          goingUp = false;//change goingUp to false
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){//if the down arrow key was released
          goingDown = false;//change goingDown to false
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT){//if the shift key was released
          caps = false;//return all values in lowercase form
        }
      }//end of keyReleased method
      public void keyTyped(KeyEvent e) {}//Method not needed, only needed to be overrided since it was an abstract method in the KeyListener import
    });//end of adding a KeyListener
  }//end of constructor
}//end of KeyPressing class