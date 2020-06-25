import java.util.Arrays;
import java.util.Random;

public class Lesson2_Main {


    public static void main(String[] args) {
        //требуется java8 и да это медленные методы for быстрее

        //task1
        System.out.println("task1");
        int[] task1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Исходный массив \n" + Arrays.toString(task1));
        Arrays.parallelSetAll(task1, i -> task1[i] == 1? 0 : 1);
        System.out.printf("Отредактированный массив \n%s\n\n", Arrays.toString(task1));


        //task2
        System.out.println("task2");
        int[] task2 = new int[8];
        Arrays.setAll(task2, i -> i * 3);
        System.out.println(Arrays.toString(task2)+"\n\n");


        //task3
        //немного хитро указано условие задания нет указания что заменить элементы массива
        //"пройти по нему циклом, и числа меньшие 6 умножить на 2" поэтому просто числа просто умножаем
        System.out.println("task3");
        int[] task3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i: task3)
            System.out.printf("%s, ",i < 6? i * 2 : i);
        System.out.println("\n\n");

        //task4
        System.out.println("task4");
        final int SIZE = 8;
        int[][] task4 = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            task4[i][i] = 1;
        Arrays.stream(task4).forEach(iline -> System.out.println(Arrays.toString(iline)));

        //task5
        System.out.println("\n\ntask5");
        Random random = new Random();
        int[] task5 = {random.nextInt(10),random.nextInt(10), random.nextInt(10),
                random.nextInt(10), random.nextInt(10), random.nextInt(10),
                random.nextInt(10), random.nextInt(10), random.nextInt(10)};
        System.out.printf(
                "%s\n max = %d\n min = %d\n\n\n",
                Arrays.toString(task5),
                Arrays.stream(task5).max().getAsInt(),
                Arrays.stream(task5).min().getAsInt());

        //task6
        System.out.println("task6");
        int[] task6 = new int[10];
        Arrays.setAll(task6, i -> random.nextInt(10));
        System.out.printf(
                "%s\n%s\n\n\n",
                Arrays.toString(task6),
                isEquilibriumArray(task6)? "Равновесный": "Не равновесный");

        //task7
        System.out.println("task7");
        //с System.arraycopy всё решается в 2 строчки но это создание новых массивов
        //моё решение топорное но массив по факту остаётся один и тотже
        int[] task7 = {1,2,3,4,5,6,7,8,9};
        displaceVal(task7, random.nextInt(10) - 5);
        System.out.println(Arrays.toString(task7));
    }

    static boolean isEquilibriumArray(int[] inArray) {
        if (inArray.length < 2) return false;
        for (int i = 1; i < inArray.length; i++) {
            if (Arrays.stream(Arrays.copyOfRange(inArray,0, i)).sum() ==
                Arrays.stream(Arrays.copyOfRange(inArray, i, inArray.length)).sum()) {
                System.out.printf("%s || %s\n",
                        Arrays.toString(Arrays.copyOfRange(inArray,0, i)),
                        Arrays.toString(Arrays.copyOfRange(inArray, i, inArray.length)));
                return true;
            }
        }
        return false;
    }

    static void displaceVal(int[] inArray, int val) {
        System.out.println("Смещаем на " + val);
        if (val > 0)
            while (val > 0) {
                displaceUnoRight(inArray);
                val--;
            }
        else
            while (val < 0) {
                displaceUnoLeft(inArray);
                val++;
            }
    }

    static void displaceUnoRight(int[] inArray) {
        int remember = inArray[inArray.length - 1];
        for (int i = inArray.length - 1; i > 0 ; i--) {
            inArray[i] = inArray[i-1];
        }
        inArray[0] = remember;
    }

    static void displaceUnoLeft(int[] inArray) {
        //12345
        //23451
        int remember = inArray[0];
        for (int i = 0; i < inArray.length - 1 ; i++) {
            inArray[i] = inArray[i+1];
        }
        inArray[inArray.length-1] = remember;
    }

}
