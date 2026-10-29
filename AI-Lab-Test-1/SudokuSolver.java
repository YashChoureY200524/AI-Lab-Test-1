/**
 * Basic 9x9 Sudoku solver using backtracking.
 * 0 represents an empty cell.
 */
public class SudokuSolver {
    static final int SIZE = 9;

    // Predefined puzzle; zero means blank.
    static int[][] board = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static void main(String[] args) {
        System.out.println("Original Sudoku:");
        printBoard();

        if (!isInitialBoardValid()) {
            System.out.println("The puzzle is invalid: duplicate or out-of-range given value.");
            return;
        }

        if (solve()) {
            System.out.println("\nSolved Sudoku:");
            printBoard();
        } else {
            System.out.println("No solution exists for this Sudoku puzzle.");
        }
    }

    static boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int digit = 1; digit <= 9; digit++) {
                        if (isSafe(row, col, digit)) {
                            board[row][col] = digit;

                            if (solve()) {
                                return true;
                            }

                            // Backtrack if this choice leads to no solution.
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // No empty cells remain.
    }

    static boolean isSafe(int row, int col, int digit) {
        // Check the row and column.
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == digit || board[i][col] == digit) {
                return false;
            }
        }

        // Find the top-left corner of the 3x3 subgrid.
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isInitialBoardValid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int digit = board[row][col];
                if (digit < 0 || digit > 9) {
                    return false;
                }
                if (digit != 0) {
                    board[row][col] = 0;
                    boolean safe = isSafe(row, col, digit);
                    board[row][col] = digit;
                    if (!safe) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
