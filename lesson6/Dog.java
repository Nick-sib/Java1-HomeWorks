package lesson6;


import java.util.Random;

public class Dog extends Animal{

    private static final String[] NAMES = {
            "Макс", "Бейли", "Чарли", "Бадди",
            "Рокки", "Джейк", "Джек", "Тоби"};

    //коэффициенты для полинома
    private static final double A1 = -514.81/113_696;
    private static final double A2 =  9_235.29/28_424;
    private static final double A3 = -893_794.51/113_696;
    private static final double A4 =  118_851.99/1_776.50;
    private static final double A5 = -82_943.29/1_421.20;

    //данные из интернета взяты рекордные показания тренированных собак
    private static final double MAXRUN  = 40000f;//м суточной пробег
    private static final double MAXJUMP = 1.6f;  //м прыжок ввысоту с разбега
    private static final double MAXSWIM = 15200f;//м рекорд от 2 сентября 1995 г

    public static final int MAXAGE = 29;
    private static final Random random = new Random();

    public Dog() {
        //если нам вообще лень задавать параметры то выбираем минимум наугад
        //рекорд продолжительности жизни 29 лет
        this(NAMES[random.nextInt(NAMES.length)],"разный",2 + random.nextInt(MAXAGE - 4));
    }

    public Dog(String name, String color, int age) {
        //если задали минимум посчитаем коэффицент мощи животного от возраста
        //для простоты задания будем считать мошь собаки по полиному 4 порядка
        //пик мощи от 5 до 9 лет дальше по ниспадающей
        this(name, color, age,
                Math.min(1, (A1 * Math.pow(age, 4) +
                             A2 * Math.pow(age, 3) +
                             A3 * Math.pow(age, 2) +
                             A4 * age + A5) / 100));
    }

    private Dog(String name, String color, int age, double ageCoef) {
        this(name, color, age, MAXRUN * ageCoef,
                              MAXSWIM * ageCoef,
                              MAXJUMP * ageCoef);
    }

    public Dog(String name, String color, int age, double maxRun, double maxSwim, double maxJump) {
        super(name, color, age, maxRun, maxSwim, maxJump);
    }

    @Override
    void swim(int dist) {
        System.out.printf("%s.swim(%d); -> результат: swim: %b\n",name , dist, ((float) dist < getMaxSwim()));
    }

    @Override
    public String toString() {
        return String.format("Собака '%s', возраст %d",  name, getAge());
    }
}
