package ru.otus.l15.ms.websocket.db;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.otus.l15.ms.websocket.fe.WSSocketFront;

public class DBWSSocketHandler extends WebSocketHandler {

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(WSSocketDB.class);
    }
}
