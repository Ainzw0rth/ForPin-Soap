package core;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    protected Connection connection;
    private String MYSQL_USERNAME = System.getenv("MYSQL_USER");
//    private String MYSQL_USERNAME = "root";

    private String MYSQL_PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD");
//    private String MYSQL_PASSWORD = "piglet123";
    private String MYSQL_URL = "jdbc:mysql://mysqldb:3306/forpin-soap";
//    private String MYSQL_URL = "jdbc:mysql://localhost:3306/forpin_soap";

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
            String APIKey = exchange.getRequestHeaders().getFirst("Authorization");
            String RESTKey = System.getenv("SOAP_REST");
            String appKey = System.getenv("SOAP_PHP");
            if (APIKey.equals(RESTKey) || APIKey.equals(appKey)) {
                status = true;
                System.out.println("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return status;
    }

    public void insertLog(String description, String IP, String endpoint) {
        try (Connection connection = this.getConnection()) {
            String query = "INSERT INTO log (description, IP, endpoint) VALUES ('" + description + "', '" + IP + "', '" + endpoint + "')";
            System.out.println("QUERY");
            System.out.println(query);
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

    public List<Map<String, Object>> getResFormat(ResultSet result) throws SQLException {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        if (result != null) {
            if (!result.next()) {
                return null;
            } else {
                ResultSetMetaData meta = result.getMetaData();
                int columns = meta.getColumnCount();
                 do {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= columns; ++i) {
                        String columnName = meta.getColumnName(i);
                        Object val = result.getObject(i);
                        row.put(columnName, val);
                    }
                    dataList.add(row);
                } while (result.next());
            }
        }
        return dataList;
    }
}
