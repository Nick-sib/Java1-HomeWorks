package lesson6;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random RNDM = new Random();

    enum AnimalClass{
        CAT,
        DOG,
    }
    enum ObstaclesClass{
        RUN,
        JUMP,
        SWIM
    }

    public static void main(String[] args) {
        Animal animal = setCompetotor();
        System.out.println(animal.toString());
        obstacle (animal);
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("1- провести новое испытание\n2 - сменить испытуемого\n\n0 - выход\n->");
            String val = sc.next();
            if (!checkInputValue(val, '0', '2'))
                System.out.print("1- провести новое испытание\n2 - сменить испытуемого\n\n0 - выход\n->");

            char ch = val.charAt(0);
            switch (ch) {
                case '0':
                    return;
                case '1':
                    obstacle (animal);
                    break;
                case '2':
                    animal = setCompetotor();
                    System.out.println(animal.toString());
                    break;
            }
        }
    }

    static Animal setCompetotor(){
        switch (AnimalClass.values()[RNDM.nextInt(AnimalClass.values().length)]) {
            case CAT:
                return new Cat();
            case DOG:
            default:
                return new Dog();
        }
    }

    static void obstacle (Animal animal) {
        switch (ObstaclesClass.values()[RNDM.nextInt(ObstaclesClass.values().length)]) {
            case RUN:
                animal.run(RNDM.nextInt(100_000));
                break;
            case JUMP:
                animal.jump(3 * RNDM.nextFloat());
                break;
            case SWIM:
            default:
                animal.swim(RNDM.nextInt(15200));
        }
    }

    static boolean checkInputValue(String value, char min, char max){
        return value.length() == 1 && value.charAt(0) >= min && value.charAt(0) <= max;
    }

}
