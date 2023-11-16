package service;

import com.google.gson.Gson;
import core.Database;
import interfaces.PremiumInterface;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "interfaces.PremiumInterface")
public class PremiumService extends Database implements PremiumInterface {
    @Resource
    WebServiceContext wsContext;

    private boolean callbackToPHP(String creator_id, String status)  {
        try {
            String phpURL = System.getenv("PHP_URL_PREMIUM");
            URL url = new URL(phpURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            String json = "{\"creator_id\": " + creator_id + ", \"status\": \"" + status + "\"}";
            System.out.println(json);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return false;
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            log(wsContext, "Failed to callback to php");
            return false;
        }
    }

    @WebMethod
    public boolean newPremiumUser(@WebParam(name = "creator_id") int creator_id) {
        if (verifyAPIKey(wsContext)) {
            String query = "INSERT INTO premium (creator_id, status) VALUES (" + creator_id + ", 'PENDING')";
            try {
                int result = this.executeUpdate(query);
                if (result != 0) {
                    log(wsContext, "New premium user");
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return false;
            }
        } else {
            return false;
        }
    }
    @WebMethod
    public String premiumList() {
        if (verifyAPIKey(wsContext)) {
            System.out.println("here");
            String query = "SELECT * FROM premium WHERE status = 'PENDING'";
            try {
                ResultSet result = this.executeQuery(query);
                System.out.println("here");
                List<Map<String, Object>> data = getResFormat(result);
                if (data == null) {
                    System.out.println("here2");
                    return "[]";
                }
                System.out.println(result);
                System.out.println(data);
                if (data.size() > 0) {
                    System.out.println("here3");
                    log(wsContext, "Fetched premium users data");
                    return new Gson().toJson(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return null;
            }
        } else {
            return null;
        }
        return null;
    }
    @WebMethod
    public String checkPremiumUser(@WebParam(name = "creator_id") int creator_id) {
        String status = "";
        if (verifyAPIKey(wsContext)) {
            String query = "SELECT * FROM premium WHERE creator_id = " + creator_id;
            try {
                ResultSet result = this.executeQuery(query);
                if (result.next()) {
                    status = result.getString("status");
                    log(wsContext, "Check premium status");
                    return status;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return status;
            }
            return "";
        } else {
            return status;
        }
    }
    @WebMethod
    public boolean updatePremiumUser(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "status") String status) {
        if (verifyAPIKey(wsContext)) {
            System.out.println("CREATOR ID");
            System.out.println(creator_id);
            System.out.println("STATUS");
            System.out.println(status);
            String query = "UPDATE premium SET status ='" + status + "' WHERE creator_id = " + creator_id;
            try {
                int res = this.executeUpdate(query);
                if (res != 0) {
                    if (callbackToPHP(String.valueOf(creator_id), status)) {
                        log(wsContext, "Updated premium status");
                    }
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return false;
            }
        } else {
            return false;
        }
    }
    

}
