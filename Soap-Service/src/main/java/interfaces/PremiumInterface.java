package interfaces;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface PremiumInterface {
    @WebMethod
    public String newPremiumUser(@WebParam(name = "creator_id") int creator_id);
    @WebMethod
    public String checkPremiumUser(@WebParam(name = "creator_id") int creator_id);
    @WebMethod
    public String updatePremiumUser(@WebParam(name = "creator_id") int creator_id);
    @WebMethod
    public String deletePremiumUser(@WebParam(name = "creator_id") int creator_id);
}
