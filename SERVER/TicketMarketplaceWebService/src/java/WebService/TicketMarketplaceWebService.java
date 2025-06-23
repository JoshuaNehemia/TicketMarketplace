/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package WebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author joshu
 */
@WebService(serviceName = "TicketMarketplaceWebService")
public class TicketMarketplaceWebService {

    @WebMethod(operationName = "ConnectionTest")
    public String ConnectionTest() {
        return "CONNECTION SUCCESFULL!";
    }
    
    
    //USER (BUYER)
    @WebMethod(operationName = "UserLogIn")
    public String UserLogIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return "CONNECTION SUCCESFULL!";
    }
}
