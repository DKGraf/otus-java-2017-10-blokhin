package ru.otus.l16.ms;

/**
 * Параметр int, передаваемый в MessageSystem задает количество
 * фронтов и датабазных сервисов, которые будут запускаться.
 * Фронты будут запускаться на портах, начиная от 8090 включительно
 * с шагом в 1. Таким образом, при заданном значении параметра 2
 * будет запущено два фронта на портах 8090 и 8091 соотетственно.
 */
public class MessageSystemMain {
    public static void main(String[] args) {
        MessageSystem messageSystem = new MessageSystem(2);
        messageSystem.start();
    }
}
