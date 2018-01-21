package ru.otus.l12;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.l12.base.DBServiceHibernateImpl;
import ru.otus.l12.base.monitoring.Monitoring;
import ru.otus.l12.base.servlet.AdminServlet;

public class WebServer {
    private final static int PORT = 8090;
    private final static String RESOURCES = "web";
    private final Server server;

    WebServer(DBServiceHibernateImpl dbService) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(RESOURCES);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new AdminServlet(new Monitoring(dbService))), "/admin");

        server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
    }

    public void run() {
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
