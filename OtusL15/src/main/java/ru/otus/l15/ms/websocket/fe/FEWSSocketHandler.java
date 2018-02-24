package ru.otus.l15.ms.websocket.fe;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class FEWSSocketHandler extends WebSocketHandler {

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.register(WSSocketFront.class);
    }
}
