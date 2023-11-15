package service;

import core.Database;
import interfaces.SubscriptionInterface;
import com.google.gson.Gson;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "interfaces.SubscriptionInterface")
public class SubscriptionService extends Database implements SubscriptionInterface {
    @Resource
    WebServiceContext wsContext;
    @WebMethod
    public String subscriptionList() {
        if (verifyAPIKey(wsContext)) {
            String query = "SELECT * FROM subscription WHERE status = 'PENDING'";
            try {
                ResultSet result = this.executeQuery(query);
                List<Map<String, Object>> data = getResFormat(result);
                if (data == null) {
                    return "[]";
                } else if (data.size() > 0) {
                    log(wsContext, "Fetched subscription data");
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
    public boolean newSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id) {
        if (verifyAPIKey(wsContext)) {
            String query = "INSERT INTO subscription (creator_id, subscriber_id, status) VALUES (" + creator_id + ", " + subscriber_id + ", 'PENDING')";
            try {
                int result = this.executeUpdate(query);
                if (result != 0) {
                    this.log(wsContext, "Added new subscription");
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
    public String checkSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id) {
        String status = "";
        if (verifyAPIKey(wsContext)) {
            String query = "SELECT * FROM subscription WHERE creator_id = " + creator_id + " AND subscriber_id = " + subscriber_id;
            try {
                ResultSet result = this.executeQuery(query);
                if (result.next()) {
                    status = result.getString("status");
                    log(wsContext, "Check subscription status");
                    return status;
                }
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return status;
            }
        } else {
            return status;
        }
    }
    @WebMethod
    public boolean updateSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id, @WebParam(name = "status") String status) {
        if (verifyAPIKey(wsContext)) {
            String query = "UPDATE subscription SET status ='" + status + "' WHERE creator_id = " + creator_id + "AND subscriber_id = " + subscriber_id;
            try {
                int res = this.executeUpdate(query);
                if (res != 0) {
                    log(wsContext, "Updated subscription status");
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
