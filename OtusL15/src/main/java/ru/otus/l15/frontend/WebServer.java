package ru.otus.l15.frontend;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import ru.otus.l15.frontend.servlet.AdminServlet;

public class WebServer {
    private final static int PORT = 8090;
    private final static String RESOURCES_LOCATION = "/web/";
    private final Server server;
    private FEMonitoringClient monitoringClient;

    public WebServer() {
        ResourceHandler resourceHandler = new ResourceHandler();
        Resource resource = Resource.newClassPathResource(RESOURCES_LOCATION);
        resourceHandler.setBaseResource(resource);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        monitoringClient = new FEMonitoringClient();
        context.addServlet(new ServletHolder(new AdminServlet(monitoringClient)), "/admin");

        server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

    }

    public void run() {
        try {
            server.start();
            monitoringClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
