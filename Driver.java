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
          WordSearch test = new WordSearch(Integer.parseInt(args[0]),
          Integer.parseInt(args[1]), args[2]);
          System.out.println(test.toString());
      }
    }
    if (args.length == 4){
      WordSearch test = new WordSearch(Integer.parseInt(args[0]),
      Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
      System.out.println(test.toString());
    }
  }
}
