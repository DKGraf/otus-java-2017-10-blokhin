package ru.otus.l01;

import com.google.common.math.LongMath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class GCD {
    private long first;
    private long second;
    private long gcd;

    void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите первое число типа long:");
            first = Long.parseLong(reader.readLine());
            System.out.println("Введите второе число типа long:");
            second = Long.parseLong(reader.readLine());
            gcd = LongMath.gcd(first, second);
            System.out.println("Наибольший общий делитель для введенных чисел - " + gcd);
        } catch (IOException e) {
            System.out.println("Что-то пошло не так.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка!");
            System.out.println("Введенные данные не являются числом типа long.");
        }
    }
}

