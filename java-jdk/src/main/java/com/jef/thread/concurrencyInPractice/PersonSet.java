package com.jef.thread.concurrencyInPractice;

import com.jef.entity.Person;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * 人类集合
 * @author Jef
 * @date 2020/6/18 0018
 */
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return mySet.contains(person);
    }


}