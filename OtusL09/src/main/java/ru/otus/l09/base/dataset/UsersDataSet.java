package ru.otus.l09.base.dataset;

public class UsersDataSet extends DataSet {
    private final String name;
    private final int age;

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

    public long getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
