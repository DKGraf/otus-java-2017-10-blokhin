package ru.otus.l10.base;

import org.junit.jupiter.api.Test;
import ru.otus.l10.base.datasets.AddressDataSet;
import ru.otus.l10.base.datasets.PhoneDataSet;
import ru.otus.l10.base.datasets.UserDataSet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class DBServiceHibernateImplTest {

    @Test
    void dbServiceHibernateTest() {
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
        DBServiceHibernateImpl dbsh = new DBServiceHibernateImpl();
        dbsh.save(user1);
        dbsh.save(user2);
        UserDataSet user3 = dbsh.load(1, UserDataSet.class);
        UserDataSet user4 = dbsh.load(2, UserDataSet.class);

        assertEquals(user1.getName(), user3.getName());
        assertEquals(user1.getAge(), user3.getAge());
        assertEquals(user1.getAddress(), user3.getAddress());
        assertIterableEquals(user1.getPhones(), user3.getPhones());
        assertEquals(user2.getName(), user4.getName());
        assertEquals(user2.getAge(), user4.getAge());
        assertEquals(user2.getAddress(), user4.getAddress());
        assertIterableEquals(user2.getPhones(), user4.getPhones());
    }
}