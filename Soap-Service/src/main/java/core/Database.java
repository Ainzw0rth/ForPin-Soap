package core;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;
public class Database {
    protected Connection connection;
    private Dotenv dotenv = Dotenv.load();
    private String MYSQL_URL = dotenv.get("MYSQL_URL");
    private String MYSQL_USERNAME = dotenv.get("MYSQL_USERNAME");
    private String MYSQL_PASSWORD = dotenv.get("MYSQL_PASSWORD");

    public Database() {
        try {
            this.connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] Connecting to database");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
