package com.codecool.loginform;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.codecool.loginform.helpers.CookieHelper;
import com.codecool.loginform.helpers.LoginHelper;

import java.io.IOException;


public class LogOut implements HttpHandler {

    private final CookieHelper cookieHelper;
    private final SimulatedDB simulatedDB;
    private final LoginHelper loginHelper;


    public LogOut(SimulatedDB simulatedDB) {
        this.simulatedDB = simulatedDB;
        this.cookieHelper = new CookieHelper();
        this.loginHelper = new LoginHelper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestURI = exchange.getRequestURI().toString();
        System.out.println(requestURI);
        String sessionId = exchange.getRequestHeaders().getFirst("Cookie")
                .replace("\"", "")
                .replace("sessionId=", "");
        int newSessionId = Integer.parseInt(sessionId);
        simulatedDB.getSessionUserMap().remove(newSessionId);
        System.out.println("sessionid removed");
        removeCookie(exchange);
        loginHelper.redirectToLogin(exchange);
    }

    private void removeCookie(HttpExchange exchange) {
        String cookie = exchange.getRequestHeaders().getFirst("Cookie") + ";Max-age=0";
        exchange.getResponseHeaders().set("Set-Cookie", cookie);
    }
}