/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccialexoracoloclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author bucci.alex
 */
public class BucciAlexOracoloClient {
    

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connesso a " + "localhost" + ":" + 5000);
            System.out.println("Scrivi HELP per i comandi oppure EXIT per uscire.");

            while (scanner.hasNextLine()) {
                String comando = scanner.nextLine().trim();
                if (comando.isEmpty()) {
                    continue;
                }
                if ("EXIT".equalsIgnoreCase(comando)) {
                    break;
                }

                out.writeUTF(comando);
                out.flush();
                System.out.println("Server: " + in.readUTF());
            }
        } catch (IOException ex) {
            System.err.println("Impossibile comunicare con il server: " + ex.getMessage());
        }
    }
    
}
