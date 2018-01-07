package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.datasets.UserDataSet;
import ru.otus.l09.base.executor.FieldSetter;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class UsersDAO extends BaseDAO {
    public UsersDAO() {
        super.connection = getConnection();
    }

    public UsersDAO(Connection connection) {
        super.connection = connection;
    }

    public <T> T load(long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(UserDataSet.class.getCanonicalName()).newInstance();
        List<String> userData;
        Table tableName = UserDataSet.class.getAnnotation(javax.persistence.Table.class);
        Field[] fields = UserDataSet.class.getDeclaredFields();
        String table = tableName.name();
        Map<Field, String> fieldsAndColumns = getFieldsAndColumns(fields);
        userData = getById(table, id, fieldsAndColumns);

        fieldsAndColumns.forEach((k, v) ->
        {
            try {
                FieldSetter.setField(k, v, t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return t;
    }

}
