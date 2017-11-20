package ru.otus.l05.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Тестовый пакет.
 * Если в конструктор передать имя пакета, то будет произведен поиск
 * методов для теста по всем классам, входящим в данный пакет.
 * Если в конструктор передать имя класса, то будет выполнен поиск
 * методов для тестирования только в этом классе.
 */

public class Tester {
    private String name;

    public Tester(String name) {
        this.name = name;
    }

    public void run() {
        Set<Method> setOfMethodsForTest = AnnotationsFinder.findMethodsForTest(name);
        int numOfTests = 0;
        int numOfSuccess = 0;

        for (Method method :
                setOfMethodsForTest) {
            numOfTests++;
            System.out.println("Running test " + numOfTests + " of " + setOfMethodsForTest.size());
            System.out.println("Current class for test: " + method.getDeclaringClass().getName());
            System.out.println("Current method for test: " + method.getName());
            if (test(method)) {
                numOfSuccess++;
                System.out.println("Test " + numOfTests + " passed.\n");
            } else {
                System.out.println("Test " + numOfTests + " failed.\n");
            }
        }

        if (numOfTests == numOfSuccess) {
            System.out.println("All tests pass.");
        } else {
            System.out.println("Tests failed.");
            System.out.println(numOfSuccess + " out of " + numOfTests + " passed.");
        }
    }

    private boolean test(Method method) {
        try {
            Class<?> clazz = Class.forName(method.getDeclaringClass().getName());
            Object obj = clazz.newInstance();
            Method beforeMethod = AnnotationsFinder.findMethodWithBefore(method.getDeclaringClass());
            Method afterMethod = AnnotationsFinder.findMethodWithAfter(method.getDeclaringClass());

            if (beforeMethod != null) {
                invoke(beforeMethod, obj);
            }
            invoke(method, obj);
            if (afterMethod != null) {
                invoke(afterMethod, obj);
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void invoke(Method method, Object obj) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(obj);
    }

}
