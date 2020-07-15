package lesson6;

import java.util.Random;

public class Cat extends Animal {

    private static final String[] NAMES = {"Дымок", "Уголек", "Апельсин", "Лисенок", "Персик"};

    //коэффициенты для полинома
    private static final double A1 = -7.419361/4431822.595200;
    private static final double A2 = 62.881087/177272.903808;
    private static final double A3 = -297.538091/10942.771840;
    private static final double A4 = 871792.783801/886364.519040;
    private static final double A5 = -9571072.797733/553977.824400;
    private static final double A6 = 204098.616005/1641.415776;
    private static final double A7 = -1711.060187/15.987816;

    //данные из интернета взяты рекордные показания тренированных кошек
    private static final double MAXRUN  = 200.0f;//м суточной пробег хорошо замотивированного кота
    private static final double MAXJUMP = 2.3f;  //м прыжок ввысоту с разбега
    private static final double MAXSWIM = 0f;    //м интернет говорить что кот может проплыть 300м, будем считать что кот не мотивирован

    public static final int MAXAGE = 38;
    private static final Random random = new Random();

    public Cat() {
        //если нам вообще лень задавать параметры то выбираем минимум наугад
        //рекорд продолжительности жизни 29 лет
        this(NAMES[random.nextInt(NAMES.length)],"разный",2 + random.nextInt(MAXAGE - 4));
    }

    public Cat(String name, String color, int age) {
        //пик мощи от 4 до 10 лет дальше по ниспадающей
        this(name, color, age,
                Math.min(1, (A1 * Math.pow(age, 6) +
                        A2 * Math.pow(age, 5) +
                        A3 * Math.pow(age, 4) +
                        A4 * Math.pow(age, 3) +
                        A5 * Math.pow(age, 2) +
                        A6 * age + A7) / 100));
    }

    private Cat(String name, String color, int age, double ageCoef) {
        this(name, color, age, MAXRUN * ageCoef,
                MAXSWIM * ageCoef,
                MAXJUMP * ageCoef);
    }

    public Cat(String name, String color, int age, double maxRun, double maxSwim, double maxJump) {
        super(name, color, age, maxRun, maxSwim, maxJump);
    }

    @Override
    void swim(int dist) {
        System.out.println((float) dist < getMaxSwim()
                ? name + " конечно может, но не будет"
                : name + " и не может и пробовать не будет");
    }

    @Override
    public String toString() {
        return String.format("Кот '%s', возраст %d",  name, getAge());
    }
}
