package ru.otus.l09.base.dataset;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Класс, полностью соответствующий имеющейся таблице
 * в базе данных
 */

@Entity
public class UsersDataSet extends DataSet {
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public UsersDataSet() {
        super(0);
    }

    public UsersDataSet(long id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
