package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "interfaces.PremiumInterface")
public class PremiumService {
    @WebMethod
    public boolean newPremiumUser(@WebParam(name = "creator_id") int creator_id) {

        return false;
    }
    @WebMethod
    public String checkPremiumUser(@WebParam(name = "creator_id") int creator_id) {
        return "";
    }
    @WebMethod
    public String updatePremiumUser(@WebParam(name = "creator_id") int creator_id) {
        return "";
    }
    @WebMethod
    public String deletePremiumUser(@WebParam(name = "creator_id") int creator_id) {
        return "";
    }
}
