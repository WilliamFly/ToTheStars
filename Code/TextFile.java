/*TextFile class
 * Author: William Mucha
 * Class Created: 1/2/2017
 * Class Last Updated: 1/12/2017
 * Purpose of Class: to be able to read and change the file "scores.txt"
 * Imports used in the TextFile class*/
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
//TextFile class
public class TextFile{
//Variables
  static String[] scores = new String[7];//static String array containing all 4 highscores and 3 names
  //Strings used in the getHighscore method to save scores in a temp variable while sorting
  String tempname1;
  String tempscore1;
  String tempname2;
  String tempscore2;
//Methods
  /*getTextFile method
   * Postcondition: fills the scores array with the files content
   *Purpose: to retrive the contents of the "scores.txt" file and save it*/
  public void getTextFile(){
    try{
      String score = new Scanner(new File("scores.txt")).useDelimiter("\\Z").next();//puts the file into a string
      int tracker = 0;//tracker to keep track of position in scores array
      StringTokenizer textFile = new StringTokenizer(score);//initializing a StringTokenizer
      while(textFile.hasMoreTokens()) {//if there are more tokens
        scores[tracker] = textFile.nextToken();//fills array
        tracker++;//adds one to move to next spot in array
      }
    } catch(IOException ex){}
  }//end of getTextFile method
  public static String getStoryCompletions(){return scores[0];}//getStoryCompletions method that returns scores[0] (as an String)
  public static String getSurvivalName1(){return scores[1];}//getSurvivalName1 method that returns scores[1] (as an String)
  public static String getSurvivalScore1(){return scores[2];}//getSurvivalScore1 method that returns scores[2] (as an String)
  public static String getSurvivalName2(){return scores[3];}//getSurvivalName2 method that returns scores[3] (as an String)
  public static String getSurvivalScore2(){return scores[4];}//getSurvivalScore2 method that returns scores[4] (as an String)
  public static String getSurvivalName3(){return scores[5];}//getSurvivalName3 method that returns scores[5] (as an String)
  public static String getSurvivalScore3(){return scores[6];}//getSurvivalScore3 method that returns scores[6] (as an String)
  /*addOneCompletions method
   * Postcondition: scores[0]++;
   *Purpose: to add 1 to the number of story completions completed by the user*/
  public void addOneCompletions(){
    try{
      Path path = Paths.get("scores.txt");//creates a path to the "scores.txt" file
      Charset charset = StandardCharsets.UTF_8;//initializes an Eight-bit UCS Transformation Format
      String readBytes = new String(Files.readAllBytes(path), charset);//reads all the bytes in the file
      int tracker = 0;//tracker to keep track of position in scores array
      StringTokenizer textFile = new StringTokenizer(readBytes);//initializing a StringTokenizer
      while(textFile.hasMoreTokens()) {//if there are more tokens
        scores[tracker] = textFile.nextToken();//fills array
        tracker++;//adds one to move to next spot in array
      }
      int newScore = Integer.valueOf(scores[0]);//variable newScores is made (an int) to add 1 to the String scores[0];
      newScore++;//add 1
      scores[0] = String.valueOf(newScore);//change newScore back into a String and save it to scores[0]
      String newTextFile = scores[0]+" "+scores[1]+" "+scores[2]+" "+scores[3]+" "+scores[4]+" "+scores[5]+" "+scores[6];//String with all the scores
      Files.write(path, newTextFile.getBytes(charset));//save new String with changes back into the file
    } catch(IOException ex){}
  }//end of addOneCompletions method
  /*getHighscore method
   * Precondition: name is a String of length 1 to 16 and givenScore = whole number greater than 0
   * Postcondition: saves all the highscores into "scores.txt" file
   *Purpose: to sort all the scores to determine the highscore*/
  public void getHighscore(String name,int givenScore){
    try{
      Path path = Paths.get("scores.txt");//creates a path to the "scores.txt" file
      Charset charset = StandardCharsets.UTF_8;//initializes an Eight-bit UCS Transformation Format
      String readBytes = new String(Files.readAllBytes(path), charset);//reads all the bytes in the file
      int tracker = 0;//tracker to keep track of position in scores array
      StringTokenizer textFile = new StringTokenizer(readBytes);//initializing a StringTokenizer
      while(textFile.hasMoreTokens()) {//if there are more tokens
        scores[tracker] = textFile.nextToken();//fills array
        tracker++;//adds one to move to next spot in array
      }
      //Manual Sorting
      if(givenScore>Integer.valueOf(scores[2])){//If score given in parameter is highest score
        //set the highest scores to temp variables
        tempname1 = scores[1];
        tempscore1 = scores[2];
        //set the given parameters as the highest score
        scores[1] = name;
        scores[2] = String.valueOf(givenScore);
        //set the second highest scores to a temp variables
        tempname2 = scores[3];
        tempscore2 = scores[4];
        //set the old highest scores as the second highes
        scores[3] = tempname1;
        scores[4] = tempscore1;
        //set the old second highest scores as the third highest
        scores[5] = tempname2;
        scores[6] = tempscore2;
      } else if(givenScore>Integer.valueOf(scores[4])){//If score given in parameter is second highest score
        //set the second highest scores to a temp variables
        tempname2 = scores[3];
        tempscore2 = scores[4];
        //set score given in parameter as the second highest score
        scores[3] = name;
        scores[4] = String.valueOf(givenScore);
        //set the old second highest scores as the third highest
        scores[5] = tempname2;
        scores[6] = tempscore2;
      } else if(givenScore>Integer.valueOf(scores[6])){//If score given in parameter is third highest score
        //set score given in parameter as third highest score
        scores[5] = name;
        scores[6] = String.valueOf(givenScore);
      }
      String newTextFile = scores[0]+" "+scores[1]+" "+scores[2]+" "+scores[3]+" "+scores[4]+" "+scores[5]+" "+scores[6];//String with all the scores
      Files.write(path, newTextFile.getBytes(charset));//save new String with changes back into the file
    } catch(IOException ex){}
  }//end of getHighscore method
}//end of TextFile class