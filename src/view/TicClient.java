package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicClient {
  private PrintWriter socketOut;
  private Socket ticSocket;
  private BufferedReader stdIn;
  private BufferedReader socketIn;

  public TicClient(String serverName, int portNumber) {
    try {
      ticSocket = new Socket(serverName, portNumber);
      stdIn = new BufferedReader(new InputStreamReader(System.in));
      socketIn = new BufferedReader(new InputStreamReader(ticSocket.getInputStream()));
      socketOut = new PrintWriter((ticSocket.getOutputStream()), true);
    } catch (IOException e) {
      System.err.println(e.getStackTrace());
    }
  }

  public void communicate() {
    String line = "";
    String response = "";
    while (true) {
      try {
        // System.out.println("Reading line from server");
        line = socketIn.readLine();

        // Print message to clients terminal
        if (line.contains("Message:")) {
          // System.out.println("Message Condition");
          System.out.println(line);

          // Print board to clients terminal
        } else if (line.contains("Board:")) {

          // System.out.println("Board Condition");
          while (!line.contains("\0")) {
            line = socketIn.readLine();
            System.out.println(line);
          }

          // Display prompt to client, read input, and send back to server
        } else if (line.contains("QUIT")) {
          break;
        } else {
          // System.out.println("Prompt Condition");
          System.out.println(line);
          response = stdIn.readLine();
          socketOut.println(response);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      stdIn.close();
      socketIn.close();
      socketOut.close();
    } catch (IOException e) {
      System.out.println("Closing error: " + e.getMessage());
    }
  }

  public static void main(String[] args) throws IOException {
    TicClient aClient = new TicClient("localhost", 9898);
    aClient.communicate();
  }
}
