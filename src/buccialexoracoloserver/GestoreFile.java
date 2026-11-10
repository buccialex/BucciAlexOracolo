package buccialexoracoloserver;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
public class GestoreFile implements IGestoreJson{
    
    private final Map<String, List<String>> risposte = new LinkedHashMap<>();
    private final Random r = new Random();
    
    public GestoreFile(){}
    
    @Override
    public void creaJsonRisposte(String percorsoFile){
        Map<String, List<String>> risposteMap = new LinkedHashMap<>();

        // Inserimento a codice delle personalità e delle relative frasi
        risposteMap.put("SAGGIO", Arrays.asList(
            "La pazienza è la virtù dei forti.",
            "Guarda oltre ciò che vedi.",
            "Il tempo rivela ogni verità."
        ));

        risposteMap.put("COMICO", Arrays.asList(
            "Hai provato a spegnere e riaccendere?",
            "Non posso rispondere ora, sto mangiando una pizza!",
            "Chiedimelo di nuovo dopo la pausa caffè."
        ));

        risposteMap.put("MISTICO", Arrays.asList(
            "Le stelle indicano un grande futuro.",
            "Il destino è ancora incerto.",
            "Vedo una risposta felice all'orizzonte."
        ));

        // Scrittura del file JSON con formattazione leggibile
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(percorsoFile)) {
            gson.toJson(risposteMap, writer);
        } catch (IOException ex) {
            System.getLogger(GestoreFile.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override
    public void caricaRisposte(String percorsoFile){
        try (FileReader reader = new FileReader(percorsoFile)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            risposte.clear();

            for (String personalita : jsonObject.keySet()) {
                JsonArray arrayRisposte = jsonObject.getAsJsonArray(personalita);
                List<String> lista = new ArrayList<>();

                for (JsonElement elem : arrayRisposte) {
                    lista.add(elem.getAsString());
                }

                risposte.put(personalita.trim().toUpperCase(), lista);
            }
        } catch (FileNotFoundException ex) {
            System.getLogger(GestoreFile.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(GestoreFile.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String getRisposta(String personalita) {
        if (personalita == null || personalita.trim().isEmpty()) {
            return "ERRORE_PERSONALITA_NON_DEFINITA";
        }
        List<String> lista = risposte.get(personalita.trim().toUpperCase());
        if (lista == null || lista.isEmpty()) {
            return "ERRORE_NESSUNA_RISPOSTA_PER_QUESTA_PERSONA";
        }
        return lista.get(r.nextInt(lista.size()));
    }
}
