import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class Driver{
  public static void main(String[] args) {

    if (args.length < 3){
      System.out.println(
      "Please input the dimensions and the name of the text file. i.e. java WordSearch xx yy words.txt. You may also include a seed to view a previous puzzle, and include key to see the answers");
    }
    else if (args.length == 4){
      try{
        WordSearch test = new WordSearch(Integer.parseInt(args[0]),
        Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
        System.out.println(test.toString());
      }
      catch(NumberFormatException e){
        try{
          Integer.parseInt(args[0]);
          Integer.parseInt(args[1]);
        }
        catch(NumberFormatException f){
          System.out.println("The first two numbers are the rows and columns, so they must be integers");
        }
        try{
          Integer.parseInt(args[3]);
        }
        catch(NumberFormatException g){
          System.out.println("The fourth number is the seed of a previous puzzle if you would like to see it again, so it must be an integer");
        }
      }
    }
    else{
      if (args.length == 5){
          try{
            Integer.parseInt(args[3]);
          }
          catch(NumberFormatException g){
            System.out.println("The fourth number is the seed of a previous puzzle if you would like to see it again, so it must be an integer");
          }
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
          try{
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
          }
          catch(NumberFormatException f){
            System.out.println("Please input integer for the row and columns (first two parameters)");
          }
        }
      }
    }
    if (args.length == 3){
      Random randgen = new Random();
      int seed = randgen.nextInt();
      try{
        WordSearch test = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], seed, false);
        System.out.println(test.toString());
      }
      catch(NumberFormatException e){
        try{
          Integer.parseInt(args[0]);
          Integer.parseInt(args[1]);
        }
        catch(NumberFormatException f){
          System.out.println("The first two numbers are the rows and columns, so they must be integers");
        }
    }
  }
  if (args.length > 5){
    System.out.println("Too many parameters: the maximum number of useful parameters is 5.");
  }
}
}
