package ru.otus.l09.base;

import org.junit.jupiter.api.Test;
import ru.otus.l09.base.datasets.AddressDataSet;
import ru.otus.l09.base.datasets.PhoneDataSet;
import ru.otus.l09.base.datasets.UserDataSet;
import ru.otus.l09.base.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

class DBServiceImplTest {

    @Test
    void dbServiceTest() {
        try (Connection connection = getConnection()) {
            Executor exec = new Executor(connection);
            exec.execUpdate("create table if not exists addresses (" +
                    "id bigserial not null primary key, " +
                    "address varchar);");
            exec.execUpdate("create table if not exists users (" +
                    "id bigserial not null primary key, " +
                    "name varchar, " +
                    "age integer, " +
                    "address_id integer references addresses (id));");
            exec.execUpdate("create table if not exists phones (" +
                    "id bigserial not null primary key, " +
                    "phonenumber varchar, " +
                    "user_id integer references users (id));");
            List<PhoneDataSet> user1Phones = new ArrayList<>();
            List<PhoneDataSet> user2Phones = new ArrayList<>();
            PhoneDataSet phone1 = new PhoneDataSet("+7-985-678-94-52");
            PhoneDataSet phone2 = new PhoneDataSet("+7-945-688-91-26");
            PhoneDataSet phone3 = new PhoneDataSet("+7-909-758-81-29");
            user1Phones.add(phone1);
            user2Phones.add(phone2);
            user2Phones.add(phone3);
            UserDataSet user1 = new UserDataSet("Someone1", 32, new AddressDataSet("Some st."), user1Phones);
            UserDataSet user2 = new UserDataSet("Someone2", 64, new AddressDataSet("Another st."), user2Phones);
            phone1.setUser(user1);
            phone2.setUser(user2);
            phone3.setUser(user2);
            DBServiceImpl dbs = new DBServiceImpl();
            dbs.save(user1);
            dbs.save(user2);
            UserDataSet user3 = dbs.load(1, UserDataSet.class);
            UserDataSet user4 = dbs.load(2, UserDataSet.class);

            assertEquals(user1.getName(), user3.getName());
            assertEquals(user1.getAge(), user3.getAge());
            assertEquals(user1.getAddress().getAddress(), user3.getAddress().getAddress());
            assertEquals(user2.getName(), user4.getName());
            assertEquals(user2.getAge(), user4.getAge());
            assertEquals(user2.getAddress().getAddress(), user4.getAddress().getAddress());

//            exec.execUpdate("drop table if exists users cascade");
//            exec.execUpdate("drop table if exists addresses cascade");
//            exec.execUpdate("drop table if exists phones cascade");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}