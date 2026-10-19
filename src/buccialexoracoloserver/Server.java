/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexoracoloserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bucci.alex
 */
public class Server {

    private final int porta;
    private ServerSocket serverSocket;
    private volatile boolean attivo;
    private GestoreFile gestoreRisposte;

    public Server() {
        this.porta = 5000;
        this.gestoreRisposte = new GestoreFile();
    }

    public void avvia() {
        try{
        
            gestoreRisposte.caricaRisposte("risposte.txt");
        
            serverSocket = new ServerSocket(porta);
            attivo = true;
            System.out.println("Server avviato sulla porta " + porta);

        
                while (attivo) {
                    Socket clientSocket = serverSocket.accept();
                    try {
                        GestoreClient gestore = new GestoreClient(clientSocket, this, gestoreRisposte);
                        new Thread(gestore, "gestore-client-" + clientSocket.getPort()).start();
                    } catch (IOException ex) {
                        try {
                        clientSocket.close();
                        } catch (IOException closeException) {
                            System.err.println("Errore durante la chiusura del client: " + closeException.getMessage());
                        }
                        System.err.println("Errore durante la connessione del client: " + ex.getMessage());
                    }
                }
            } catch (IOException e) {
                ferma();
            }
    }

    public synchronized void ferma() {
        attivo = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore durante la chiusura del server: " + ex.getMessage());
            }
        }
    }

}
