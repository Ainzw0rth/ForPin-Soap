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

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void query(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    public void bind(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        statement.executeUpdate();
        statement.close();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    public ResultSet single(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setMaxRows(1);
        return statement.executeQuery();
    }
}
