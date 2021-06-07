package Algorithms;

import DataStructures.Cycle;
import DataStructures.Graph;

import java.time.LocalTime;
import java.util.*;

public class HeldKarp {

    // per la memorizzazione delle tabelle utilizzo una hashpap di hashmap, la prima chiave sarà
    // di tipo HashSet in quanto permette l'identificazione univoca degli insiemi.
    private static Map<HashSet<Integer>, Map<Integer, Double>> d;
    private static Map<HashSet<Integer>, Map<Integer, Double>> pred;
    // permette di memorizzare il vertice iniziale
    private static int initVertex;
    private static Graph G;
    // permette di memorizzare la durata di tempo che l'algoritmo deve rispettare.
    // utilizzo la classe LocalTime per sfruttare al meglio i metodi per gestire
    // i tempi da Java.
    private static LocalTime timeLimit;

    public static Cycle TSP(Graph inputG, int timeout) {
        d = new HashMap<>();
        pred = new HashMap<>();
        G = inputG;
        initVertex = 1;
        HashSet<Integer> V = new HashSet<>(G.getAllId());
        // inizializzo
        timeLimit = LocalTime.now().plusMinutes(timeout);

        double result = visit_HK(initVertex, V);

        return new Cycle(result, V.size());
    }

    private static double visit_HK(int v, HashSet<Integer> S) {
        if (S.contains(v) && S.size() == initVertex) {
            return G.getWeight(v, initVertex);
        } else if (d.get(S) != null && d.get(S).get(v) != null) {
            return d.get(S).get(v);
        } else {
            double mindist = Double.POSITIVE_INFINITY;
            double minprec = Double.POSITIVE_INFINITY;
            // creo un nuovo insieme per poter memorizzare il nuovo insieme di vertici senza il nodo v
            HashSet<Integer> set_without_v = (HashSet<Integer>) S.clone();
            set_without_v.remove(v);

            for (Integer u : set_without_v) {
                // permette di verificare se rimane tempo continuare l'esecuzione dell'algoritmo.
                // altrimenti ritorno il valore INFINITO per poter confrontarlo con gli altri trovati precedentemente
                double dist = remainTime() ? visit_HK(u, set_without_v) : Double.POSITIVE_INFINITY;

                if (dist + G.getWeight(u, v) < mindist) {
                    mindist = dist + G.getWeight(u, v);
                    minprec = u;
                }
            }
            // inserisco i valori trovati nelle tabelle.
            d.computeIfAbsent(S, k -> new HashMap<>());
            d.get(S).put(v, mindist);

            pred.computeIfAbsent(S, k -> new HashMap<>());
            pred.get(S).put(v, minprec);

            return mindist;
        }
    }

    private static boolean remainTime() {
        return LocalTime.now().compareTo(timeLimit) <= 0;
    }

}
