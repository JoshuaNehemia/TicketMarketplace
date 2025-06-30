/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ConsoleApp;

import FormUI.FormAdmin;
import Service.TCP;

/**
 *
 * @author joshu
 */
public class AdminApp {

    public static TCP tcpservice;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int serverport = 1234;
        try {
            tcpservice = new TCP(serverport);
            //tcpservice.start();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FormAdmin().setVisible(true);
        });
    }

}
