package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.datasets.PhoneDataSet;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class PhoneDataSenDAO extends BaseDAO {
    public PhoneDataSenDAO() {
        super.connection = getConnection();
    }

    public PhoneDataSenDAO(Connection connection) {
        super.connection = connection;
    }

    @Override
    public <T> T load(long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(PhoneDataSet.class.getCanonicalName()).newInstance();
        List<String> columnsList = new ArrayList<>();
        List<String> phoneData;
        List<Field> annotatedFields = new ArrayList<>();
        Table tableName = PhoneDataSet.class.getAnnotation(javax.persistence.Table.class);
        String table = tableName.name();


        return null;
    }
}
