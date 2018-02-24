package ru.otus.l15.frontend;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebSocket
public class FEMonitoringClientSocket {
    private Session session;
    private Gson gson;
    private Map<String, Object> pageVariables;

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;
        gson = new Gson();
        sendMessage();
    }

    @OnWebSocketMessage
    @SuppressWarnings("unchecked")
    public void onText(String message) {
        pageVariables = gson.fromJson(message, HashMap.class);
    }

    public void sendMessage() {
        try {
            session.getRemote().sendString("getCache");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getPageVariables() {
        return pageVariables;
    }
}
