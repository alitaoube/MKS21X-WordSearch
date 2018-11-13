import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception

public class WordSearch{
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

    public WordSearch(int rows, int cols, String fileName){
       data = new char[rows][cols];
       try{
         File f = new File(fileName);//can combine
         Scanner in = new Scanner(f);//into one line
         randgen = new Random();
         seed = randgen.nextInt();
         wordsToAdd = new ArrayList<String>();
         while(in.hasNext()){
           String line = in.nextLine();
           wordsToAdd.add(line);
         }
         clear();
         addAllWords();
       }
       catch(FileNotFoundException e){
        System.out.println("File not found: " + fileName);
        System.exit(1);
      }
    }



    public WordSearch(int rows, int cols, String fileName, int Randseed){
      data = new char[rows][cols];
      randgen = new Random(Randseed);
      try{
        File f = new File(fileName);//can combine
        Scanner in = new Scanner(f);//into one line
        randgen = new Random(Randseed);
        seed = randgen.nextInt();
        wordsToAdd = new ArrayList<String>();
        while(in.hasNext()){
          String line = in.nextLine();
          wordsToAdd.add(line);
        }
      }
      catch(FileNotFoundException e){
       System.out.println("File not found: " + fileName);
       System.exit(1);
      }
      clear();
      addAllWords();
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
        return true;
    }

   /*[rowIncrement,colIncrement] examples:
    *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)
    *[ 1,0] would add downwards because (row+1), with no col change
    *[ 0,-1] would add towards the left because (col - 1), with no row change
    */


    private boolean addAllWords(){
      for (int x = 0; x < wordsToAdd.size(); x++){
        String word = wordsToAdd.get(randgen.nextInt(wordsToAdd.size()));
        int y = 0;
        // String word,int row, int col, int rowIncrement, int colIncrement;
        // System.out.println(randgen.nextInt(data.length));
        // System.out.println(randgen.nextInt(data[0].length));
        while(!addWord(word, randgen.nextInt(data.length),randgen.nextInt(data[0].length),
        randgen.nextInt() % 2, randgen.nextInt() % 2) && y < 10) {
          y++;
          // System.out.println("hello");
          addWord(word, randgen.nextInt(data.length),
          randgen.nextInt(data[0].length), randgen.nextInt() % 2, randgen.nextInt() % 2);
          // System.out.println("bye");

        }
      }
      return true;
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
      for (int x = 0; x < wordsToAdd.size(); x++){
        output += wordsToAdd.get(x);
      }
      output += "seed: " + seed;
      return output;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      if (col + word.length() > data[row].length){
        return false;
      }

      for (int i = 0; i < word.length(); i++){
        if (data[row][i + col] != '_' && data[row][i + col] != word.charAt(i)) {
          return false;
        }
      }
      for (int x = 0; x < word.length(); x++){
          data[row][col + x] = word.charAt(x);

      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      if (row + word.length() > data.length){
        return false;
      }
      for (int i = 0; i < word.length(); i++){
        if (data[row + i][col] != '_' && data[row + i][col] != word.charAt(i)) {
          return false;
        }
      }
      for (int x = 0; x < word.length(); x++){
        data[row + x][col] = word.charAt(x);
      }
      return true;
    }

    /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from top left to bottom right, must fit on the WordGrid,
  *and must have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  *or there are overlapping letters that do not match, then false is returned.
  */
 public boolean addWordDiagonal(String word,int row, int col){
   if (col + word.length() > data[row].length || row + word.length() > data.length){
     return false;
   }
   for (int x = 0; x < word.length(); x++){
     if (data[row+x][col+x] != '_' && data[row + x][col+x] != word.charAt(x)){
       return false;
     }
   }
   for (int i = 0; i < word.length(); i++){
     data[row+i][col+i] = word.charAt(i);
 }
  return true;
  }
}
