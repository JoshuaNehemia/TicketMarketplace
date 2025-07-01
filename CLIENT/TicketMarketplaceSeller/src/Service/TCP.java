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
import javax.swing.JOptionPane;
import tmwebservice.Ticket;

/**
 * SELLER TCP
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
        System.out.println("THREAD STARTED");
        try {
            while (true) {
                System.out.println("READY TO RECEIVE MESSAGE");
                Communication received = this.ReceivingMessage();
                this.Runnable(received.getCommand(), received.getData());
            }
        } catch (Exception ex) {
            System.out.println("ERROR IN TCP MULTITHREAD: " + ex);
        }
    }

    private Communication ReceivingMessage() throws Exception {
        String message = incoming.readLine();
        System.out.println("RECEIVED: " + message);
        return new Communication(message);
    }

    private void SendingMessage(Communication comms) throws Exception {
        System.out.println("SENDING MESSAGE:\n" +comms.getMessage());
        this.sending.writeBytes(comms.getMessage() + "\n");
    }

    //RUNNABLE
    private void Runnable(String command, String[] data) {
        if (command.equals("NEWNOTIFICATION")) {
            this.HandlingRefund(data[0]);
        }
    }

    //FUNCTION
    public void RegisteringToServer(String username) throws Exception {
        String[] data = new String[1];
        data[0] = username;
        this.SendingMessage(new Communication("REGISTER", true, data));
    }

    public void SendingNotification(String username) throws Exception {
        //SEND PAKAI TCP
        String[] data = new String[1];
        data[0] = username;
        this.SendingMessage(new Communication("SENDNOTIFICATION", true, data));
    }

    private void HandlingRefund(String ticket_id) {
        System.out.println("HANDLING REFUND IN PROGRESS");
        Ticket tiket = getTicketById(ticket_id);
        System.out.println("TICKET RETRIEVED: " + tiket.getId());
        boolean dialogResult = false;
        String message = "Do you want to approve the refund of this ticket,\n"
                + "Id: " + tiket.getId() + "\n"
                + "Event : " + tiket.getEvent().getName() + "\n"
                + "Event Class: " + tiket.getEventClass() + "\n"
                + "Paid Time : " + tiket.getPaidTime();
        System.out.println("MESSAGE: " + message);
        //SHOW DIALOG BOX
        int response = JOptionPane.showConfirmDialog(
                null,
                message,
                "Refund Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        dialogResult = (response == JOptionPane.YES_OPTION);
        String[] data = new String[1];
        data[0] = tiket.getId();
        try {
            if (dialogResult) {
                approveRefundTicket(tiket);
                this.SendingMessage(new Communication("REFUNDRESPONSE", true, data));
            } else {
                notRefundTicket(tiket);
                this.SendingMessage(new Communication("REFUNDRESPONSE", false, data));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }

    }

    private static Ticket getTicketById(java.lang.String ticketId) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.getTicketById(ticketId);
    }

    private static int approveRefundTicket(tmwebservice.Ticket ticket) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.approveRefundTicket(ticket);
    }

    private static int notRefundTicket(tmwebservice.Ticket ticket) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.notRefundTicket(ticket);
    }

}
