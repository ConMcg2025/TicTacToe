
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
    private char[][] board;
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
        int winner = getWinner();
        return (winner == 0 || winner == 1 ||  winner == -1);
    }

    public void updateBoard(char[][] board) {
        this.board = board;
    }

    private boolean isBoardFull(){
        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                if (chars[j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */


    public int getWinner()
    {
        for (int i = 0; i < 3; i++){ // Get clumns
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return (board[i][0] == 'X') ? 1 : 0;
            }
        }
        for (int i = 0; i < 3; i++){ // Get Rows
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return (board[0][i] == 'X') ? 1 : 0;
            }
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]){ // Check diagonally
            return (board[0][0] == 'X') ? 1 : 0;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]){ // Check diagonally the other way
            return (board[0][2] == 'X') ? 1 : 0;
        }
        if (isBoardFull()){ // Ask method to know if the board is full or not
            return -1; // Draw
        }
        return 99;
    }



    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        for (char[] r : board) // If new game reset the board to '-'
            java.util.Arrays.fill(r, '-');
        player = 0; // Set first player
    }
}
