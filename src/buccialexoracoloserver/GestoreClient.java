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
public class GestoreClient implements Runnable {

    private Socket clientSocket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private Protocollo protocollo;
    private boolean attivo = true;

    public GestoreClient(Socket clientSocket, Server server, GestoreFile gestoreRisposte) {
        try {
            this.clientSocket = clientSocket;
            this.server = server;
            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out = new DataOutputStream(clientSocket.getOutputStream());
            this.protocollo = new Protocollo(gestoreRisposte);
        } catch (IOException e) {

        }

    }

    public synchronized void chiudiConnessione() {
        attivo = false;
        try {
            clientSocket.close();
        } catch (IOException ex) {
            // La connessione e gia chiusa.
        }
    }

    @Override
    public void run() {
        try {
            while (attivo) {
                String messaggio = in.readUTF();
                String risposta = protocollo.interpreta(messaggio);
                if(risposta != null)
                    out.writeUTF(risposta);
                    out.flush();
                }
        } catch (IOException ex) {
            if (attivo) {
                System.err.println("Connessione client interrotta: " + ex.getMessage());
                chiudiConnessione();
            }
        }
    }

}
