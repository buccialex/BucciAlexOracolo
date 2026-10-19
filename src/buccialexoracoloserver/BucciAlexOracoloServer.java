/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccialexoracoloserver;

/**
 *
 * @author bucci.alex
 */
public class BucciAlexOracoloServer {

    public static void main(String[] args) {
        try {
            new Server().avvia();
        } catch (Exception ex) {
            System.err.println("Impossibile avviare il server: " + ex.getMessage());
        }
    }
    
}
