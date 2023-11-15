package interfaces;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface PremiumInterface {
    @WebMethod
    public String premiumList();
    @WebMethod
    public boolean newPremiumUser(@WebParam(name = "creator_id") int creator_id);
    @WebMethod
    public String checkPremiumUser(@WebParam(name = "creator_id") int creator_id);
    @WebMethod
    public boolean updatePremiumUser(@WebParam(name = "creator_id") int creator_id, @WebParam(name = "status") String status);
}
