package ru.otus.l16.frontend;

public class FrontendMain {
    public static void main(String[] args) {
        int param = Integer.parseInt(args[0]);
        WebServer server = new WebServer(param);
        server.run();
    }
}
