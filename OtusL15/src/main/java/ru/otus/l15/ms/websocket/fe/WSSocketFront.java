package ru.otus.l15.ms.websocket.fe;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.otus.l15.ms.Messages;
import ru.otus.l15.ms.Sessions;

@WebSocket
public class WSSocketFront {
    private Session session;

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        Sessions.getSessions().put("fe", session);
    }

    @OnWebSocketMessage
    public void onText(String message) {
        Messages.getFrontToMSmessages().add(message);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        Sessions.getSessions().remove("fe", session);
    }
}
