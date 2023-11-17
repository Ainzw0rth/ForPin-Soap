package interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SubscriptionInterface {
    @WebMethod
    public String subscriptionList();
    @WebMethod
    public String notYetSubscribedUserList(@WebParam(name = "subscriber_id", targetNamespace = "http://interfaces/") int subscriber_id);
    @WebMethod
    public boolean newSubscription(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id, @WebParam(name = "subscriber_id", targetNamespace = "http://interfaces/") int subscriber_id);
    @WebMethod
    public String checkSubscription(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id, @WebParam(name = "subscriber_id", targetNamespace = "http://interfaces/") int subscriber_id);
    @WebMethod
    public boolean updateSubscription(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id, @WebParam(name = "subscriber_id", targetNamespace = "http://interfaces/") int subscriber_id, @WebParam(name = "status", targetNamespace = "http://interfaces/") String status);

}
