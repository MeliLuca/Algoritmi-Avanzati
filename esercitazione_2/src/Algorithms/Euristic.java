package Algorithms;

import DataStructures.Cycle;
import DataStructures.Graph;
import javafx.util.Pair;

import java.util.*;

public class Euristic {

    // Variabile globale che mi permette di tener conto dei nodi non ancora inseriti
    // utilizzo un HashSet per velocizzare le operazioni di rimozione ed inserimento
    private static Set<Integer> vertexToInsert = new HashSet<>();

    // IMPLEMENTAZIONE TRAMITE CHEAPEST INSERTION

    public static Cycle TSP(Graph G) {
        
        vertexToInsert = G.getAllId();
        //Inizializzo il circuito
        ArrayList<Integer> circuit = initialize(G);

        while (circuit.size() < G.getSize()) {

            Pair<Integer, Integer> vertexToSwap = selection(G, circuit);
            insert(circuit, vertexToSwap);
        }
        // creo un oggetto cycle ed inserisco i pesi degli aechi del circuito
        Cycle TSP = new Cycle();
        for (int i=1; i<circuit.size(); i++){
            TSP.addEdge(G.getWeight(circuit.get(i-1), circuit.get(i)));
        }
        return TSP;
    }

    private static ArrayList<Integer> initialize(Graph G) {
        // inizializzo l'array rappresentante il circuito
        ArrayList<Integer> initCircuit = new ArrayList<>(G.getSize());
        // inizializzo le variabili per la ricerca dei valori minimi
        int minDistVertex = 0;
        double minDist = Double.POSITIVE_INFINITY;
        // trovo il lato più leggero tra il nodo iniziale 1 e tutti gli altri.
        for (int i = 2; i < G.getSize(); i++) {
            double currDistance = G.getWeight(1, i);
            if (currDistance < minDist) {
                minDist = currDistance;
                minDistVertex = i;
            }
        }
        //Inserisco in modo ordinato i nodi nel circuito e li rimuovo dall'HashSet
        initCircuit.add(1);
        initCircuit.add(minDistVertex);
        initCircuit.add(1);
        vertexToInsert.remove(initCircuit.get(0));
        vertexToInsert.remove(initCircuit.get(1));
        return initCircuit;
    }

    private static Pair<Integer, Integer> selection(Graph G, ArrayList<Integer> circuit) {
        // ricerca dell'arco all'interno del circuito
        double minEdge = Double.POSITIVE_INFINITY;
        int firstVertex = 0;
        int vertexFind = 0;

        for (int i = 1; i < circuit.size(); i++) {
            // memorizzo il peso del lato corrente
            double currEdge = G.getWeight(circuit.get(i-1), circuit.get(i));
            // scorro tutti i nodi da inserire per trovare quello che minimizza la disuguaglianza
            for (Integer k : vertexToInsert) {
                double newEdge = G.getWeight(circuit.get(i - 1), k) + G.getWeight(k, circuit.get(i)) - currEdge;
                if(newEdge < minEdge){
                    minEdge = newEdge;
                    firstVertex = circuit.get(i);
                    vertexFind = k;
                }
            }
        }
        // rimuovo il vertice trovato.
        vertexToInsert.remove(vertexFind);
        // ritorno la coppia contente il nodo che verrà spostato ed il nodo da inserire nel circuito
        return new Pair<>(firstVertex, vertexFind);
    }

    private static void insert(ArrayList<Integer> circuit, Pair<Integer, Integer> vertex_to_insert) {
        // Utilizzo il metodo lastIndexOf che ritorna l'indice dell'ultima occorrenza in quanto
        // è presente due volte il nodo 1, e così facendo ritorno la posizione dell'ultimo.
        int index = circuit.lastIndexOf(vertex_to_insert.getKey());
        circuit.add(index, vertex_to_insert.getValue());
    }
}
