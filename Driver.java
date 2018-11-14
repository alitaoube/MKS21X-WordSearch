import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Driver{
  public static void main(String[] args) {

    if (args.length < 3){
      System.out.println(
      "Please input the dimensions and the name of the text file. i.e. java WordSearch xx yy words.txt");
    }
    else{
      if (args.length == 3){
        try{
          WordSearch test = new WordSearch(Integer.parseInt(args[0]),
          Integer.parseInt(args[1]), args[2]);
          System.out.println(test.toString()); //fix this so that it returns version with letters added in"
        }
        catch (NumberFormatException e){
          System.out.println("Please input integer for the row and columns (first two parameters)");
        }

      }
    }
    if (args.length == 4){
      WordSearch test = new WordSearch(Integer.parseInt(args[0]),
      Integer.parseInt(args[1]), args[2]);
      if (args[3].equals("answer")){
        System.out.println(test.toString()); //Keep this the same so that it returns the answer key
      }
      System.out.println(test.toString()); //fix this so that it returns version with letters added in"
    }
  }
}
