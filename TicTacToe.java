import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        char currentPlayer = 'X';

        printBoard(board);

        while (true) {
            int row = 0;
            int col = 0;

            // Input pemain
            while (true) {
                try {
                    System.out.print("Enter the coordinates (row and column): ");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (isValidMove(board, row, col)) {
                        break;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid row and column.");
                    scanner.nextLine(); // Clear input buffer
                }
            }

            // Update papan
            board[row][col] = currentPlayer;
            printBoard(board);

            // Periksa kemenangan atau seri
            if (isGameOver(board, currentPlayer)) {
                if (currentPlayer == 'X') {
                    System.out.println("Player X wins!");
                } else {
                    System.out.println("Player O wins!");
                }
                break;
            } else if (isBoardFull(board)) {
                System.out.println("It's a draw!");
                break;
            }

            // Ganti giliran pemain
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    public static boolean isGameOver(char[][] board, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Baris
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Kolom
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Diagonal
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Diagonal
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false; // Terdapat celah kosong
                }
            }
        }
        return true; // Semua sel terisi
    }
}
