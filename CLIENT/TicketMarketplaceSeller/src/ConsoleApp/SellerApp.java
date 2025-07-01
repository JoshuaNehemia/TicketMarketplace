package ConsoleApp;

import FormUI.FormLogin;
import FormUI.FormSellerRegister;
import Service.TCP;

public class SellerApp {

    public static TCP tcpservice;

    public static void main(String[] args) {

        int serverport = 1234;
        try {
            tcpservice = new TCP(serverport);
            tcpservice.start();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        // Jalankan form di Event Dispatch Thread (best practice)
        java.awt.EventQueue.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}
