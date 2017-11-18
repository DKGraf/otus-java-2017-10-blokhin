package ru.otus.l05.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Tester {
    private String packageName;

    public Tester(String packageName) {
        this.packageName = packageName;
    }

    public void run() {
        Set<Method> setOfMethodsForTest = AnnotationsFinder.findMethodsForTest(packageName);
        int numOfTests = 0;
        int numOfSuccess = 0;

        for (Method method :
                setOfMethodsForTest) {
            numOfTests++;
            if (test(method)) {
                numOfSuccess++;
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
