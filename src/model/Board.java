package model;
/**
 * Class Board includes most of the tic-tac-toe game's logic. It implements the constants interface.
 *
 * @author Tyson Trail
 * @version 1.0
 * @since Sep 25, 2022
 */
public class Board implements Constants {

  /** 2-D Array of the game board */
  private char theBoard[][];

  /** Counter for marks on the board */
  private int markCount;

  /**
   * Creates a new board object with a virtual 3 x 3 char array and populates it with empty chars
   * (spaces)
   */
  public Board() {
    markCount = 0;
    theBoard = new char[3][];
    for (int i = 0; i < 3; i++) {
      theBoard[i] = new char[3];
      for (int j = 0; j < 3; j++) theBoard[i][j] = SPACE_CHAR;
    }
  }

  /**
   * Returns the Mark (char) populating a specific row and column of the board object array
   *
   * @param row row on the board
   * @param col column on the board
   * @return char Mark that is occupying the specified row and column
   */
  public char getMark(int row, int col) {
    return theBoard[row][col];
  }

  /**
   * Compares the current Mark count to a full board (9 Marks) and returns true or false
   *
   * @return boolean true if Mark count is 9, false otherwise
   */
  public boolean isFull() {
    return markCount == 9;
  }

  /**
   * Checks if char X wins using the checkWinner function
   *
   * @return boolean true if X wins
   */
  public boolean xWins() {
    if (checkWinner(LETTER_X) == 1) return true;
    else return false;
  }

  /**
   * Checks if char o Wins using the checkWinner function
   *
   * @return boolean true if O wins
   */
  public boolean oWins() {
    if (checkWinner(LETTER_O) == 1) return true;
    else return false;
  }

  /** Displays the current game board */
  public String display() {
    String str = "Board:\n";
    str += displayColumnHeaders();
    str += addHyphens();
    for (int row = 0; row < 3; row++) {
      str += addSpaces();
      str += "    row " + row + ' ';
      for (int col = 0; col < 3; col++) str += "|  " + getMark(row, col) + "  ";
      str += "|\n";
      str += addSpaces();
      str += addHyphens();
    }
    str += "\n \0";
    return str;
  }

  /**
   * Adds a mark to the game board
   *
   * @param row row to add the mark to
   * @param col column to add the mark to
   * @param mark mark to add
   */
  public void addMark(int row, int col, char mark) {

    theBoard[row][col] = mark;
    markCount++;
  }

  /** Clears the current board and replaces marks with space char */
  public void clear() {
    for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) theBoard[i][j] = SPACE_CHAR;
    markCount = 0;
  }

  /**
   * Checks if any rows, columns, or diagonals have 3 of the parameter mark
   *
   * @param mark mark to check
   * @return int result returns 1 indicating the parameter mark has 3 in a row, column, or diagonal
   *     making it a winner. Returns 0 otherwise.
   */
  int checkWinner(char mark) {
    int row, col;
    int result = 0;

    // Checks if any of the rows have 3 of the mark
    for (row = 0; result == 0 && row < 3; row++) {
      int row_result = 1;
      for (col = 0; row_result == 1 && col < 3; col++)
        if (theBoard[row][col] != mark) row_result = 0;
      if (row_result != 0) result = 1;
    }

    // Checks if any of the columns have 3 of the mark
    for (col = 0; result == 0 && col < 3; col++) {
      int col_result = 1;
      for (row = 0; col_result != 0 && row < 3; row++)
        if (theBoard[row][col] != mark) col_result = 0;
      if (col_result != 0) result = 1;
    }

    // Checks the diagonal result 1 if no rows or columns have 3 of the mark
    if (result == 0) {
      int diag1Result = 1;
      for (row = 0; diag1Result != 0 && row < 3; row++)
        if (theBoard[row][row] != mark) diag1Result = 0;
      if (diag1Result != 0) result = 1;
    }

    // Checks the diagonal result 2 if no rows, columns, or diagonal 1 have 3 of the mark
    if (result == 0) {
      int diag2Result = 1;
      for (row = 0; diag2Result != 0 && row < 3; row++)
        if (theBoard[row][3 - 1 - row] != mark) diag2Result = 0;
      if (diag2Result != 0) result = 1;
    }
    return result;
  }

  /** Displays the column headers */
  String displayColumnHeaders() {
    String str = "          ";
    for (int j = 0; j < 3; j++) str += "|col " + j;
    return str += "\n";
  }

  /** Adds hyphens */
  String addHyphens() {
    String str = "          ";
    for (int j = 0; j < 3; j++) str += "+-----";
    return str += "+\n";
  }

  /** Add's spaces */
  String addSpaces() {
    String str = "          ";
    for (int j = 0; j < 3; j++) str += "|     ";
    return str += "|\n";
  }

  @Override
  public String toString() {
    return this.display();
  }
}
