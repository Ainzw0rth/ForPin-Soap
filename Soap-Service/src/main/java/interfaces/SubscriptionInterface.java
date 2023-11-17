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
    public String notYetSubscribedUserList(@WebParam(name = "subscriber_username", targetNamespace = "http://interfaces/") String subscriber_username);
    @WebMethod
    public boolean newSubscription(@WebParam(name = "creator_username", targetNamespace = "http://interfaces/") String creator_username, @WebParam(name = "subscriber_username", targetNamespace = "http://interfaces/") String subscriber_username);
    @WebMethod
    public String checkSubscription(@WebParam(name = "creator_username", targetNamespace = "http://interfaces/") String creator_username, @WebParam(name = "subscriber_username", targetNamespace = "http://interfaces/") String subscriber_username);
    @WebMethod
    public boolean updateSubscription(@WebParam(name = "creator_username", targetNamespace = "http://interfaces/") String creator_username, @WebParam(name = "subscriber_username", targetNamespace = "http://interfaces/") String subscriber_username, @WebParam(name = "status", targetNamespace = "http://interfaces/") String status);

}
