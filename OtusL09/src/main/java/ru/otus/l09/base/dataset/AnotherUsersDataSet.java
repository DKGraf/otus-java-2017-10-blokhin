package ru.otus.l09.base.dataset;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Класс, не соответствующий по своей структуре,
 * имеющейся таблице в базе данных.
 */

@Entity
public class AnotherUsersDataSet extends DataSet {
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phoneNumber;

    public AnotherUsersDataSet() {
        super(0);
    }

    public AnotherUsersDataSet(long id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
        this.address = "Homeless";
        this.phoneNumber = "Has no phone";
    }

    public AnotherUsersDataSet(long id, String name, int age, String address, String phoneNumber) {
        super(id);
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AnotherUsersDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
