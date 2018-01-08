package ru.otus.l09.base.executor;

import java.util.Map;

public class SqlCreator {
    private SqlCreator() {
    }

    public static String createSQL(Map<String, String> columns, String table) {
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();
        sbName.append("(");
        sbValue.append("(");

        for (Map.Entry<String, String> entry :
                columns.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!key.endsWith("_otm")) {
                sbName.append("")
                        .append(key)
                        .append(", ");
                sbValue.append("'")
                        .append(value)
                        .append("', ");
            }
        }

        sbName.delete(sbName.length() - 2, sbName.length());
        sbValue.delete(sbValue.length() - 2, sbValue.length());
        sbName.append(")");
        sbValue.append(")");

        return "insert into " + table + " " + sbName.toString() +
                " values " + sbValue.toString() + " returning id;";
    }

    public static String createSQLforOTM(Map<String, String> columns, String table, long id, String idName) {
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();
        sbName.append("(")
                .append(idName)
                .append(", ");
        sbValue.append("(")
                .append(id)
                .append(", ");

        for (Map.Entry<String, String> entry :
                columns.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!key.endsWith("_otm")) {
                sbName.append(key)
                        .append(", ");
                sbValue.append("'")
                        .append(value)
                        .append("', ");
            }
        }

        sbName.delete(sbName.length() - 2, sbName.length());
        sbValue.delete(sbValue.length() - 2, sbValue.length());
        sbName.append(")");
        sbValue.append(")");

        return "insert into " + table + " " + sbName.toString() +
                " values " + sbValue.toString() + " returning id;";
    }
}
