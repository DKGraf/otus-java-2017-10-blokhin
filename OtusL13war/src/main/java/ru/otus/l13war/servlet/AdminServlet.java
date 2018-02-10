package ru.otus.l13war.servlet;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.l13war.Client;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "password";

    @Autowired
    private Client client;

    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        if (user.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD)) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("password", password);
            setResponseOK(response);
        } else {
            setResponseError(response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = (String) request.getSession().getAttribute("user");
        String password = (String) request.getSession().getAttribute("password");

        if (ADMIN_LOGIN.equals(user) && ADMIN_PASSWORD.equals(password)) {
            setResponseOK(response);
        } else {
            setResponseUnauthorized(response);
        }
    }

    private void setResponseError(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(ru.otus.l13war.servlet.TemplateProcessor.instance().getSimplePage("login.html"));
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @SuppressWarnings("unchecked")
    private void setResponseOK(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Map<String, Object> pageVariables = gson.fromJson(client.getCacheStateJSON(), HashMap.class);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(TemplateProcessor.instance().getPage("monitoring.html", pageVariables));
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void setResponseUnauthorized(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(TemplateProcessor.instance().getSimplePage("unauthorized.html"));
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
