/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package buccialexoracoloserver;



/**
 *
 * @author bucci.alex
 */
public interface IGestoreJson {
    
    
    void creaJsonRisposte(String percorsoFile);
    void caricaRisposte(String percorsoFile);
    String getRisposta(String personalita);
    
}
