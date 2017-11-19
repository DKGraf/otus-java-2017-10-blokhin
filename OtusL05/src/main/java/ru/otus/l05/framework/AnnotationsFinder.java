package ru.otus.l05.framework;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import ru.otus.l05.annotations.After;
import ru.otus.l05.annotations.Before;
import ru.otus.l05.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Данный класс содержит статические методы для поиска следующих аннотаций:
 * Test, Before, After.
 * Конструктор класса выполнен приватным, чтобы препятствовать инстанцированию
 * данного класса.
 */

class AnnotationsFinder {

    private AnnotationsFinder() {
    }

    static Set<Method> findMethodsForTest(String packageName) {
        Reflections reflections = new Reflections(packageName, new MethodAnnotationsScanner());
        return reflections.getMethodsAnnotatedWith(Test.class);
    }

    private static Method findMethodsWithAnnotations(Class<?> clazz, Class<? extends Annotation> annotation) {
        for (Method method :
                clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                return method;
            }
        }
        return null;
    }

    static Method findMethodWithBefore(Class<?> clazz) {
        return findMethodsWithAnnotations(clazz, Before.class);
    }

    static Method findMethodWithAfter(Class<?> clazz) {
        return findMethodsWithAnnotations(clazz, After.class);
    }
}
