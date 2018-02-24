package ru.otus.l15.frontend;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;

public class FEMonitoringClient {
    private FEMonitoringClientSocket socket;

    public void start() {
        WebSocketClient wsClient = new WebSocketClient();
        try {
            socket = new FEMonitoringClientSocket();
            wsClient.start();
            String dest = "ws://localhost:9999";
            URI uri = new URI(dest);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            wsClient.connect(socket, uri, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FEMonitoringClientSocket getSocket() {
        return socket;
    }
}
