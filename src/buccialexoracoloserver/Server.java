/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexoracoloserver;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bucci.alex
 */
public class Server {

    private int porta;
    private ServerSocket serverSocket;
    private boolean attivo;
    private IGestoreJson gestoreRisposte;

    public Server() {
        this.porta = 5000;
        this.gestoreRisposte = new GestoreFile();
    }

    public void avvia() {
        try {
            String percorsoJson = "BucciAlexOracoloClient/risposte.json";
            File fileJson = new File(percorsoJson);

            // Genera il file JSON con tutte le risposte se non esiste ancora
            if (!fileJson.exists()) {
                gestoreRisposte.creaJsonRisposte(percorsoJson);
            }

            // Carica le risposte in memoria
            gestoreRisposte.caricaRisposte(percorsoJson);

            serverSocket = new ServerSocket(porta);
            attivo = true;
            System.out.println("Server avviato sulla porta " + porta);

            while (attivo) {
                Socket clientSocket = serverSocket.accept();
                GestoreClient gestore = new GestoreClient(clientSocket, this, (GestoreFile) gestoreRisposte);
                new Thread(gestore, "gestore-client-" + clientSocket.getPort()).start();
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
