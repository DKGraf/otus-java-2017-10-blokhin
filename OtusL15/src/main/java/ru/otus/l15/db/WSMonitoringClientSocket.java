package ru.otus.l15.db;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import ru.otus.l15.db.monitoring.Monitoring;

import java.io.IOException;
import java.util.Map;

@WebSocket
public class WSMonitoringClientSocket {
    private Session session;
    private Monitoring monitoring;
    private Gson gson = new Gson();

    WSMonitoringClientSocket(Monitoring monitoring) {
        this.monitoring = monitoring;
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        this.session = session;

    }

    @OnWebSocketMessage
    public void onText(String message) {
        if ("getCache".equals(message)) {
            Map<String, Object> cacheState = monitoring.getCacheState();
            String state = gson.toJson(cacheState);
            if (session != null) {
                try {
                    session.getRemote().sendString(state);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
