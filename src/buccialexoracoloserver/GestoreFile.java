package buccialexoracoloserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bux
 */
public class GestoreFile {
    
    private final Map<String, List<String>> risposte = new LinkedHashMap<>();
    private Random r = new Random();
    
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
                    risposte.computeIfAbsent(parti[0].trim().toUpperCase(), k -> new ArrayList<>()).add(parti[1].trim());
                }
            }
        }
    }
    
    public String getRisposta(String personalita) {
        if (personalita == null || personalita.trim().isEmpty()) {
            return "ERRORE_PERSONALITA_NON_DEFINITA";
        }
        List<String> lista = risposte.get(personalita.trim().toUpperCase());
        if(lista == null || lista.isEmpty()){
            return "ERRORE_NESSUNA_RISPOSTA_PER_QUESTA_PERSONA";
        }
        return lista.get(r.nextInt(lista.size()));
    }
}
