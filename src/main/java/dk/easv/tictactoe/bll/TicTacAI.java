package dk.easv.tictactoe.bll;

public class TicTacAI {
    private final char aiSymbol;   // 'O'
    private final char playerSymbol; // 'X'

    public TicTacAI(char aiSymbol, char playerSymbol) {
        this.aiSymbol = aiSymbol;
        this.playerSymbol = playerSymbol;
    }

    public int[] getBestMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = aiSymbol;
                    int score = minimax(board, 0, false);
                    board[i][j] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        return move;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        Integer score = evaluate(board);
        if (score != null) return score;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = aiSymbol;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = playerSymbol;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        }
    }

    private Integer evaluate(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0] == aiSymbol ? 10 : -10;
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i] == aiSymbol ? 10 : -10;
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0] == aiSymbol ? 10 : -10;
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2] == aiSymbol ? 10 : -10;

        for (char[] row : board)
            for (char c : row)
                if (c == '-') return null;

        return 0; // draw
    }
}
