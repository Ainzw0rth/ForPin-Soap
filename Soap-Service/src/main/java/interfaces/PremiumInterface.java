package interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface PremiumInterface {
    @WebMethod
    public String premiumList();
    @WebMethod
    public boolean newPremiumUser(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id);
    @WebMethod
    public String checkPremiumUser(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id);
    @WebMethod
    public boolean updatePremiumUser(@WebParam(name = "creator_id", targetNamespace = "http://interfaces/") int creator_id, @WebParam(name = "status", targetNamespace = "http://interfaces/") String status);
}
