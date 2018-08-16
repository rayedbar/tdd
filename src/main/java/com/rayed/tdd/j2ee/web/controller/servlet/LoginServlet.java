package com.rayed.tdd.j2ee.web.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rayed
 * @since August 14, 2018
 */
public class LoginServlet extends HttpServlet {

    protected AuthenticationService getAuthenticationService() {
        return null;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (getAuthenticationService().isValidLogin(username, password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/homepage");
        } else {
            response.sendRedirect("/invalidLogin");
        }
    }
}
