package ru.otus.l10.base.datasets;

import org.hibernate.Session;

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
