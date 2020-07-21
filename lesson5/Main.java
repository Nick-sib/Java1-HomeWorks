package lesson5;

import java.util.Random;

public class Main {

    public static final String[] FIOS  = {
            "Игнатьев Илларион Михайлович",
            "Богданов Август Лукьянович",
            "Максимов Георгий Вадимович",
            "Пестов Лукьян Филиппович",
            "Гаврилов Оскар Антонович",
            "Александров Азарий Протасьевич",
            "Горшков Роман Всеволодович",
            "Савельев Святослав Игнатьевич",
            "Макаров Витольд Рубенович",
            "Кузнецов Альфред Макарович",
            "Доронина Гелианна Платоновна",
            "Шилова Аделия Евгеньевна",
            "Тетерина Северина Лукьевна",
            "Белова Нинна Максовна",
            "Волкова Дионисия Давидовна",
            "Мартынова Жаклин Артёмовна",
            "Веселова Елизавета Руслановна",
            "Полякова Станислава Руслановна",
            "Силина Алевтина Макаровна",
            "Кононова Альжбета Валентиновна"};

    public static final String[] POSITIONS  = {
            "Подолог",
            "Учитель",
            "Технолог",
            "Диетолог",
            "Топограф",
            "Архитектор",
            "Кавалерист",
            "Химик-аналитик",
            "Сталевар",
            "Плиточник"};

    public static final String[] EMAILS  = {
            "bhvtpb@mail.ru",
            "fitlef@gmail.com",
            "gqepft@yandex.ru",
            "ihqeof@mail.ru",
            "kdined@gmail.com",
            "mfvcre@yandex.ru",
            "pjbzcu@mail.ru",
            "sannnq@gmail.com",
            "vnyjkw@yandex.ru",
            "xreuab@mail.ru",
    };

    public static final String[] PHONES  = {
            "7309016891",
            "5617558509",
            "7855955546",
            "6724412061",
            "6602987533",
            "7610512583",
            "9050880655",
            "8618243479",
            "1869179959",
            "4209237581",
            "2628530846",
            "3455919803",
            "9224067051",
            "9049918712",
            "3600881409",
            "4515165242",
            "7455644884",
            "5334917441",
            "8712709247",
            "9465921423"
    };

    public static final int AGE_TASK = 40;

    static Random random = new Random();


    public static void main(String[] args) {
        Person[] persArray = new Person[5];

        for (int i = 0; i < persArray.length; i++) {
            persArray[i] = new Person(
                    FIOS[random.nextInt(FIOS.length)],
                    POSITIONS[random.nextInt(POSITIONS.length)],
                    EMAILS[random.nextInt(EMAILS.length)],
                    PHONES[random.nextInt(PHONES.length)],
                    random.nextFloat() * 50_000 + 100_000,
                    random.nextInt(30) + 20);
        }
        boolean finded = false;
        for (Person person: persArray) {
            if (person.isOlder(AGE_TASK)) {
                person.println();
                finded = true;
            }
        }
        if (!finded) System.out.printf("Сотрудников старше %d не найдено", AGE_TASK);
    }

}
