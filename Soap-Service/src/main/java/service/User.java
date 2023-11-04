package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class User {
    @WebMethod
    public double add(Double a, Double b) {
        return a + b;
    }
}
