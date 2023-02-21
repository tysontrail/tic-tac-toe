# tic-tac-toe ❌ 🆚 ⭕️
This is a terminal tic-tac-toe game using a Java thread pool. After the server is started a thread pool starts a new game for every two clients that connect.

## Usage 💻
To play this game, execute TicServer.java. Once the server is started in the terminal, execute TicClient.java in a new terminal window. This will prompt Player X to enter a name, and wait for a second Player to join.

<img width="242" alt="image" src="https://user-images.githubusercontent.com/62910152/220479600-97086952-0f39-489f-bb75-64efb46ab095.png">

Execute TicClient.java again in a new terminal window and this will prompt Player O to enter a name. Once Player O has entered their name, Player X will have the first turn and the play will go between the terminal windows until one of the Players wins, or the game ends in a tie.
