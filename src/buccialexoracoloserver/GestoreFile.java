package buccialexoracoloserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bux
 */
public class GestoreFile {
    
    private final Map<String, String> risposte = new HashMap<>();
    
    public GestoreFile(){}
    
    public void caricaRisposte(String percorsoFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoFile))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                riga = riga.trim();
                if (riga.isEmpty() || riga.startsWith("#")) {
                    continue; 
                }
                String[] parti = riga.split("=", 2);
                if (parti.length == 2) {
                    risposte.put(parti[0].trim().toUpperCase(), parti[1].trim());
                }
            }
        }
    }
    
    public String getRisposta(String personalita) {
        if (personalita == null || personalita.trim().isEmpty()) {
            return "ERRORE_PERSONALITA_NON_DEFINITA";
        }
        return risposte.getOrDefault(
            personalita.trim().toUpperCase(), 
            "ERRORE_NESSUNA_RISPOSTA_PER_QUESTA_PERSONALITA"
        );
    }
}
