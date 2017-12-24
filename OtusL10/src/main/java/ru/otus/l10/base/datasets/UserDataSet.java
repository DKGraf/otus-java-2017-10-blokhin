package ru.otus.l10.base.datasets;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressDataSet address;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private PhoneDataSet phone;

    public UserDataSet() {
    }

    public UserDataSet(String name, int age/*, AddressDataSet address, PhoneDataSet phone*/) {
        this.name = name;
        this.age = age;
//        this.address = address;
//        this.phone = phone;
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

//    public AddressDataSet getAddress() {
//        return address;
//    }
//
//    public void setAddress(AddressDataSet address) {
//        this.address = address;
//    }
//
//    public PhoneDataSet getPhone() {
//        return phone;
//    }
//
//    public void setPhone(PhoneDataSet phone) {
//        this.phone = phone;
//    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age;/* +
                ", address=" + address +
                ", phone=" + phone +
                '}';*/
    }
}
