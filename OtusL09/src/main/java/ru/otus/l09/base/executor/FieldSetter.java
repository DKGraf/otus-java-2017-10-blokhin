package ru.otus.l09.base.executor;

import ru.otus.l09.base.datasets.AddressDataSet;
import ru.otus.l09.base.datasets.PhoneDataSet;
import ru.otus.l09.base.datasets.dao.AddressDataSetDAO;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

public class FieldSetter {

    private FieldSetter() {
    }

    public static Field setField(Field field, String value, Object obj) throws IllegalAccessException {
        String type = field.getType().getName().toLowerCase();
        switch (type) {
            case "boolean": {
                field.set(obj, Boolean.parseBoolean(value));
                break;
            }
            case "byte": {
                field.set(obj, Byte.parseByte(value));
                break;
            }
            case "short": {
                field.set(obj, Short.parseShort(value));
                break;
            }
            case "char": {
                char c = value.charAt(0);
                field.set(obj, c);
                break;
            }
            case "int": {
                field.set(obj, Integer.parseInt(value));
                break;
            }
            case "long": {
                field.set(obj, Long.parseLong(value));
                break;
            }
            case "float": {
                field.set(obj, Float.parseFloat(value));
                break;
            }
            case "double": {
                field.set(obj, Double.parseDouble(value));
                break;
            }
            case "java.lang.string": {
                field.set(obj, value);
                break;
            }
            case "ru.otus.l09.base.datasets.addressdataset": {
                try {
                    AddressDataSet address = new AddressDataSetDAO().load(Long.parseLong(value), AddressDataSet.class);
                    field.set(obj, address);
                    break;
                } catch (SQLException | ClassNotFoundException | InstantiationException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return field;
    }

    public static Field setOTM(Field field, List<PhoneDataSet> value, Object obj) throws IllegalAccessException {
        field.set(obj, value);
        return field;
    }
}
