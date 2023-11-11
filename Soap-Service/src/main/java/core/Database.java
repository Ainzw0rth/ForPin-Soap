package core;

import java.sql.*;

public class Database {
    protected Connection connection;
    private String MYSQL_USERNAME = System.getenv("MYSQL_USER");
    private String MYSQL_PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD");
    private String MYSQL_URL = "jdbc:mysql://mysqldb:3306/forpin-soap";
    public Database() {
        try {
            System.out.println(MYSQL_URL);
            System.out.println(MYSQL_USERNAME);
            System.out.println(MYSQL_PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] Connecting to database");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public int executeUpdate(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeUpdate();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
}
