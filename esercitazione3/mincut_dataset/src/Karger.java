import DataStructures.Edge;
import DataStructures.GeneratorData;
import DataStructures.Graph;
import DataStructures.Measure;

import java.io.File;
import java.time.LocalTime;

public class Karger {
    // definizione variabile globale per il timeout
    private static LocalTime timeLimit;

    public static Measure minCut(File path, int k, int timeout) throws Exception {
        // inizializzazione delle variabili per l'algoritmo
        int min = Integer.MAX_VALUE;
        Measure res = new Measure();
        // timeout per fermare l'esecuzione dell'algoritmo
        timeLimit = LocalTime.now().plusMinutes(timeout);
        // inizializzo variabile per calcolare il tempo di esecuzione dell'algoritmo
        long start_Karger = System.currentTimeMillis();
        // for con doppia condizione per permettere l'uscita allo scadere del timeout
        for(int i=0; i<k  && remainTime(); i++){
            // inizializzazione variabile per calcolare il tempo di una singola full contraction
            long start_fc = System.currentTimeMillis();
            // esecuzione full. contr. passando in input un nuovo grafo generato tramite
            // il file dato in input.
            int t = fullContraction(GeneratorData.generationDataFromFile(path));
            // aggiunta del tempo di esecuzione
            res.addFullContrTime(System.currentTimeMillis()-start_fc);
            // verifica del risultato. Se è minimo allora si andrà a settare i valori
            // per verificare i risultati
            if(t < min) {
                min = t;
                res.setDiscoveryTime(System.currentTimeMillis()-start_Karger);
                res.setMinCutSize(min);
            }
        }
        res.setTotalTime(System.currentTimeMillis()-start_Karger);
        return res;
    }


    private static int fullContraction(Graph CopyGraph) {
        // implementazione classica dell'algoritmo full Contraction

        int initialSize = CopyGraph.vertexSize();
        for(int i=0; i < initialSize-2; i++){
            int index = (int) Math.ceil(Math.random() * CopyGraph.getEdgeSize()-1);
            Edge e = CopyGraph.getEdgeByIndex(index);
            CopyGraph.contraction(e);
        }
        return CopyGraph.getEdgeSize();
    }

    private static boolean remainTime() {
        return LocalTime.now().compareTo(timeLimit) <= 0;
    }
}
