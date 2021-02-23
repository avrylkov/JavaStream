package demo.home;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
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

    @Test
    public void testMatch() {
        Optional<Person> person1 = personList.stream().filter(p -> p.getLastName().startsWith("Р")).findAny();
        Assert.assertTrue(person1.isPresent());
        Person person = person1.get();

        person1 = personList.stream().filter(p -> p.getLastName().startsWith("Ра")).findAny();
        Assert.assertFalse(person1.isPresent());

        boolean match = personList.stream().anyMatch(p -> p.getLastName().startsWith("Р"));
        Assert.assertFalse(match);
    }

    @Test
    public void testMap() {
        List<String> strings = personList.stream()
                .map(person -> person.getFirstName() + " " + person.getLastName().substring(0, 1) + ".")
                .collect(Collectors.toList());
        Assert.assertEquals(strings.size(), 3);
        strings.forEach(s -> System.out.println(s));
    }

    private String getKey(Person person) {
        return person.getLastName() + person.getFirstName();
    }

    @Test
    public void testMapToHashMap() {
        List<String> collect = personList.stream().map(p -> p.getLastName().substring(0, 3)).collect(Collectors.toList());

        Map<String, Person> personMap = personList.stream()
                .collect(Collectors.toMap(this::getKey, Function.identity()));
        Assert.assertEquals(personMap.size(), 3);

        Person person = personMap.get("РыльковАлександр");
        Assert.assertNotNull(person);

        person = personMap.get("Рыльков");
        Assert.assertNull(person);
    }

    @Test
    public void testMapToSet() {
        Set<String> collect = personList.stream().map(p -> p.getFirstName() + " " + p.getLastName()).collect(Collectors.toSet());
        Assert.assertEquals(collect.size(), 3);

        Set<String> collect2 = personList.stream().map(p -> p.getLastName()).collect(Collectors.toSet());
        Assert.assertEquals(collect2.size(), 2);
    }

    //================================================================

    private boolean isMatchString(Predicate<String> predicate, String text) {
        return predicate.test(text);
    }

    @Test
    public void testPredicate() {
        Assert.assertTrue(isMatchString(s -> s.startsWith("Р"), "Рыльков"));
        Assert.assertTrue(isMatchString(s -> s.contains("ы"), "Рыльков"));
        Assert.assertFalse(isMatchString(s -> s.endsWith("л"), "Рыльков"));
        Assert.assertTrue(isMatchString(s -> s.length() > 5, "Рыльков"));
    }

    //================================================================

    private String not1() {
        return "not 1";
    }

    @Test
    public void testOptional() {
        Person person = new Person("Вячеслав", "Рыльков", 15, Arrays.asList("Учеба", "Спорт"));

        Person parent = new Person("Александр", "Рыльков", 50, Arrays.asList("Работа", "Спорт"));
        person.setParent(parent);

        String str = Optional.ofNullable(person)
                .map(p -> p.getParent())
                .map(p -> "Возраст родителя " + p.getAge())
                .orElse("пусто");
        System.out.println(str);


    }

}
