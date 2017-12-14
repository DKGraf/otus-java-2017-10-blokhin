package ru.otus.l08.writer.checker;

public class TypeChecker {
    private TypeChecker() {
    }

    public static boolean isPrimitiveOrWrapper(Class<?> type) {
        return (type.isPrimitive() && type != void.class) ||
                type == Double.class || type == Float.class || type == Long.class ||
                type == Integer.class || type == Short.class || type == Character.class ||
                type == Byte.class || type == Boolean.class;
    }

    public static boolean isString(Class<?> type) {
        return type == String.class;
    }
}
