package service;

import jakarta.xml.ws.Endpoint;

public class Main {
    private static final String URL = "http://0.0.0.0:1234/user";

    public static void main(String[] args) {
        try {
            System.out.println("Service is published at: " + URL);
            Endpoint.publish(URL, new User());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
