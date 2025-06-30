/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entities.Message.Notification;
import Protocol.Comm.Communication;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class TCP extends Thread {

    private Socket socket;
    private BufferedReader incoming;
    private DataOutputStream sending;
    
    public ArrayList<Notification> notif;

    public TCP(int port) throws Exception {
        notif = new ArrayList<Notification>();
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
                this.Runnable(received.getCommand(),received.getData());
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }
    
    private Communication ReceivingMessage() throws Exception{
        String message = incoming.readLine();
        return new Communication(message);
    }
    
    
    private void SendingMessage(Communication comms) throws Exception{
        this.sending.writeBytes(comms.getMessage()+"\n");
    }
    
    //RUNNABLE
    private void Runnable(String command, String[] data){
        if(command.equals("NEWNOTIFICATIONS")){
            this.ReceivingNewNotification();
        }
        else if(command.equals("NEWBROADCAST")){
            this.ReceivingNewBroadcast(data[0]);
        }
    }
    
    //FUNCTION
    public void RegisteringToServer(String username)throws Exception{
        String[] data = new String[1];
        data[0] = username;
        this.SendingMessage(new Communication("REGISTER",true,data));
    }
    
    public void ReceivingNewNotification(){
            //REQUEST KE WEBSERVICE
            
            //PERBARUI LIST NOTIF
            
            //TUNJUKIN MESSAGE BOX (REQUEST BISA KLIK LANGSUNG BACA NOTIF - kalau nutut)
            
    }
    
    public void ReceivingNewBroadcast(String broadcast){
            //REQUEST KE WEBSERVICE
            
            //PERBARUI LIST WEBSERVICE
            
            //TUNJUKIN MESSAGE BOX (PESAN AJA - kasih title kalau itu broadcast)
    }

}
