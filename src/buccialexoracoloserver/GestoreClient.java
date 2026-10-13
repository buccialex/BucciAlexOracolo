/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexoracoloserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author bucci.alex
 */
public class GestoreClient implements Runnable{

    private Socket clientSocket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean attivo = true;
    
    public GestoreClient(Socket clientSocket, Server server) {
        
    }
    
    public synchronized void chiudiConnessione() {
        
    }
    
    @Override
    public void run() {
        
    }
    
}
