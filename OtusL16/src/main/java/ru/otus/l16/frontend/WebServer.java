package ru.otus.l16.frontend;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.otus.l16.frontend.servlet.AdminServlet;

public class WebServer {
    private final static int PORT = 8090;
    private final static String RESOURCES_LOCATION = "/web/";
    private final Server server;
    private final FEtoMSSocket socket;
//    private FEMonitoringClient monitoringClient;

    public WebServer() {
        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(RESOURCES_LOCATION);
        resourceHandler.setBaseResource(resource);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        socket = new FEtoMSSocket();
//        monitoringClient = new FEMonitoringClient();
        context.addServlet(new ServletHolder(new AdminServlet(socket)), "/admin");

        server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

    }

    public void run() {
        try {
            server.start();
//            monitoringClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
