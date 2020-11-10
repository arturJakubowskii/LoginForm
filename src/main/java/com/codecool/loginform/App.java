package com.codecool.loginform;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App
{
    public static void main( String[] args ) throws IOException {
        SimulatedDB simulatedDB = new SimulatedDB();
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/login", new LogIn(simulatedDB));
        server.createContext("/logout", new LogOut(simulatedDB));
        server.createContext("/static", new Static());
        server.setExecutor(null);
        server.start();
    }
}