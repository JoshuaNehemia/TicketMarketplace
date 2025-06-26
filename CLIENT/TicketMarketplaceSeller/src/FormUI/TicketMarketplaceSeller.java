/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FormUI;

import tmwebservice.Seller;

/**
 *
 * @author joshu
 */
public class TicketMarketplaceSeller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Seller baru = new Seller();
        baru.setCompanyAddress("jalan");
        baru.setCompanyName("XX1");
        baru.setEmail("email");
        baru.setFullName("nama lengkap");
        baru.setPassword("password");
        baru.setPhoneNumber("009099");
        baru.setUsername("admin1");
        sellerSignUp(baru);
        System.out.println("add berhasil");
    }

    private static int sellerSignUp(tmwebservice.Seller user) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.sellerSignUp(user);
    }
    
}
