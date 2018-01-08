package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.executor.FieldSetter;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class AddressDataSetDAO extends BaseDAO {
    public AddressDataSetDAO() {
        super.connection = getConnection();
    }

    public AddressDataSetDAO(Connection connection) {
        super.connection = connection;
    }

    @Override
    public <T> T load(long id, Class clazz) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(clazz.getCanonicalName()).newInstance();
        List<String> userData;
        Table tableName = (Table) clazz.getAnnotation(Table.class);
        Field[] fields = clazz.getDeclaredFields();
        String table = tableName.name();
        Map<Field, String> fieldsAndColumns = getFieldsAndColumns(fields);
        userData = getByID(table, id, fieldsAndColumns);

        int index = 0;
        for (Map.Entry<Field, String> entry :
                fieldsAndColumns.entrySet()) {
            String value = userData.get(index);
            FieldSetter.setField(entry.getKey(), value, t);
            index++;
        }

        return t;
    }
}
