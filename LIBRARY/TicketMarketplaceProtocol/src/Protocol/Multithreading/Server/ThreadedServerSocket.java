/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Protocol.Multithreading.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class ThreadedServerSocket implements Runnable {
    //FIELD
    private ServerSocket serverSocket;
    private ArrayList<SocketHandler> clientsSocket;
    
    //CONSTRUCTOR
    public ThreadedServerSocket(int port) throws Exception{
        this.serverSocket = new ServerSocket(port);
    }
    
    
    //GETTER AND SETTER
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    //FUNCTION
    @Override
    public void run()
    {
        try{
            while(true){
                Socket clientSocket = this.getServerSocket().accept();
                
            }
        }
        catch(Exception ex){
            System.out.println("Error, exception thrown: " + ex);
        }
    }
    
    
}
