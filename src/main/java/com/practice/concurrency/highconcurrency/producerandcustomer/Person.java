package com.practice.concurrency.highconcurrency.producerandcustomer;

//评测题目: 无
import java.util.*;

/**
 * 需要的打印结果如下
 * before id ===   2  3  1
 * after  id ===   1  2  3
 */
class Person {

    /**
     * 编号
     */
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public static Person build(Integer id, String name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }
}

/**
 * 需要的打印结果如下
 * before id ===   2  3  1
 * after  id ===   1  2  3
 */
class PersonSort {

    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        peopleList.add(Person.build(2, "nihao"));
        peopleList.add(Person.build(3, "wohao"));
        peopleList.add(Person.build(1, "tahao"));

        // before print
        System.out.print(" before id === ");
        for (Person person : peopleList) {
            System.out.print("  " + person.getId());
        }
        System.out.println();
        // sort by id asc
        peopleList = sort(peopleList);
        // after print
        System.out.print(" after  id === ");
        for (Person person : peopleList) {
            System.out.print("  " + person.getId());
        }
        System.out.println();
    }


    private static List<Person> sort(List<Person> personList) {
        personList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getId()-o2.getId();
            }
        });
        return personList;
    }

}
