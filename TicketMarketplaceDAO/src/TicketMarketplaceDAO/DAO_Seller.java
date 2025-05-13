package TicketMarketplaceDAO;

import TicketMarketplaceEntities.Seller;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author joshu
 */
public class DAO_Seller {
        public static Seller Select_Seller(String username, String password, ArrayList<Seller> list) {
        Seller selectedUser = new Seller();

        for (Seller us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                selectedUser = us;
            }
        }

        return selectedUser;
    }

    public static ArrayList<Seller> Insert_Seller(Seller seller, ArrayList<Seller> list) {
        list.add(seller);
        
        return list;
    }

    public static ArrayList<Seller> Update_User(Seller seller, ArrayList<Seller> list) {
        Seller selectedUser = Select_Seller(seller.getUsername(), seller.getPassword(), list);
        
        list.remove(selectedUser);
        list.add(seller);
        
        return list;
    }

    public static ArrayList<Seller> Delete_User(String username, String password, ArrayList<Seller> list) {
        for (Seller us : list) {
            if (us.getUsername().equals(username) && us.getPassword().equals(password)) {
                list.remove(us);
            }
        }
        return list;
    }
}
