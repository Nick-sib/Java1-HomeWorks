public class Main {

    //task1
    public static void main(String[] args) {
        //task2 создадим только примитивы и String
        boolean isBool = false;

        byte myByte = 0;
        short myShort = 0;
        int myInt = 0;
        float myFloat = 0f;
        double myDouble = 0d;
        long myLong = 0l;

        char myChar = 0;
        String myString = "0";

        System.out.println(task3(1,2,3,4));
        System.out.println(task4(1,2));
        task5(0);
        System.out.println(task6(0));
        task7("Тимофей");
        task8(2020);
    }

    static float task3(int a, int b, int c, int d){
        return a * (b + ((float) c / d));
    }

    static boolean task4(int a, int b){
        return (a + b) > 9 && (a + b) < 21;
    }

    static void task5(int a){
        System.out.println(a > -1 ? "положительное" : "трицательное");
    }


    static boolean task6(int a){
        return a < 0;
    }

    static void task7(String name){
        System.out.printf(String.format("Привет, %s!\n", name));
    }

    static boolean task8(int year){
        /*по заданию не совсем понятно каждый 100 "год" или каждый 100 "высокосный год", также 400
        * будем считать год как таковой*/
        boolean res = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        if (res)
            System.out.println("Високосный");
        return res;

    }
}
