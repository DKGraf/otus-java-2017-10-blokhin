package ru.otus.l09.base.dataset;


import javax.persistence.*;

@MappedSuperclass
public abstract class DataSet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}