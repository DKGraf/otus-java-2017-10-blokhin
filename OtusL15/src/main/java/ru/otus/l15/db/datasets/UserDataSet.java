package ru.otus.l15.db.datasets;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PhoneDataSet> phones;

    public UserDataSet() {
    }

    public UserDataSet(String name, int age, AddressDataSet address, List<PhoneDataSet> phones) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private AddressDataSet getAddressDataSet() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name = '" + name + '\'' +
                ", age = " + age +
                ", address = '" + getAddressDataSet().getAddress() +
                "', phones =" + phoneNumbers() +
                '}';
    }

    private String phoneNumbers() {
        StringBuilder numbers = new StringBuilder();
        for (PhoneDataSet pds :
                phones) {
            numbers.append(", ")
                    .append(pds.getNumber());
        }
        numbers.deleteCharAt(0);
        return numbers.toString();
    }
}
