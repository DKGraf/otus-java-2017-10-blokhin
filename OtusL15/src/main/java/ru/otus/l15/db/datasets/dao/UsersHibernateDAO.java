package ru.otus.l15.db.datasets.dao;

import org.hibernate.Session;
import ru.otus.l15.db.datasets.UserDataSet;

public class UsersHibernateDAO {
    private final Session session;

    public UsersHibernateDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet user) {
        session.save(user);
    }

    public UserDataSet load(long id) {
        return session.get(UserDataSet.class, id);
    }
}
