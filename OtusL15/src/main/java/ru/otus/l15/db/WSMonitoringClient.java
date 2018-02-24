package ru.otus.l15.db;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import ru.otus.l15.db.monitoring.Monitoring;

import java.net.URI;

public class WSMonitoringClient {
    private Monitoring monitoring;

    WSMonitoringClient(Monitoring monitoring) {
        this.monitoring = monitoring;
    }

    public void start() {
        WebSocketClient wsClient = new WebSocketClient();
        try {
            WSMonitoringClientSocket socket = new WSMonitoringClientSocket(monitoring);
            wsClient.start();
            String dest = "ws://localhost:9998";
            URI uri = new URI(dest);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            wsClient.connect(socket, uri, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
