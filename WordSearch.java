public class WordSearch{
    private char[][]data;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      clear();
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int x = 0; x < data.length; x++){
        for (int y = 0; y < data[x].length; y++){
          data[x][y] = '_';
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
        for (int y = 0; y < data[x].length; y++){
          output += data[x][y];
          output += " ";
        }
        output += "\n";
      }
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
