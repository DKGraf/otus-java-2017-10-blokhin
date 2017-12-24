package ru.otus.l09.executor;

import java.util.Map;

public class SqlCreator {
    private Map<String, String> columns;
    private String table;

    public SqlCreator(Map<String, String> columns, String table) {
        this.columns = columns;
        this.table = table;
    }

    public String createSQL() {
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();
        sbName.append("(");
        sbValue.append("(");

        columns.forEach((k, v) -> {
                    sbName.append("")
                            .append(k)
                            .append(", ");
                    sbValue.append("'")
                            .append(v)
                            .append("', ");
                }
        );

        sbName.delete(sbName.length() - 2, sbName.length());
        sbValue.delete(sbValue.length() - 2, sbValue.length());
        sbName.append(")");
        sbValue.append(")");

        return "insert into " + table + " " + sbName.toString() +
                " values " + sbValue.toString();
    }
}
