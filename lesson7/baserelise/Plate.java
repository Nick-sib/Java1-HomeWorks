package lesson7.baserelise;

public class Plate {

    final int maxFood; // размер миски
    private int food = 0;

    public Plate(int plateSize) {
        maxFood = plateSize;
    }

    public String addFood(int val) {
        if (maxFood >= food + val) {
          food += val;
          return "в миску добавили " + val;
        }
        return "в миску не вошло " + val;
    }

    int getFood(int val) {
        if (food >= val) {
            food -=val;
            return val;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("В миске: %d еды", food);
    }
}
