package ru.otus.l11.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.l11.base.cache.CacheElement;
import ru.otus.l11.base.cache.CacheEngine;
import ru.otus.l11.base.cache.CacheEngineImpl;
import ru.otus.l11.base.datasets.*;

public class DBServiceHibernateImpl implements DBService {
    private final SessionFactory sessionFactory;
    private final CacheEngine<Long, UserDataSet> cache;

    @SuppressWarnings("unchecked")
    DBServiceHibernateImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.username", "dkgraf");
        configuration.setProperty("hibernate.connection.password", "dkgraf");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/otusl10");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");

        sessionFactory = createSessionFactory(configuration);
        cache = new CacheEngineImpl(5, 5000);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry registry = builder.build();
        return configuration.buildSessionFactory(registry);
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            Long id = (Long) session.save(user);
            cache.put(new CacheElement<>(id, user));
        }
    }

    @Override
    public UserDataSet load(long id) {
        CacheElement element = cache.get(id);
        if (element != null) {
            return (UserDataSet) element.getValue();
        } else {
            UserDataSet user = sessionFactory.openSession().get(UserDataSet.class, id);
            cache.put(new CacheElement<>(id, user));
            return user;
        }
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public CacheEngine<Long, UserDataSet> getCache() {
        return cache;
    }
}
