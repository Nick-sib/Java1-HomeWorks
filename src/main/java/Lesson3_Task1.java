import java.util.Random;
import java.util.Scanner;

public class Lesson3_Task1 {

    public static void main(String[] args) {
        final int TRY_COUNT = 3;
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Угадайте число  от 0 до 9, с 3х попыток!");
        while (true) {
            int PC_value = random.nextInt(10);
            System.out.println("компьютер уже загадал число");

            for (int i = 1; i <= TRY_COUNT; i++) {
                System.out.printf("Ваша попытка %d)\n", i);
                String s = sc.next();
                if (!checkInputValue(s, '0', '9')) {
                    System.out.println("Почемуто Вы ввели не одну цифру или вообще не цифру!\nи потратили попытку");
                    continue;
                }
                int USER_value = Integer.parseInt(s);
                if (USER_value == PC_value) {
                    System.out.println("Вы угадали это число " + PC_value);
                    break;
                } else if (USER_value > PC_value) System.out.println("Загаданое число меньше чем ваше");
                else if (USER_value < PC_value) System.out.println("Загаданое число больше чем ваше");

                if (i == 3) System.out.println("Вам не удалось угадать число: " + PC_value);
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            String s = sc.next();
            if (checkInputValue(s, '0', '1')) {
                if (s.equals("0")) {
                    System.out.println("Всего доброго!");
                    break;
                } else {
                    System.out.print("Ещё раз ");
                }
            }
            else System.out.println("Вы промахнулись по \'1\', ничего страшного я всё равно вас понял!");
        }

    }

    static boolean checkInputValue(String value, char min, char max){
        return value.length() == 1 && value.charAt(0) >= min && value.charAt(0) <= max;
    }

}
