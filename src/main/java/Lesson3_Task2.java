import java.util.Random;
import java.util.Scanner;

public class Lesson3_Task2 {

    private static final int SIZE = 15;
    private static final String[] WORDS = {"apple",  "orange", "lemon", "banana", "apricot",
             "avocado", "broccoli", "carrot", "cherry",  "garlic", "grape", "melon", "leak",
             "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
             "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String PC_string = WORDS[random.nextInt(WORDS.length)];
        char[] PC_chars = extractChars(PC_string);

        System.out.println("Компьютер уже загадал слово попробуйте угадать ");
        while (true) {
            String USER_string = sc.next().toLowerCase();
            char[] USER_chars = extractChars(USER_string);
            if (PC_string.equals(USER_string)) {
                System.out.println("Вы угадали! Слово: " + PC_string);
                break;
            } else {
                System.out.println("Почти угадали");
                for (int i = 0; i < SIZE; i++) {
                    System.out.print(USER_chars[i] == PC_chars[i]? USER_chars[i] : '#');
                }
                System.out.println("Попробуйте ещё");
            }
        }

    }

    static char[] extractChars(String value) {
        char[] res = new char[SIZE];
        String.format("%s%s", value, "###############")
                .getChars(0, SIZE, res, 0);

        return res;
    }
}
