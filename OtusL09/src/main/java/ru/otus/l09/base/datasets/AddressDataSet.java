package ru.otus.l09.base.datasets;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {

    @Column(name = "address")
    private String address;

    public AddressDataSet() {
    }

    public AddressDataSet(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "address='" + address + '\'' +
                '}';
    }
}
