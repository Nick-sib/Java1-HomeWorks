package lesson5;

public final class Person {
    protected static final String SPACE = "\n    " ;

    protected String fio = "None";
    protected String position = "None";
    protected String email = "none@none.com";
    protected long phone = 0l;
    protected float salary = 0f;
    protected int age = -1;

    public Person(String fio, String position, String email, String phone, float salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email.toLowerCase();
        String ph = "";
        for (int i = 0; i < phone.length(); i++) {
            char ch = phone.charAt(i);
            if (ch >= '0' && ch <= '9') ph = ph + ch;
        }
        this.phone = Long.parseLong(ph);
        this.salary = salary;
        this.age = age;
    }

    private String toPhone(){
        return String.valueOf(phone)
                        .replaceFirst("(\\d{3})(\\d{3})(\\d{2})(\\d+)",
                                "+7 ($1) $2-$3-$4");

    }

    @Override
    public String toString() {
        return String.format("Сотрудник {%sФИО: %s%sдолжность: %s%semail: %s%s" +
                "телефон: %s%sзарплата: %.1f ₽%sвозраст: %d }", SPACE, fio, SPACE, position, SPACE, email
                ,SPACE, toPhone(), SPACE, salary, SPACE, age);
    }

    public void print(){
        System.out.println(toString());
    }

    public void println(){
        System.out.println(toString()+"\n");
    }

    public boolean isOlder(int value){
        return age > value;
    }

    public int getAge() {
        return age;
    }
}
