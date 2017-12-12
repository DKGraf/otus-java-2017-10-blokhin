package ru.otus.l08.writer.builder;

import ru.otus.l08.writer.checker.TypeChecker;

/**
 * Класс, используемый для построения и вывода итоговой JSON строки.
 */

public class JSONStringBuilder {
    private StringBuilder sb;

    public JSONStringBuilder() {
        sb = new StringBuilder();
        sb.append("{");
    }

    public void build(String key, String value, Class aClass) {
        sb.append("\"")
                .append(key)
                .append("\"")
                .append(":");
        if (TypeChecker.isString(aClass)) {
            sb.append("\"")
                    .append(value)
                    .append("\"")
                    .append(",");
        } else {
            sb.append(value)
                    .append(",");
        }
    }

    public String getJSONString() {
        sb.deleteCharAt(sb.length() - 1)
                .append("}");
        return sb.toString();
    }
}
