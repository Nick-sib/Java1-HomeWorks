package lesson7.baserelise;

public class Cat {
    final String name;
    final int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String eat (Plate plate) {
        if (!satiety) {
            satiety = appetite == plate.getFood(appetite);
            return satiety ? String.format("%s: съел %d",name, appetite) : name + ": не поел";
        }
        return name + ": уже сыт";
    }

    public boolean isSatiety() {
        return satiety;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, satiety ? "сыт" : "голоден");
    }
}
