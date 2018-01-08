package ru.otus.l09.base.datasets.dao;

import ru.otus.l09.base.datasets.PhoneDataSet;
import ru.otus.l09.base.executor.FieldSetter;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.otus.l09.base.connection.ConnectionHelper.getConnection;

public class UserDataSetDAO extends BaseDAO {
    public UserDataSetDAO() {
        super.connection = getConnection();
    }

    public UserDataSetDAO(Connection connection) {
        super.connection = connection;
    }

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
            String columnName = entry.getValue();
            if (!columnName.endsWith("_otm")) {
                String value = userData.get(index);
                FieldSetter.setField(entry.getKey(), value, t);
                index++;
            } else {
                Field field = entry.getKey();
                Type type = field.getGenericType();
                String fullType = type.getTypeName();
                String typeName = fullType.substring(fullType.indexOf("<") + 1, fullType.length() - 1);
                Class aClass = Class.forName(typeName);
                Table tableOTM = (Table) aClass.getAnnotation(Table.class);
                String tableNameOTM = tableOTM.name();
                OneToMany otm = field.getAnnotation(javax.persistence.OneToMany.class);
                String idName = otm.mappedBy() + "_id";
                Field[] fieldsOTM = aClass.getDeclaredFields();
                Map<Field, String> fieldsAndColumnsOTM = getFieldsAndColumns(fieldsOTM);
                List<PhoneDataSet> list = getPhoneDataSetByID(tableNameOTM, id, fieldsAndColumnsOTM, idName);
                FieldSetter.setOTM(field, list, t);
            }
        }

        return t;
    }

    private List<PhoneDataSet> getPhoneDataSetByID(String table, long id, Map<Field, String> fieldsAndColumns, String idName) throws SQLException, IllegalAccessException {
        List<PhoneDataSet> resultList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        fieldsAndColumns.forEach((k, v) -> sb.append(", ")
                .append(v));
        sb.deleteCharAt(0);

        String sql = "select" + sb.toString() + " from " + table + " where " + idName + " = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();

        while (result.next()) {
            List<String> data = new ArrayList<>();
            String s = result.getString(1);
            data.add(s);
            PhoneDataSet pds = loadPhoneDataSet(fieldsAndColumns, data);
            resultList.add(pds);
        }
        return resultList;
    }

    private PhoneDataSet loadPhoneDataSet(Map<Field, String> fieldsAndColumns, List<String> data) throws IllegalAccessException {
        PhoneDataSet pds = new PhoneDataSet();
        int index = 0;
        for (Map.Entry<Field, String> entry :
                fieldsAndColumns.entrySet()) {
            String columnName = entry.getValue();
            if (!columnName.endsWith("_otm")) {
                String value = data.get(index);
                FieldSetter.setField(entry.getKey(), value, pds);
                index++;
            }
        }
        return pds;
    }

}
