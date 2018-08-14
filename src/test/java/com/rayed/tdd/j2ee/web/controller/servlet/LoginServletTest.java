package com.rayed.tdd.j2ee.web.controller.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServlet;

/**
 * @author rayed
 * @since August 14, 2018
 */
public class LoginServletTest {

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        HttpServlet loginServlet = new LoginServlet();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/login");

        request.setParameter("username", "no_such_user");
        request.setParameter("password", "wrong_password");

        loginServlet.service(request, response);

        Assertions.assertEquals("/invalidLogin", response.getRedirectedUrl());
    }
}
