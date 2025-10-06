
package dk.easv.tictactoe.bll;

import dk.easv.tictactoe.gui.TicTacToe;
import dk.easv.tictactoe.gui.controller.TicTacViewController;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 *
 * @author EASV
 */
public class GameBoard extends TicTacViewController implements IGameBoard
{

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    private char[][] board = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
    };
    private int player = 0;

    public int getNextPlayer()
    {
        //TODO Implement this method
        if (player == 0)
        {
            player = 1;
        }
        else
        {
            player = 0;
        }
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        //TODO Implement this method
        getNextPlayer();
        return true;
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        int a = 0;
        int b = 0;
        for(Node n : gridPane.getChildren()){
            Button btn =  (Button) n;
            if(!btn.getText().isEmpty()) {
                board[a][b] = btn.getText().charAt(0);
            }
            b++;
            if (b == 3){
                a++;
                b = 0;
            }
        }
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        //TODO Implement this method
        player = 0;
    }
}
