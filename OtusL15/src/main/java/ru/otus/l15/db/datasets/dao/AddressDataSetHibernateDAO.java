package ru.otus.l15.db.datasets.dao;

import org.hibernate.Session;
import ru.otus.l15.db.datasets.AddressDataSet;

public class AddressDataSetHibernateDAO {
    private final Session session;

    public AddressDataSetHibernateDAO(Session session) {
        this.session = session;
    }

    public void save(AddressDataSet user) {
        session.save(user);
    }

    public AddressDataSet load(long id) {
        return session.get(AddressDataSet.class, id);
    }
}
