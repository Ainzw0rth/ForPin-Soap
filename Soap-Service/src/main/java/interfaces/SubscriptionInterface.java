package interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface SubscriptionInterface {
    @WebMethod
    public boolean newSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);
    @WebMethod
    public String checkSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);
    @WebMethod
    public boolean updateSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id, @WebParam(name = "status") String status);

}
