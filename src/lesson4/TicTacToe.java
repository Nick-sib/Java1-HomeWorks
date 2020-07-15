/**
 * Порядок выполнения:
 * 1. main() -> initMap() - инициализация массива
 * -------------------------------------------
 * 2. main() -> printMap() - печать массива
 * 3. printMap() -> объеденил
 * -------------------------------------------
 * 5. main() -> playGame() - запуск игры. 2 части: человек и машина
 * * -------------------------------------------
 * 6. playGame() -> humanTurn() - ввод числа в терминал
 * 7. humanTurn -> isCellValid - проверка на валидность введенного числа
 * 8. playGame() -> printMap (см п.3-4)
 * * -------------------------------------------
 * 9. playGame() -> checkEnd() - проверка на завершение
 * 10. checkEnd -> checkWin() - проверка на победу
 * 11. checkEnd -> isMapFull() - проверка на ничью
 * * -------------------------------------------
 * 12. п.6-11, но с aiTurn, вместо humanTurn
 * * -------------------------------------------
 * 13. повторение п.6-12
 */

package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';
    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";

    public static char[][] map = new char[SIZE][SIZE];
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    static int humanRowNumber, humanColNumber,
               aiRowNumber, aiColNumber;

    public static void main(String[] args) {

        turnGame();

    }

    public static void turnGame() {
        initMap();

        printMap();

        playGame();
    }

    public static void playGame() {
        while (true) {
            humanTurn();
            printMap();
            if (checkEnd(humanRowNumber,humanColNumber, "Вы выиграли!")) {
                System.exit(0);
            }

            aiTurn();
            printMap();
            if (checkEnd(aiRowNumber, aiColNumber,"К сожалению, Вы проиграли...")) {
                System.exit(0);
            }

        }
    }

    public static void initMap() {
        for (char[] line : map) {
            Arrays.fill(line, DOT_EMPTY);
        }
    }

    public static void printMap() {
        //не вижу большого смысла отдельно печатать шапки и тела
        System.out.print(FIRST_EMPTY_CHAR);
        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }


    private static void humanTurn() {
        //Добавил проверку ввода не числа
        System.out.println("Ход пользователя! Введите номера строки и столбца");
        do {
            humanRowNumber = iputValue(String.format("Введите номер с строки [1..%s] ",SIZE));
            humanColNumber = iputValue(String.format("Введите номер с столбца [1..%s] ",SIZE));
        } while (!isCellValid(humanRowNumber, humanColNumber, DOT_HUMAN));

        map[humanRowNumber - 1][humanColNumber - 1] = DOT_HUMAN;
    }

    private static int iputValue(String invateSTR) {
        String val;
        do {
            System.out.print(invateSTR);
            val = scanner.next();
        } while (!checkInputValue(val, '1', String.valueOf(SIZE).charAt(0)));
        return Integer.parseInt(val);
    }

    private static boolean checkInputValue(String value, char min, char max) {
        //По заданию предпологаюся размеры массива 3 и 5 поэтому тип переменных один символ
        return value.length() == 1 && value.charAt(0) >= min && value.charAt(0) <= max;
    }

    private static boolean isCellValid(int rowNumber, int colNumber, char symbol) {

        boolean isHuman = symbol == DOT_HUMAN;

        if ((rowNumber < 1 || rowNumber > SIZE) || (colNumber < 1 || colNumber > SIZE)) {
            if (isHuman)
            System.out.println("\nПроверьте значения строки и столбца!");
            return false;
        }

        if (map[rowNumber - 1][colNumber - 1] != DOT_EMPTY) {
            if (isHuman)
            System.out.println("\nВы выбрали занятую ячейку");
            return false;
        }
        return true;
    }

    private static void aiTurn() {
        //int rowNumber, colNumber;

        do {
            aiRowNumber = random.nextInt(SIZE) + 1;
            aiColNumber = random.nextInt(SIZE) + 1;

        } while (!isCellValid(aiRowNumber, aiColNumber, DOT_AI));

        map[aiRowNumber - 1][aiColNumber - 1] = DOT_AI;
    }

    private static boolean checkEnd(int row, int col, String winMessage) {
        if (checkWin(row-1, col-1)) {
            System.out.println(winMessage);
            return true;
        }

        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean checkWin(int row, int col) {
        char symbol = map[row][col];
        int delta = DOTS_TO_WIN - 1;
        //проверяем горизонтать
        int count = 0;
        for (int i = Math.max(0,row - delta); i < Math.min(row + delta, SIZE); i++) {
            if (map[i][col] == symbol) count++;
            else count = 0;
            if (count >= DOTS_TO_WIN) return true;
        }
        //проверяем вертикаль
        count = 0;
        for (int i = Math.max(0,col - delta); i < Math.min(col + delta, SIZE); i++) {
            if (map[row][i] == symbol) count++;
            else count = 0;
            if (count >= DOTS_TO_WIN) return true;
        }
        //проверяем основную диагональ
        count = 0;
        for (int i = 1 - DOTS_TO_WIN; i < DOTS_TO_WIN; i++) {
            if (inRange(row+i, col+i))
                if (map[row+i][col+i] == symbol) count++;
                else count = 0;
            if (count >= DOTS_TO_WIN) return true;
        }
        //проверяем доп диагональ
        count = 0;
        for (int i = 1 - DOTS_TO_WIN; i < DOTS_TO_WIN; i++) {
            if (inRange(row+i, col-i))
                if (map[row+i][col-i] == symbol) count++;
                else count = 0;
            if (count >= DOTS_TO_WIN) return true;
        }
        return false;
    }

    private static boolean inRange(int row, int col) {
        //проверяем ранг координаты
        return row > -1 && row < SIZE && col > -1 && col < SIZE;
    }

    private static boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY)
                    return false;
            }
        }
        return Boolean.TRUE;
    }
}
