import java.math.BigInteger;

public class Lesson3_TaskExt2 {

    static int fibVer1(int val) {
        if (val == 1 || val == 0) {
            return 1;
        }
        else
            return fibVer1(val - 1) + fibVer1(val - 2);
    }

    static int sumSqwrVer1(int val){
        int per = 0;
        for (int i = 0; i < (val + 1); i++) {
            per += fibVer1(i) * 4;
        }
        return per;
    }

    static BigInteger sumSqwrVer2(int val) {
        //решение через массив позволит сократить кол-во операций но сожрёт больше памяти
        //ждать расчет числа 10000 утомительно поэтому память не жалеем
        if (val < 2)
            return new BigInteger(String.valueOf(val * 4));
        BigInteger[] fib = new BigInteger[val + 1];
        fib[0] = new BigInteger("1");
        fib[1] = new BigInteger("1");
        for (int i = 2; i < val + 1; i++) {
            fib[i] = fib[i-1].add(fib[i-2]);
        }

        BigInteger per = new BigInteger("0");
        for (BigInteger i: fib)
            per = per.add(i);

        return per.multiply(new BigInteger("4"));
    }

    public static void main(String[] args) {
        System.out.println(sumSqwrVer1(7));
        System.out.println(sumSqwrVer2(10000));

    }
}
