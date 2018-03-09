package ru.otus.l16.frontend;

public class FrontendMain {
    public static void main(String[] args) {
        WebServer server = new WebServer(8090);
        server.run();
    }
}
