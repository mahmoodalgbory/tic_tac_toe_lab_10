import java.util.Scanner;

public class Main {

    //board display
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];

    // clear board
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = "   ";
            }
        }
    }

    // displays game board
    private static void display() {
        String displayBoard = "";
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (j == COL - 1) {
                    displayBoard += board[i][j];
                } else {
                    displayBoard += board[i][j] + "|";
                }
            }
            if (i != ROW - 1) {
                displayBoard += "\n---+---+---\n";
            }
        }
        System.out.println(displayBoard);
    }

    //check for the valid input 1-3
    private static boolean isValidMove(int row, int col) {
        return (board[row][col].equals("   "));
    }

    //Colum win condition
    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    //row win condition
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    //diagonal win condition
    private static boolean isDiagonalWin(String player) {
        if ((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))){
            return true;
        } else {
            return false;
        }
    }
    //is a win
    private static boolean isWin(String player) {
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        } else {
            return false;
        }
    }


    private static boolean isTie() {
        int count = 0;
        if (isTieRows()) {
            count++;
        }
        if (isTieCols()) {
            count++;
        }
        if (isTieDiagDown()) {
            count++;
        }
        if (isTieDiagUp()) {
            count++;
        }

        if (count >= 3) {
            return true;
        }
        return false;
    }


    private static boolean isTieRows() {
        int countX = 0;
        int countO = 0;
        int numDeadWinVectors = 0;
        for (int i = 0; i < ROW; i++) {
            countX = 0;
            countO = 0;
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" X ")) {
                    countX++;
                } else if (board[i][j].equals(" O ")) {
                    countO++;
                }
                if (countX >= 1 && countO >= 1) {
                    numDeadWinVectors++;
                }
            }
        }
        if (numDeadWinVectors >= 3) {
            return true;
        } else {
            return false;
        }
    }


    private static boolean isTieCols() {
        int countX = 0;
        int countO = 0;
        int numDeadWinVectors = 0;
        for (int i = 0; i < ROW; i++) {
            countX = 0;
            countO = 0;
            for (int j = 0; j < COL; j++) {
                if (board[j][i].equals(" X ")) {
                    countX++;
                } else if (board[j][i].equals(" O ")) {
                    countO++;
                }
                if (countX >= 1 && countO >= 1) {
                    numDeadWinVectors++;
                }
            }
        }
        if (numDeadWinVectors >= 3) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isTieDiagDown() {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < ROW; i++) {
            if (board[i][i].equals(" X ")) {
                countX++;
            } else if (board[i][i].equals(" O ")) {
                countO++;
            }
        }
        if (countX >= 1 && countO >= 1) {
            return true;
        } else {
            return false;
        }
    }


    private static boolean isTieDiagUp() {
        int countX = 0;
        int countO = 0;
        if (board[0][2].equals(" X ")) {
            countX++;
        } else if (board[0][2].equals(" O ")) {
            countO++;
        }
        if (board[1][1].equals(" X ")) {
            countX++;
        } else if (board[1][1].equals(" O ")) {
            countO++;
        }
        if (board[2][0].equals(" X ")) {
            countX++;
        } else if (board[2][0].equals(" O ")) {
            countO++;
        }
        if (countX >= 1 && countO >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int games = 0;
        int row;
        int col;
        String P1 = "First player";
        String P2 = "Second player";
        String player1 = " X ";
        String player2 = " O ";
        String currentMove = player1;
        String currentPlayer1o2;
        int moves;

        do {
            games++;
            moves = 0;
            clearBoard();
            display();
            for (int i = 0; i < 9; i++) { // maximum 9 turns for 9 spaces on the board
                if (i % 2 == 0) {
                    currentMove = player1;
                    currentPlayer1o2 = P1;
                } else {
                    currentMove = player2;
                    currentPlayer1o2 = P2;
                }
                System.out.printf("It is "+currentPlayer1o2 +"'s turn.");
                do {
                    row = SafeInput.getRangedInt(in, "Enter your row coordinate", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter your column coordinate", 1, 3) - 1;
                } while (!isValidMove(row, col));
                moves += 1;
                board[row][col] = currentMove;
                display();

                if (moves >= 5) { //check for win after 5 plays
                    if (isWin(currentMove)) {
                        System.out.printf(currentPlayer1o2 + " Wins! congrats!");
                        break;
                    } else if (moves >= 7) {
                        if (isTie()) {
                            System.out.println("Its a tie game!");
                            break;
                        }
                    }
                }
            }
            if (player1.equals(" X ")) {
                player1 = " O ";
                player2 = " X ";
            } else {
                player1 = " X ";
                player2 = " O ";
            }
        } while (SafeInput.getYNConfirm(in, "Would you like to play again Y/N"));
        System.out.printf("You have played " + games + " game(s)");
    }
}