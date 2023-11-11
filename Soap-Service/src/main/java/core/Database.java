package core;

import com.sun.net.httpserver.HttpPrincipal;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.spi.http.HttpExchange;
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

    public boolean verifyAPIKey(WebServiceContext wsContext) {
        boolean status = false;
        try  {
            MessageContext msgContext =  wsContext.getMessageContext();
            HttpExchange exchange = (HttpExchange) msgContext.get("com.sun.xml.ws.http.exchange");
//            String APIKey = exchange.getRequestHeaders().get();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return status;
    }

    public void insertLog(String description, String IP, String endpoint) {
        try (Connection connection = this.getConnection()) {
            String query = "INSERT INTO LOG (description, IP, endpoint) VALUES (" + description + ", " + IP + ", " + endpoint + ")";
            this.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void log(WebServiceContext wsContext, String description) {
        MessageContext msgContext = wsContext.getMessageContext();
        HttpExchange httpExchange = (HttpExchange) msgContext.get("com.sun.xml.ws.http.exchange");
        String ip = httpExchange.getRemoteAddress().getAddress().getHostAddress();
        String endpoint = httpExchange.getRequestURI().toString();
        insertLog(description, ip, endpoint);
    }
}
