import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson3_TaskExt1 {

    static Scanner sc = new Scanner(System.in);

    enum Directions {
        DOWN,
        RIHT,
        UP  ,
        LEFT;
//данный метод моджно заложить в ArrayState, но так проще изменять направления спирали
        public Directions getNext(Directions val) {
            switch (val){
                case DOWN: return RIHT;
                case RIHT: return UP  ;
                case UP  : return LEFT;
                case LEFT:
                default:   return DOWN;
            }
        }
    }

    static class ArrayState {
        int x, y, X_MAX, Y_MAX, X_MIN, Y_MIN;
        Directions direction;

        public ArrayState(int x, int y, int x_MAX, int y_MAX, int x_MIN, int y_MIN, Directions direction) {
            this.x = x;
            this.y = y;
            X_MAX = x_MAX;
            Y_MAX = y_MAX;
            X_MIN = x_MIN;
            Y_MIN = y_MIN;
            this.direction = direction;
        }

        void getNext() {
            switch (direction){
                case DOWN:
                    if (++y >= Y_MAX) {
                        direction = direction.getNext(direction);
                        Y_MAX--;
                        y = Y_MAX;
                        x++;
                    }
                    break;
                case RIHT:
                    if (++x >= X_MAX) {
                        direction = direction.getNext(direction);
                        X_MAX--;
                        x = X_MAX;
                        y--;
                    }
                    break;
                case UP:
                    if (--y <= Y_MIN) {
                        direction = direction.getNext(direction);
                        Y_MIN++;
                        y = Y_MIN;
                        x--;
                    }
                    break;
                case LEFT:
                    if (--x <= X_MIN) {
                        direction = direction.getNext(direction);
                        X_MIN++;
                        x = X_MIN;
                        y++;
                    }
                    break;
            }

        }
    }

    static boolean checkInputValue(String value, char min, char max) {
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(0) < min || value.charAt(0) > max)
                return false;
        }
        return true;
    }

    static int pleaseInputValue(String invateTXT, int minVal){
        System.out.println(invateTXT);
        String USER_string;
        while (true) {
            USER_string = sc.next().toLowerCase().trim();
            if (checkInputValue(USER_string, '0', '9')) {
                if (Integer.parseInt(USER_string) < minVal)
                    System.out.println(invateTXT + " введите число больше " + (minVal - 1));
                else break;
            }
            else System.out.println(invateTXT + " цифрами от 0 до 9");
        }
        return Integer.parseInt(USER_string);
    }

    static int[][] workARRAY;

    public static void main(String[] args) {
        drawSpiral(
                pleaseInputValue("Введите ширину массива", 1),
                pleaseInputValue("Введите высоту массива", 1));

    }

    static void drawSpiral(int height, int weight) {
        ArrayState arrayState = new ArrayState(0, 0, height, weight, 0, -1, Directions.DOWN);


        workARRAY = new int[weight][height];
        //да я на путал с ориентацией координат
        for (int curVAL = 1; curVAL < height * weight + 1; curVAL++) {
            workARRAY[arrayState.y][arrayState.x] = curVAL;
            arrayState.getNext();
        }


        String frm= " %0" + String.valueOf(height * weight).length() + "d";
        for (int [] i: workARRAY) {
            for (int j: i)
                System.out.printf(frm, j);
            System.out.println();
        }

    }


}
