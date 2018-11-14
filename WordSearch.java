import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
  public static void main(String[] args) {

    if (args.length < 3){
      System.out.println(
      "Please input the dimensions and the name of the text file. i.e. java WordSearch xx yy words.txt. You may also include a seed to view a previous puzzle, and include key to see the answers");
    }
    else if (args.length == 4){
      try{
        if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0){
          System.out.println("Please input positive number for the row and column length");
        }
        else{
          WordSearch test = new WordSearch(Integer.parseInt(args[0]),
          Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
          System.out.println(test.toString());
        }
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
          System.out.println("The fourth number is the seed of a previous puzzle if you would like to see it again, so it must be an integer between -2,147,483,648 and 2,147,483,648.");
        }
      }
    }
    else{
      if (args.length == 5){
          try{
            Integer.parseInt(args[3]);
          }
          catch(NumberFormatException g){
            System.out.println("The fourth number is the seed of a previous puzzle if you would like to see it again, so it must be an integer between -2,147,483,648 and 2,147,483,648.");
          }
        try{
          if (args[4].equals("key")){
            if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0){
              System.out.println("Please input positive number for the row and column length");
            }
            else{
              WordSearch test = new WordSearch(Integer.parseInt(args[0]),
              Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), true);
              System.out.println(test.toString());
            }
          }
          else{
            if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0){
              System.out.println("Please input positive number for the row and column length");
            }
            else{
              WordSearch test = new WordSearch(Integer.parseInt(args[0]),
              Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]), false);
              System.out.println(test.toString());
            }
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
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */

    public WordSearch(int rows, int cols, String fileName, int Randseed, boolean key){
      data = new char[rows][cols];
      try{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        wordsToAdd = new ArrayList<String>();
        wordsAdded = new ArrayList<String>();
        while(in.hasNext()){
          String line = in.nextLine();
          wordsToAdd.add(line);
        }
        randgen = new Random(Randseed);
        seed = Randseed;
        clear();
        addAllWords();
        if (!key){
          fillIn();    //Make this add all the random letters
        }
        else{
          removeUnder();
        }
      }
      catch(FileNotFoundException e){
       System.out.println("File not found: " + fileName + ". Please input a file that exists in this directory to use as the words for this wordsearch.");
       System.exit(1);
     }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int x = 0; x < data.length; x++){
        for (int y = 0; y < data[x].length; y++){
          data[x][y] = '_';
        }
      }
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added in the direction rowIncrement,colIncrement
    *Words must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
    *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction
    *@return true when: the word is added successfully.
    *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
    *        OR there are overlapping letters that do not match
    */
    private boolean addWord(String word,int row, int col, int rowIncrement, int colIncrement){
        if (rowIncrement == 0 && colIncrement == 0){
          return false;
        }
        if (row + rowIncrement * word.length() > data.length ||
            col + colIncrement * word.length() > data[row].length || col + colIncrement * word.length() < 0 ||
            row + rowIncrement * word.length() < 0){
          return false;
        }
        for (int x = 0; x < word.length(); x++){
          if (data[row + rowIncrement * x][col + colIncrement * x] != '_'
          && data[row+x*rowIncrement][col + colIncrement * x] != word.charAt(x)){
            return false;
          }
        }
        for (int i = 0; i < word.length(); i++){
          data[row+i*rowIncrement][col+i*colIncrement] = word.charAt(i);
        }
        wordsAdded.add(word);
        return true;
    }

   /*[rowIncrement,colIncrement] examples:
    *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
    *[ 1,0] would add downwards because (row+1), with no col change
    *[ 0,-1] would add towards the left because (col - 1), with no row change
    */

    private void addAllWords(){
      randgen = new Random(seed);
      ArrayList<String> words = new ArrayList<String>();
      for (int x = 0; x < wordsToAdd.size(); x++){
        String word = wordsToAdd.get(x).toUpperCase();
        words.add(word);
      }
        for (int x = 0; x < words.size(); x++){
          String word = words.get(randgen.nextInt(words.size()));
          int y = 0;
          while (y < 1000){
            int randRow = randgen.nextInt(data.length);
            int randCol = randgen.nextInt(data[0].length);
            int randRowIncr = randgen.nextInt() % 2;
            int randColIncr = randgen.nextInt() % 2;

            while (data.length + word.length() * randRowIncr > data.length || data.length + word.length() * randRowIncr < 0 ||
            data[randRow].length + word.length() * randColIncr > data[randRow].length || data[randRow].length + word.length() * randColIncr < 0){
              randRow = randgen.nextInt(data.length);
              randCol = randgen.nextInt(data[0].length);
              randRowIncr = randgen.nextInt() % 2;
              randColIncr = randgen.nextInt() % 2;
            }

            int z = 0;
            while (words.contains(word) && z < 1000){
              if (addWord(word,randRow,randCol,randRowIncr, randColIncr)){
                words.remove(word);
              }
              z++;
          }
          y++;
          }
          words.remove(word);
          x--;
      }
    }

    private void fillIn(){
      char[] alphabet = new char[]{
      'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
      for (int x = 0; x < data.length; x++){
        for (int y = 0; y < data[x].length; y++){
      if (data[x][y] == '_' || data[x][y] == ' '){
        data[x][y] = alphabet[randgen.nextInt(alphabet.length)];
      }
        }
      }
    }

    private void removeUnder(){
      for (int x = 0; x < data.length; x++){
        for (int y = 0; y < data[x].length; y++){
          if (data[x][y] == '_'){
            data[x][y] = ' ';
          }
        }
      }
    }
    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String output = "";
      for (int x = 0; x < data.length; x++){
        output += "|";
        for (int y = 0; y < data[x].length; y++){
          output += data[x][y];
          output += " ";
        }
        output += "|";
        output += "\n";
      }
      output += "Words: ";
      for (int x = 0; x < wordsAdded.size(); x++){
        output += wordsAdded.get(x);
        if (x + 1 != wordsAdded.size()){
          output += ", ";
        }
      }
      output += "\n" + "Seed: " + seed;
      return output;
    }
  }
