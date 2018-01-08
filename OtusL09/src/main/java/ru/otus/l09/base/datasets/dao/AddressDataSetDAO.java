package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.datasets.AddressDataSet;
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
    public <T> T load(long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        @SuppressWarnings("unchecked") T t = (T) Class.forName(AddressDataSet.class.getCanonicalName()).newInstance();
        List<String> data;
        Table tableName = AddressDataSet.class.getAnnotation(javax.persistence.Table.class);
        Field[] fields = AddressDataSet.class.getDeclaredFields();
        String table = tableName.name();
        Map<Field, String> fieldsAndColumns = getFieldsAndColumns(fields);
        data = getById(table, id, fieldsAndColumns);

        int index = 0;
        for (Map.Entry<Field, String> entry:
                fieldsAndColumns.entrySet()) {
            String value = data.get(index);
            FieldSetter.setField(entry.getKey(), value, t);
            index++;
        }
        return t;
    }
}
