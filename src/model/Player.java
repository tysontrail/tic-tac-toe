package model;

import java.io.*;

/**
 * Player object for the tic-tac-toe game.
 *
 * @author Tyson Trail
 * @version 1.0
 * @since Sep 25, 2022
 */
public class Player {

  private PrintWriter socketOut;
  private BufferedReader socketIn;

  public PrintWriter getSocketOut() {
    return socketOut;
  }

  /** Player name */
  private String name;
  /** Game board */
  private Board board;
  /** Opponent of player */
  private Player opponent;
  /** Mark of player */
  private char mark;

  /**
   * Default constructor
   *
   * @param name Player name
   * @param mark Player mark
   */
  public Player(String name, char mark, PrintWriter socketOut, BufferedReader socketIn) {
    this.socketIn = socketIn;
    this.socketOut = socketOut;
    this.name = name;
    this.mark = mark;
  }

  /**
   * Class play is called initially by the referee object, then is iteratively called until the x or
   * o player wins the game
   */
  public void play() {
    socketOut.println(board.display());
    if (this.board.xWins() || this.board.oWins()) {
      socketOut.println("Message: THE GAME IS OVER: " + opponent.getName() + " is the winner!");
      socketOut.println("QUIT");
      opponent.getSocketOut().println(board.display());
      opponent
          .getSocketOut()
          .println("Message: THE GAME IS OVER: " + opponent.getName() + " is the winner!");
      opponent.getSocketOut().println("QUIT");
    } else if (this.board.isFull()) {
      socketOut.println("Message: THE GAME IS OVER: It's a tie!");
      socketOut.println("QUIT");
      opponent.getSocketOut().println(board.display());
      opponent.getSocketOut().println("Message: THE GAME IS OVER: It's a tie!");
      opponent.getSocketOut().println("QUIT");
    } else {
      socketOut.println("Message: " + getName() + " it is your turn to make a move.");
      makeMove();
      socketOut.println("Message: Waiting for " + opponent.getName() + "\'s move...");
      this.opponent.play();
    }
  }

  /** Called at the start of the play function to prompt a player to add a mark to the game */
  public void makeMove() {
    board.addMark(enterRow(), enterCol(), this.mark);
  }

  /**
   * Prompts player to enter row of the mark to be added
   *
   * @return int row of mark to be added
   */
  public int enterRow() {
    int row = 0;
    socketOut.println(this.name + ", what row should your next " + this.mark + " be placed in? ");
    while (true) {
      try {
        row = Integer.parseInt(socketIn.readLine());
        if (row < 0 || row > 3) {
          socketOut.println("Please enter 0, 1 or 2: ");
        } else {
          break;
        }
      } catch (Exception e) {
        socketOut.println("Please enter valid integer.");
      }
    }

    return row;
  }

  /**
   * Prompts player to enter column of the mark to be added
   *
   * @return int col of mark to be added
   */
  public int enterCol() {
    int col = 0;
    socketOut.println(
        this.name + ", what column should your next " + this.mark + " be placed in? ");
    while (true) {
      try {
        col = Integer.parseInt(socketIn.readLine());
        if (col < 0 || col > 3) {
          socketOut.println("\nPlease enter 0, 1 or 2: ");
        } else {
          break;
        }
      } catch (Exception e) {
        socketOut.println("\nPlease enter valid integer.");
      }
    }

    return col;
  }

  /**
   * name getter
   *
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * name setter
   *
   * @param name name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * board getter
   *
   * @return Board board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * board setter
   *
   * @param board board
   */
  public void setBoard(Board board) {
    this.board = board;
  }

  /**
   * opponent getter
   *
   * @return Player opponent
   */
  public Player getOpponent() {
    return opponent;
  }

  /**
   * opponent setter
   *
   * @param opponent opponent
   */
  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }

  /**
   * mark getter
   *
   * @return char mark
   */
  public char getMark() {
    return mark;
  }

  /**
   * mark setter
   *
   * @param mark mark
   */
  public void setMark(char mark) {
    this.mark = mark;
  }
}
