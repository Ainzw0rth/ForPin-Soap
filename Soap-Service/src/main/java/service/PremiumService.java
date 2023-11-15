package service;

import com.google.gson.Gson;
import core.Database;
import interfaces.PremiumInterface;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "interfaces.PremiumInterface")
public class PremiumService extends Database implements PremiumInterface {
    @Resource
    WebServiceContext wsContext;

    @WebMethod
    public String premiumList() {
        if (verifyAPIKey(wsContext)) {
            String query = "SELECT * FROM premium WHERE status = 'PENDING'";
            try {
                ResultSet result = this.executeQuery(query);
                List<Map<String, Object>> data = getResFormat(result);
                if (data == null) {
                    return "[]";
                } else if (data.size() > 0) {
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
    }
    @WebMethod
    public boolean newPremiumUser(@WebParam(name = "creator_id") int creator_id) {
        if (verifyAPIKey(wsContext)) {
            String query = "INSERT INTO premium (creator_id, status) VALUES (" + creator_id + ", 'PENDING')";
            try {
                int result = this.executeUpdate(query);
                if (result != 0) {
                    this.log(wsContext, "New premium user");
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
            String query = "UPDATE premium SET status ='" + status + "' WHERE creator_id = " + creator_id;
            try {
                int res = this.executeUpdate(query);
                if (res != 0) {
                    log(wsContext, "Updated premium status");
//                    callback
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
