package buccialexoracoloserver;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bucci.alex
 */
public class Protocollo {

    private final String COMANDO_PERSONA = "PERSONA";
    private final String COMANDO_DOMANDA = "DOMANDA";
    private String domanda;
    private String personalita;
    private GestoreFile gestoreRisposte;
    private final Gson gson = new Gson();

    public Protocollo(GestoreFile gestoreRisposte) {
        this.domanda = "";
        this.personalita = "";
        this.gestoreRisposte = gestoreRisposte;
    }

    public String interpreta(String messaggio) {
        if (messaggio == null) {
            return "ERRORE_MESSAGGIO_NULLO";
        }
        
        Richiesta richiesta;
        try {
            richiesta = gson.fromJson(messaggio, Richiesta.class);
        } catch (JsonSyntaxException e) {
            return errore("ERRORE_JSON_NON_VALIDO");
        }
        if (richiesta == null || richiesta.getComando() == null) {
            return errore("ERRORE_COMANDO_NON_RICONOSCIUTO");
        }

        
        
        String tipoComando = richiesta.getComando().trim();
        String parametro = richiesta.getParametro() == null ? "" : richiesta.getParametro().trim();

        switch (tipoComando) {
            
            case COMANDO_PERSONA:
                if (parametro.isEmpty()) {
                    return errore("ERRORE_PERSONALITA_MANCANTE");
                }
                personalita = parametro;
                return null;
            case COMANDO_DOMANDA:
                if (parametro.isEmpty()) {
                    return errore("ERRORE_DOMANDA_MANCANTE");
                }
                if (personalita.isEmpty()) {
                    return errore("ERRORE_DEVI_PRIMA_IMPOSTARE_UNA_PERSONALITA");
                }
                domanda = parametro;

                String testo = gestoreRisposte.getRisposta(personalita);
                if (testo.startsWith("ERRORE_")) {
                    return errore(testo);
                }
                return gson.toJson(new Risposta(Risposta.TIPO_ORACOLO, testo));


            default:
                return errore("ERRORE_COMANDO_NON_RICONOSCIUTO");
        }
    }

    public String getDomanda() {
        return domanda;
    }

    public String getPersonalita() {
        return personalita;
    }
    
    
    private String errore(String codice) {
        return gson.toJson(new Risposta(Risposta.TIPO_ERRORE, codice));
    }

    
}
