/*
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

package lesson8;

import java.util.Arrays;
import java.util.Random;

public class TicTacToe {

    final int SIZE;
    final int DOTS_TO_WIN;// = 4;

    private final char DOT_EMPTY = '•';
    public final char DOT_HUMAN = 'X';
    public final char DOT_AI = '0';
    private final String EMPTY = " ";

    private final char[][] map;
    private final Random random = new Random();

    public TicTacToe(int size) {
        SIZE = size;

        DOTS_TO_WIN = size == 3 ? size : 4;
        map = new char[SIZE][SIZE];

        initMap();


    }

    public void initMap() {
        for (char[] line : map)
            Arrays.fill(line, DOT_EMPTY);
        printMap();
    }

    public void printMap() {
        //не вижу большого смысла отдельно печатать шапки и тела
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++)
            printNumber(i);

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

    public void printNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }


    public boolean humanTurn(int x, int y) {
        if (isCellValid(x,y,DOT_HUMAN)) {
            map[x][y] = DOT_HUMAN;
            return true;
        }
        return false;
    }

    private boolean isCellValid(int rowNumber, int colNumber, char symbol) {
        boolean isHuman = symbol == DOT_HUMAN;
        if (map[rowNumber][colNumber] != DOT_EMPTY) {
            if (isHuman)
                System.out.println("\nВы выбрали занятую ячейку");
            return false;
        }
        return true;
    }

    public int aiTurn() {
        int aiColNumber;
        int aiRowNumber;
        do {
            aiRowNumber = random.nextInt(SIZE);
            aiColNumber = random.nextInt(SIZE);
        } while (!isCellValid(aiRowNumber, aiColNumber, DOT_AI));

        map[aiRowNumber][aiColNumber] = DOT_AI;
        printMap();

        return aiRowNumber * SIZE + aiColNumber;
    }

    public boolean checkEnd(int row, int col, String winMessage) {
        if (checkWin(row, col)) {
            System.out.println(winMessage);
            return true;
        }

        if (isMapFull()) {
            System.out.println("Ничья!");
            return false;
        }

        return false;
    }

    private boolean checkWin(int row, int col) {
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

    private boolean inRange(int row, int col) {
        //проверяем ранг координаты
        return row > -1 && row < SIZE && col > -1 && col < SIZE;
    }

    public boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }
}
