package model;

import java.io.*;

/**
 * Starting point for the tic-tac-toe program
 *
 * @author Tyson Trail
 * @version 1.0
 * @since Sep 25, 2022
 */
public class Game implements Constants, Runnable {

  public Board getTheBoard() {
    return theBoard;
  }

  private Player oPlayer;
  private Player xPlayer;

  /** Game board */
  private Board theBoard;

  /** Game referee */
  private Referee theRef;

  /** Default constructor */
  public Game() {
    theBoard = new Board();
  }

  /**
   * Appoints a referee
   *
   * @param r referee object being appointed
   * @throws IOException
   */
  private void appointReferee(Referee r) {
    theRef = r;
    theRef.runTheGame();
  }

  public void setoPlayer(Player oPlayer) {
    this.oPlayer = oPlayer;
  }

  public void setxPlayer(Player xPlayer) {
    this.xPlayer = xPlayer;
  }

  /**
   * @param args array for command line arguments
   * @throws IOException
   */
  public void run() {
    Referee theRef;
    theRef = new Referee();
    theRef.setBoard(this.theBoard);
    theRef.setoPlayer(oPlayer);
    theRef.setxPlayer(xPlayer);
    this.appointReferee(theRef);
  }
}
