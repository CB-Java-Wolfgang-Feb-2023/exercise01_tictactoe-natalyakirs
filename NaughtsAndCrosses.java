package unit08;

import java.util.Random;
import java.util.Scanner;
public class NaughtsAndCrosses {
    final char sign_X = 'x';
    final char sign_0 = 'o';
    final char sign_empty = '.';
    char [][] table;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new NaughtsAndCrosses().game();
    }
    NaughtsAndCrosses() {
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[3][3];
    }
    void game() {
        /*
        Game logic:
        while(true)
        // Player's turn
        check: Player won or tie (tell this and end cycle)
        // Computer's turn
        check: Computer won or tie (tell this and end cycle)
         */
        initTable();
        while (true) {
            turnPlayer();
            if (checkWin(sign_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, TIE!");
                break;
            }
            turnSkynet();
            printTable();
            if (checkWin(sign_0)) {
                System.out.println("Skynet WON!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry, TIE!");
                break;
            }
        }
        System.out.println("GAME OVER!");
        printTable();
    }

    // Initialize Table
    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = sign_empty;
        printTable();

    }

    // Current state of the Table
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }
    }
    // Allow a turn of Player
    void turnPlayer() {
        int x, y;
        do {
            System.out.println("Dear Player, enter X and Y (1..3):");
            x = scanner.nextInt() - 1; // to take into account o start of i and j
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = sign_X;
    }
    // Check the validity of the entered numbers by Player and check if the coordinates have been already used
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return false;
        return table[y][x] == sign_empty;
    }
    //  Allow a turn of Skynet - random number generation (not ideal option!)
    void turnSkynet() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = sign_0;
    }

     // Method checkWin  with using char dot parameter - universal (both Os and Xs)
    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||        // checking rows
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))  // checking columns
                return true;
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||            // checking main matrix diagonal
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot))      // checking secondary matrix diagonal
            return true;
        return false;
    }
    // Method isTableFull - stop cycle (if all cells are occupied) or continue
    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == sign_empty)
                    return false;
        return true;
    }
    }



