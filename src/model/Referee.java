package model;
/**
 * Referee object for the tic-tac-toe game.
 *
 * @author Tyson Trail
 * @version 1.0
 * @since Sep 25, 2022
 */
public class Referee {
  /** Player x */
  private Player xPlayer;
  /** Player o */
  private Player oPlayer;
  /** Game board */
  private Board board;

  /** Default constructor */
  public Referee() {}

  /**
   * Calls the setOpponent method for both x and o players, initiates the game by displaying the
   * board, and calling the play method for the X-Player who is always the first player
   */
  public void runTheGame() {
    xPlayer.setOpponent(oPlayer);
    oPlayer.setOpponent(xPlayer);
    xPlayer.play();
  }

  /**
   * xPlayer getter
   *
   * @return Player x
   */
  public Player getxPlayer() {
    return xPlayer;
  }

  /**
   * xPlayer setter
   *
   * @param xPlayer Player x
   */
  public void setxPlayer(Player xPlayer) {
    this.xPlayer = xPlayer;
  }

  /**
   * oPlayer getter
   *
   * @return Player o
   */
  public Player getoPlayer() {
    return oPlayer;
  }

  /**
   * oPlayer setter
   *
   * @param oPlayer Player o
   */
  public void setoPlayer(Player oPlayer) {
    this.oPlayer = oPlayer;
  }

  /**
   * Board getter
   *
   * @return Board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Board setter
   *
   * @param board board
   */
  public void setBoard(Board board) {
    this.board = board;
  }
}
