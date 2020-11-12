package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int occupied = 0;

        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }

        printField(field);
        int move = 1;
        boolean gameOver = false;
        while (!gameOver) {
            playerMove(sc, field, figure(move));
            occupied += 1;
            move *= -1;
            printField(field);
            if (checkState(field).length() > 0) {
                System.out.println(checkState(field));
            } else if (occupied == 9) {
                System.out.println("Draw");
                gameOver = true;
            }
            if (checkState(field).equals("X wins") || checkState(field).equals("O wins")) {
                gameOver = true;
            }
        }
    }

    private static char figure(int move) {
        if (move > 0) return 'X';
        return 'O';
    }

    private static void playerMove(Scanner sc, char[][] field, char figure) {
        System.out.print("Enter the coordinates: ");
        while (sc.hasNext()) {
            char x = sc.next().charAt(0);
            char y = sc.next().charAt(0);
            if (!Character.isDigit(x) || !Character.isDigit(y)) {
                System.out.print("You should enter numbers! ");
                continue;
            }

            int xx = Character.getNumericValue(x);
            int yy = Character.getNumericValue(y);
            if (xx > 3 || xx < 1 || yy > 3 || yy < 1) {
                System.out.print("Coordinates should be from 1 to 3! ");
                continue;
            } else if (field[3 - yy][xx - 1] == 'X' ||
                    field[3 - yy][xx - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            field[3 - yy][xx - 1] = figure;
            break;
        }
    }

    private static void printField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static String checkState(char[][] arr) {
        int tempRow = 0;
        int tempColumn = 0;
        int tempMainLine = 0;
        int tempSideLine = 0;
        String str = "";
        for (int i = 0; i < 3; i++) {
            tempMainLine += arr[i][i];
            tempSideLine += arr[i][arr.length - 1 - i];
            for (int j = 0; j < 3; j++) {
                tempRow += arr[i][j];
                tempColumn += arr[j][i];
            }
            if (tempRow == 264 || tempColumn == 264
                    || tempMainLine == 264 || tempSideLine == 264) {
                if (str.length() > 0) {
                    str = "Impossible";
                } else {
                    str = "X wins";
                }
            } else if (tempRow == 237 || tempColumn == 237
                    || tempMainLine == 237 || tempSideLine == 237) {
                if (str.length() > 0) {
                    str = "Impossible";
                } else {
                    str = "O wins";
                }
            }
            tempColumn = 0;
            tempRow = 0;
        }
        return str;
    }
}