package model;

import core.Database;

import java.sql.Connection;

public class LogModel extends Database {
    public LogModel() {
        super();
    }

    public void insertLog(String description, String IP, String endpoint) {
        try (Connection connection = this.getConnection()) {
            String query = "INSERT INTO LOG (description, IP, endpoint) VALUES (" + description + ", " + IP + ", " + endpoint + ")";
            this.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
