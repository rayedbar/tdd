package com.rayed.tdd.j2ee.web.controller.servlet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rayed
 * @since August 16, 2018
 */
public class DummyAuthenticationService implements AuthenticationService {

    private Map<String, String> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    @Override
    public boolean isValidLogin(String username, String password) {
        return users.containsKey(username) && password.equals(users.get(username));
    }
}
