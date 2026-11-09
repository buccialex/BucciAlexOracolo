package buccialexoracoloserver;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bux
 */
public class Richiesta {

    private String comando;
    private String parametro;

    public Richiesta() {
    }

    public Richiesta(String comando, String parametro) {
        this.comando = comando;
        this.parametro = parametro;
    }

    public String getComando() {
        return comando;
    }

    public String getParametro() {
        return parametro;
    }

}
