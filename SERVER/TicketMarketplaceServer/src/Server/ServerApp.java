/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Server;

import Logic.Service;

/**
 *
 * @author joshu
 */
public class ServerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            System.out.println("STARTING SERVER");
            Service server = new Service();
        }
        catch(Exception ex){
            System.out.println("SERVER ERROR: " + ex);
        }
    }
    
}
