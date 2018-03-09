package ru.otus.l16.frontend.servlet;

import com.google.gson.Gson;
import ru.otus.l16.frontend.FEtoMSSocket;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "password";
    private FEtoMSSocket socket;
    private Gson gson = new Gson();

    public AdminServlet(FEtoMSSocket socket) {
        this.socket = socket;
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
        response.getWriter().println(TemplateProcessor.instance().getSimplePage("login.html"));
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @SuppressWarnings("unchecked")
    private void setResponseOK(HttpServletResponse response) throws IOException {
//        client.getSocket().sendMessage();
        Map<String, Object> pageVariables = gson.fromJson(socket.getCacheStateJSON(), HashMap.class);/*client.getSocket().getPageVariables();*/

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
