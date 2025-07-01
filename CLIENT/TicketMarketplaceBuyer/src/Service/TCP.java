/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Protocol.Comm.Communication;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import tmwebservice.Notification;

/**
 *
 * @author joshu
 */
public class TCP extends Thread {

    private Socket socket;
    private BufferedReader incoming;
    private DataOutputStream sending;


    public TCP(int port) throws Exception {
        socket = new Socket("localhost", port);
        this.incoming = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.sending = new DataOutputStream(this.socket.getOutputStream());
    }

    //TCP MULTITHREAD
    @Override
    public void run() {
        try {
            while (true) {
                Communication received = this.ReceivingMessage();
                this.Runnable(received.getCommand(), received.getData());
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

    private Communication ReceivingMessage() throws Exception {
        String message = incoming.readLine();
        System.out.println("NEW MESSAGE: " + message);
        return new Communication(message);
    }

    public void SendingMessage(Communication comms) throws Exception {
        System.out.println("SENDING: " + comms.getMessage());
        this.sending.writeBytes(comms.getMessage() + "\n");
    }

    //RUNNABLE
    private void Runnable(String command, String[] data) {
        System.out.println("COMMAND: " + command);
        System.out.println("DATA: ");
        for (String s : data) {
            System.out.println(s);
        }
        if (command.equals("NEWNOTIFICATION")) {
            this.ReceivingNewNotification(data[0]);
        } else if (command.equals("NEWBROADCAST")) {
            this.ReceivingNewBroadcast(data[0]);
        }
    }

    //FUNCTION
    public void RegisteringToServer(String username) throws Exception {
        String[] data = new String[1];
        data[0] = username;
        this.SendingMessage(new Communication("REGISTER", true, data));
    }

    public void ReceivingNewNotification(String username) {
        String message = "New activities about your tickets, please check your notifications box";
        System.out.println("MESSAGE: " + message);
        //SHOW DIALOG BOX
        JOptionPane.showConfirmDialog(
                null,
                message,
                "NEW ACTIVTIY",
                JOptionPane.OK_OPTION
        );
    }

    public void ReceivingNewBroadcast(String broadcast) {
        //REQUEST KE WEBSERVICE

        //PERBARUI LIST WEBSERVICE
        //TUNJUKIN MESSAGE BOX (PESAN AJA - kasih title kalau itu broadcast)
    }
}
