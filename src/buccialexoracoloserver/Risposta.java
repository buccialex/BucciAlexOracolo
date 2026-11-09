/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexoracoloserver;

/**
 *
 * @author Bux
 */
public class Risposta {

    public static final String TIPO_ORACOLO = "ORACOLO";
    public static final String TIPO_ERRORE = "ERRORE";

    private String tipo;
    private String messaggio;

    public Risposta() {
    }

    public Risposta(String tipo, String messaggio) {
        this.tipo = tipo;
        this.messaggio = messaggio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMessaggio() {
        return messaggio;
    }

}
