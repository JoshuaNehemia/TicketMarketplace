package ConsoleApp;

import FormUI.FormLogin;
import FormUI.FormSellerRegister;

public class SellerApp {

    public static void main(String[] args) {
        // Jalankan form di Event Dispatch Thread (best practice)
        java.awt.EventQueue.invokeLater(() -> {
            new FormLogin().setVisible(true);
        });
    }
}
