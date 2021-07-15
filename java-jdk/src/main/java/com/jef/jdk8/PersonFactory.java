package com.jef.jdk8;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);

    static void main(String[] args) {
        PersonFactory personFactory = Person::new;
        Person person = personFactory.create("jef", "tu");
        System.out.println(person.firstName + person.lastName);
    }
}
