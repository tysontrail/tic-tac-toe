package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.Constants;
import model.Game;
import model.Player;

public class TicServer implements Constants {
  private Socket xSocket;
  private Socket oSocket;
  private ServerSocket serverSocket;
  private PrintWriter xSocketOut;
  private PrintWriter oSocketOut;
  private ExecutorService pool;
  private BufferedReader xSocketIn;
  private BufferedReader oSocketIn;

  public TicServer(int port) {
    try {
      serverSocket = new ServerSocket(port);
      pool = Executors.newFixedThreadPool(5);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void runServer() {
    try {
      while (true) {

        Game game = new Game();

        // X Player connects to server
        xSocket = serverSocket.accept();
        System.out.println("Connection accepted by server");
        xSocketIn = new BufferedReader(new InputStreamReader(xSocket.getInputStream()));
        xSocketOut = new PrintWriter((xSocket.getOutputStream()), true);

        // Set up X Player
        // System.out.println(game.getTheBoard().display());
        xSocketOut.println(game.getTheBoard().display());
        xSocketOut.println("Message: WELCOME To THE GAME");
        xSocketOut.println("Get the name of the X player: ");
        String name = xSocketIn.readLine();

        // Assign X Player
        Player xPlayer = new Player(name, LETTER_X, xSocketOut, xSocketIn);
        game.setxPlayer(xPlayer);
        xPlayer.setBoard(game.getTheBoard());
        xSocketOut.println("Message: Waiting for opponent to connect");

        // O Player connects to server
        oSocket = serverSocket.accept();
        System.out.println("Connection accepted by server");
        oSocketIn = new BufferedReader(new InputStreamReader(oSocket.getInputStream()));
        oSocketOut = new PrintWriter((oSocket.getOutputStream()), true);

        // Set up O Player
        oSocketOut.println(game.getTheBoard().display());
        oSocketOut.println("Message: WELCOME To THE GAME");
        oSocketOut.println("Get the name of the O player: ");
        name = oSocketIn.readLine();
        oSocketOut.println("Message: Waiting for " + xPlayer.getName() + "\'s move...");

        Player oPlayer = new Player(name, LETTER_O, oSocketOut, oSocketIn);
        game.setoPlayer(oPlayer);
        oPlayer.setBoard(game.getTheBoard());

        pool.execute(game);
      }

    } catch (IOException e) {
    }
    pool.shutdown();

    try {
      xSocket.close();
      oSocket.close();
      xSocketIn.close();
      xSocketOut.close();
      oSocketIn.close();
      oSocketOut.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    TicServer myServer = new TicServer(9898);
    myServer.runServer();
  }
}
