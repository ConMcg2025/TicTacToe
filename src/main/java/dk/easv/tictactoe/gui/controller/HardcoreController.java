package dk.easv.tictactoe.gui.controller;

import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import dk.easv.tictactoe.bll.TicTacAI;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HardcoreController implements Initializable {
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    protected GridPane gridPane;
    TicTacAI ai = new TicTacAI('O', 'X');
    private static final String TXT_PLAYER = "Player: ";
    private char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
    };
    private IGameBoard game;

    private void updateCurBoard(){
        int row = 0, col = 0;
        for(Node n : gridPane.getChildren()) {
            Button uqBtn = (Button) n;
            board[row][col] = uqBtn.getText().isEmpty() ? '-' : uqBtn.getText().charAt(0);
            col++;
            if (col == 3) { row++; col = 0; }
        }
    }


    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            if (!btn.getText().isEmpty()) return;

            btn.setText("X");
            updateCurBoard();

            int[] aiMove = ai.getBestMove(board);
            board[aiMove[0]][aiMove[1]] = 'O';
            updateButtonAt(aiMove[0], aiMove[1], "O");

            if (game.isGameOver()) {
                displayWinner(game.getWinner());
                disableButtons(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to update a button at a given row/column with a symbol
     */
    private void updateButtonAt(int row, int col, String symbol) {
        for (Node n : gridPane.getChildren()) {
            Integer r = GridPane.getRowIndex(n);
            Integer c = GridPane.getColumnIndex(n);
            int rIndex = (r == null) ? 0 : r;
            int cIndex = (c == null) ? 0 : c;
            if (rIndex == row && cIndex == col) {
                ((Button) n).setText(symbol);
                break;
            }
        }
    }


    public void disableButtons(boolean disable){
        for (Node n : gridPane.getChildren()) {
            n.setDisable(disable);
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
        disableButtons(false);
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */

    private void addHoverAnimation(Button btn) {
        btn.setOnMouseEntered(event -> { // Type of event (Checks if cursor is on the button)
            ScaleTransition scaleTr = new ScaleTransition(Duration.millis(150), btn); // What do we want to animate (Scale)
            scaleTr.setToX(1.1); // Change X to 1.1 (onHover)
            scaleTr.setToY(1.1); // Change Y to 1.1 (onHover)
            scaleTr.play(); // Play the animation
        });

        btn.setOnMouseExited(event -> { // (Cursor leaves the button)
            ScaleTransition scaleTr = new ScaleTransition(Duration.millis(150), btn); // -||-
            scaleTr.setToX(1.0);
            scaleTr.setToY(1.0);
            scaleTr.play();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        game.updateBoard(board);
        setPlayer();
        for (Node n : gridPane.getChildren()) { // Get everything from the gridPane (parent)
            if (n instanceof Button btn) { // We are looking for the buttons to add the animation so we add this if statement
                addHoverAnimation(btn); // Use our method to add the smooth animation to our buttons
            }
        }
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case -1:
                message = "It's a draw :-(";
                break;
            default:
                message = "Player " + winner + " wins!!!";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren()) // Gets every button
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}
