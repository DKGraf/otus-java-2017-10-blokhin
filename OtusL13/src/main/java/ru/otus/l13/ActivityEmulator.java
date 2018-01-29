package ru.otus.l13;

import ru.otus.l13.base.DBServiceHibernateImpl;
import ru.otus.l13.base.datasets.AddressDataSet;
import ru.otus.l13.base.datasets.PhoneDataSet;
import ru.otus.l13.base.datasets.UserDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityEmulator {
    private final long EMULATION_DURATION = 120_000;
    private DBServiceHibernateImpl dbService;

    ActivityEmulator(DBServiceHibernateImpl dbService) {
        this.dbService = dbService;
    }

    public void emulate() throws InterruptedException {
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        int userCount = 0;
        while (System.currentTimeMillis() < startTime + EMULATION_DURATION) {
            dbService.save(createUser());
            userCount++;
            Thread.sleep(250);
            dbService.save(createUser());
            userCount++;
            Thread.sleep(125);
            dbService.load(random.nextInt(userCount));
            Thread.sleep(125);
        }
    }

    private UserDataSet createUser() {
        Random random = new Random();
        PhoneDataSet pds = new PhoneDataSet(String.valueOf(random.nextLong()));
        List<PhoneDataSet> list = new ArrayList<>();
        list.add(pds);
        AddressDataSet ads = new AddressDataSet(String.valueOf(random.nextInt(100_000) + " st."));
        String userName = String.valueOf("User" + String.valueOf(random.nextInt()));
        int userAge = random.nextInt(100);

        return new UserDataSet(userName, userAge, ads, list);
    }
}
