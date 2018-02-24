package ru.otus.l15.ms.websocket.db;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;


public class DBWSServer {
    public DBWSServer() {

    }

    public void start() {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9998);
        server.addConnector(connector);

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{new DBWSSocketHandler()});
        server.setHandler(handlerList);

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
