package ru.otus.l05;

import ru.otus.l05.framework.Tester;

/**
 * Класс, содержащий метод main для запуска проверки фреймворка.
 * Возможные варианты запуска для существующих тестов:
 * "ru.otus.l05.tests" - для запуска тестов по пакету;
 * "ru.otus.l05.tests.ValidTest.class" - для запуска только класса с валидными данными;
 * "ru.otus.l05.tests.InvalidTest.class" - для запуска класса с невалидными данными.
 */

public class Main {
    public static void main(String[] args) {
        Tester tester = new Tester("ru.otus.l05.tests");
        tester.run();
    }
}
