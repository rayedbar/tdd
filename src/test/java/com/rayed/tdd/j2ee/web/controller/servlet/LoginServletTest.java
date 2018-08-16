package com.rayed.tdd.j2ee.web.controller.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author rayed
 * @since August 14, 2018
 */
public class LoginServletTest {

    private static final String REQ_PARAM_USERNAME = "username";
    private static final String REQ_PARAM_PASSWORD = "password";

    private static final String VALID_USERNAME = "username";
    private static final String VALID_PASSWORD = "password";

    private LoginServlet loginServlet;
    private DummyAuthenticationService dummyAuthenticationService;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        dummyAuthenticationService = new DummyAuthenticationService();
        dummyAuthenticationService.addUser(VALID_USERNAME, VALID_PASSWORD);

        loginServlet = new LoginServlet() {
            @Override
            protected AuthenticationService getAuthenticationService() {
                return dummyAuthenticationService;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        request.setParameter(REQ_PARAM_USERNAME, VALID_USERNAME);
        request.setParameter(REQ_PARAM_PASSWORD, "wrong_password");

        loginServlet.service(request, response);

        assertEquals("/invalidLogin", response.getRedirectedUrl());
    }

    @Test
    public void validLoginForwardsToHomePageAndStoresUsername() throws ServletException, IOException {
        request.setParameter(REQ_PARAM_USERNAME, VALID_USERNAME);
        request.setParameter(REQ_PARAM_PASSWORD, VALID_PASSWORD);

        loginServlet.service(request, response);

        assertEquals("/homepage", response.getRedirectedUrl());
        assertEquals(VALID_USERNAME, request.getSession().getAttribute(REQ_PARAM_USERNAME));
    }
}
