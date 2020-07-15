package lesson6;

public abstract class Animal {
    protected String name;
    private String color;
    private int age;

    private double maxRun;
    private double maxSwim;
    private double maxJump;

    public Animal(String name, String color, int age, double maxRun, double maxSwim, double maxJump) {
        this.name = name;
        this.color = color;
        this.age = age;
        int maxage = 0;
        if (getClass() == Dog.class) {
            maxage =((Dog) this).MAXAGE;
        } else if (getClass() == Cat.class) {
            maxage =((Cat) this).MAXAGE;
        }
        if (age <= 0) {
            System.out.println("Животное еще не родилось");
            this.maxRun = 0;
            this.maxSwim = 0;
            this.maxJump = 0;
        } else if (age > maxage) {
            System.out.println("Животное уже мертво");
            this.maxRun = 0;
            this.maxSwim = 0;
            this.maxJump = 0;
        } else {
            this.maxRun = maxRun;
            this.maxSwim = maxSwim;
            this.maxJump = maxJump;
        }
    }

    public int getAge() {
        return age;
    }

    void run(int dist){
        System.out.printf("%s.run(%d); -> результат: run: %b\n",name , dist, ((float) dist < maxRun));
    }

    abstract void swim(int dist);

    void jump(float dist){
        System.out.printf("%s.jump(%.1f); -> результат: jump: %b\n",name , dist, dist < maxJump);
    }

    protected double getMaxSwim() {

        return maxSwim;
    }


}
