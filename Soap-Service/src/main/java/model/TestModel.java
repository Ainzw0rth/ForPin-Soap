package model;

import core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestModel extends Database {
    public TestModel() {
        super();
    }

    public int fetchId() {
        int id = -1;  // Initialize to a default value or handle if needed
        String query = "SELECT id FROM log LIMIT 1"; // Fetch only one row's 'id'

        try (Connection connection = this.getConnection();
             ResultSet resultSet = this.single(query)) {
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
