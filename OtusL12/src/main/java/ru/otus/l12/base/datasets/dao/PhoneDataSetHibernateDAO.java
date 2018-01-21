package ru.otus.l12.base.datasets.dao;

import org.hibernate.Session;
import ru.otus.l12.base.datasets.PhoneDataSet;

public class PhoneDataSetHibernateDAO {
    private final Session session;

    public PhoneDataSetHibernateDAO(Session session) {
        this.session = session;
    }

    public void save(PhoneDataSet user) {
        session.save(user);
    }

    public PhoneDataSet load(long id) {
        return session.get(PhoneDataSet.class, id);
    }
}
