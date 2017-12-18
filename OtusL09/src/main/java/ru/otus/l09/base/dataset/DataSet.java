package ru.otus.l09.base.dataset;


public abstract class DataSet {
    private final long id;

    DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
