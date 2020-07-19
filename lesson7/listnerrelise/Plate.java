package lesson7.listnerrelise;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    final int maxFood; // размер миски
    private int food = 0;
    private List<Eaters> eatersList = new ArrayList<>();

    public Plate(int plateSize) {
        maxFood = plateSize;
    }

    public void addEater(Eaters eater) {
        this.eatersList.add(eater);
        //в нашей реализации коты не умирают поэтому удалять едоков не будем
    }

    public void addFood(int val, boolean print) {
        if (maxFood >= food + val) {
            food += val;
            if (print) System.out.println("в миску добавили " + val);
            for (Eaters eater : eatersList) {
                eater.update(this);
            }

        }
        else if (print) System.out.println("в миску не вошло " + val);
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
