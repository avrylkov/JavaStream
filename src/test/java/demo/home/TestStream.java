package demo.home;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {

    private static List<Person> personList = new ArrayList<>();

    static {
        personList.add(new Person("Александр", "Рыльков", 50, Arrays.asList("Работа", "Спорт")));
        personList.add(new Person("Вячеслав", "Рыльков", 15, Arrays.asList("Учеба", "Спорт")));
        personList.add(new Person("Никита", "Сомов", 17, Arrays.asList("Игры", "Отдых")));
    }

    @Test
    public void testFilter() {
        List<Person> filterdPersons = personList.stream()
                .filter(person -> person.getAge() > 15)
                .collect(Collectors.toList());
        Assert.assertEquals(filterdPersons.size(), 2);
    }

}
