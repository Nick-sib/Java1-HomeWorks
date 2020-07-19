package lesson7.listnerrelise;


import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        final int CATSCOUNT = 5;
        final String[] CATSNAMES = {"Дымок", "Уголек", "Апельсин", "Лисенок", "Персик", "Мартин", "Котяра", "Киса"};
        Random random = new Random();
        Plate plate = new Plate(50 + random.nextInt(50));
        Cat[] cats = new Cat[CATSCOUNT];
        for (int i = 0; i < CATSCOUNT; i++) {
            cats[i] = new Cat(CATSNAMES[random.nextInt(CATSNAMES.length)], 1 + random.nextInt(10));
            plate.addEater(cats[i]);
        }


        System.out.printf("У нас есть %d голодных котов\nи миска размером %d\n", CATSCOUNT, plate.maxFood);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\nНаполните миску покормите котов\nвведите число от 1 до 200\n\n0 - выход\n->");
            int val = sc.nextInt();
            if (val == 0) break;
            plate.addFood(val, true);
            boolean allCatSatiety = true;
            for (Cat cat: cats) {
                allCatSatiety = allCatSatiety && cat.isSatiety();
            }
            System.out.println("----------------\n" + plate);
            for (Cat cat: cats) {
                System.out.println(cat);
            }
            if (allCatSatiety) {
                System.out.println("\n!все сыты!");
                break;
            }
        }
    }
}
