/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import Multithreading.MultithreadedSocket;
import Protocol.Comm.Communication;

/**
 *
 * @author joshu
 */
public class Service {
    
    //FIELD
    private int serverPort = 1234;
    
    //CONSTRUCTOR
    public Service() throws Exception{
        this.InitiateServer(serverPort);
    }
    
    //GETTER AND SETTER
    
    
    //MAIN FUNCTION
    public void InitiateServer(int serverPort) throws Exception{
        MultithreadedSocket socket = new MultithreadedSocket(serverPort);
        socket.start();
        System.out.println("SERVER STARTED");
    }
    
    
    ////LOGIC FUNCTION
    
    
    
}
