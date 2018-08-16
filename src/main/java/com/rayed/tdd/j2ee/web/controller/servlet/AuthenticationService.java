package com.rayed.tdd.j2ee.web.controller.servlet;

/**
 * @author rayed
 * @since August 16, 2018
 */
public interface AuthenticationService {

    boolean isValidLogin(String username, String password);
}
