package ru.otus.l15.db.datasets;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet {

    @Column(name = "phonenumber")
    private String number;

    @ManyToOne
    private UserDataSet user;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneDataSet)) return false;
        PhoneDataSet that = (PhoneDataSet) o;
        return Objects.equals(getNumber(), that.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }
}
