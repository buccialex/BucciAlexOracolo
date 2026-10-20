package buccialexoracoloserver;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bucci.alex
 */
public class Protocollo {

    private final String AIUTO = "HELP";
    private final String COMANDO_PERSONA = "PERSONA";
    private final String COMANDO_DOMANDA = "DOMANDA";
    private String domanda;
    private String personalita;
    private GestoreFile gestoreRisposte;

    public Protocollo(GestoreFile gestoreRisposte) {
        this.domanda = "";
        this.personalita = "";
        this.gestoreRisposte = gestoreRisposte;
    }

    public synchronized String interpreta(String messaggio) {
        if (messaggio == null) {
            return "ERRORE_MESSAGGIO_NULLO";
        }

        String comando = messaggio.trim();
        int separatore = comando.indexOf('_');
        String tipoComando = separatore >= 0 ? comando.substring(0, separatore) : comando;
        String parametro = separatore >= 0 ? comando.substring(separatore + 1).trim() : "";

        switch (tipoComando) {
            case AIUTO:
                if (!AIUTO.equals(comando)) {
                    return "ERRORE_COMANDO_NON_RICONOSCIUTO";
                }
                return "HELP: PERSONA_[parametroPersonalita], DOMANDA_[parametroDomanda], HELP";
            case COMANDO_PERSONA:
                if (parametro.isEmpty()) {
                    return "ERRORE_PERSONALITA_MANCANTE";
                }
                personalita = parametro;
                return "OK_PERSONA_" + personalita;
            case COMANDO_DOMANDA:
                if (parametro.isEmpty()) {
                    return "ERRORE_DOMANDA_MANCANTE";
                }
                if (personalita.isEmpty()) {
                    return "ERRORE_DEVI_PRIMA_IMPOSTARE_UNA_PERSONALITA";
                }
                domanda = parametro;

                return "ORACOLO: " + gestoreRisposte.getRisposta(personalita);

            default:
                return "ERRORE_COMANDO_NON_RICONOSCIUTO";
        }
    }

    public synchronized String getDomanda() {
        return domanda;
    }

    public synchronized String getPersonalita() {
        return personalita;
    }
}
