package interfaces;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
@WebService
public interface SubscriptionInterface {
    @WebMethod
    public String newSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);
    @WebMethod
    public String checkSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);
    @WebMethod
    public String updateSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);

    @WebMethod
    public String deleteSubscription(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "subscriber_id") int subscriber_id);
}