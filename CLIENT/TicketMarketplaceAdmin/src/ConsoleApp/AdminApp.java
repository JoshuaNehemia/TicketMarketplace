/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ConsoleApp;

import FormUI.FormAdmin;

/**
 *
 * @author joshu
 */
public class AdminApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormAdmin().setVisible(true);
        });
    }
    
}
