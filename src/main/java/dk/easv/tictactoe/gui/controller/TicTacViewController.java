package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;
import javafx.util.Duration;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    protected GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";
    private char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
    };
    private int a = 0;
    private int b = 0;
    private IGameBoard game;

    public void updateCurBoard(){
        for(Node n : gridPane.getChildren()) {
            Button uqBtn = (Button) n;
            if (!uqBtn.getText().isEmpty()) {
                board[a][b] = uqBtn.getText().charAt(0);
            }
            b++;
            if (b == 3) {
                a++;
                b = 0;
            }
            if (a == 3) {
                a = 0;
            }
        }
    }

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;
            int player = game.getNextPlayer();
            if (game.play(c, r))
            {
                Button btn = (Button) event.getSource();
                if(btn.getText().isEmpty()) {
                    String xOrO = player == 0 ? "X" : "O";
                    btn.setText(xOrO);
                    setPlayer();
                    updateCurBoard();
                }
                if (game.isGameOver())
                {
                    displayWinner(game.getWinner());
                    for (Node n : gridPane.getChildren()) {
                        ((Button) n).setDisable(true);
                    }
                }
                game.updateBoard(board);
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
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
        btn.setOnMouseEntered(event -> { //Type of event (Checks if cursor is on the button)
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
