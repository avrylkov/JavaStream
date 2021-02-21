package demo.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {

   private String firstName;
   private String lastName;
   private Integer age;
   private List<String> hobbys;

    public Person(String firstName, String lastName, Integer age, List<String> hobbys) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hobbys = hobbys;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getHobbys() {
        return hobbys;
    }
}
