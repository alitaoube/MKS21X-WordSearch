import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Driver{
  // int rows, int cols, String fileName, int Randseed, boolean key
  public static void main(String[] args) {

    if (args.length < 3){
      System.out.println(
      "Please input the dimensions and the name of the text file. i.e. java WordSearch xx yy words.txt");
    }
    else if (args.length == 4){
      WordSearch test = new WordSearch(Integer.parseInt(args[0]),
      Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
      System.out.println(test.toString());
    }
    else{
      if (args.length == 5){
        try{
          if (args[4].equals("key")){
            WordSearch test = new WordSearch(Integer.parseInt(args[0]),
            Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
            System.out.println(test.toString());
          }
          else{
            WordSearch test = new WordSearch(Integer.parseInt(args[0]),
            Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
            System.out.println(test.toString());
          }
        }
        catch (NumberFormatException e){
          System.out.println("Please input integer for the row and columns (first two parameters)");
        }
      }
    }
    if (args.length == 3){
      // int rows, int cols, String fileName, int Randseed, boolean key
      Random randgen = new Random();
      int seed = randgen.nextInt();
      WordSearch test = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], seed, false);
      System.out.println(test.toString());
    }
  }
}
