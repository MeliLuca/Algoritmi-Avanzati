package DataStructures;

import java.util.*;

public class Graph {
    // matrice di adiacenza contenente i pesi dei nodi
    private double [][] adjMatrix;
    // permette di inizializzare la dimensione della matrice
    private int size;
    // permette di estrarre i nodi richiesti in tempo costante.
    private Map<Integer,Node> nodeMap;

    public Graph(int size) {
        this.size= size;
        this.adjMatrix = new double[size][size];
    }

    public void setMapNode(Map<Integer, Node> mapNode) {
        this.nodeMap = mapNode;
    }

    // serve per ritornare una eventuale collezione di nodi
    public Collection<Node> getAllNodes(){
        return nodeMap.values();
    }

    // serve per ritornare una lista di identificatori dei nodi
    public Set<Integer> getAllId(){
        return nodeMap.keySet();
    }

    public void addEdge(int i, int j, double weight) {
        adjMatrix[i][j] = weight;
    }

    public double getWeight(int i, int j){
        return adjMatrix[i][j];
    }

    public Node getNode(int i){
        return nodeMap.get(i);
    }


    public void printGraph(){
        for(int i=1; i< size; i++){
            for(int j=1; j<size; j++){
                System.out.println(i+" "+j+" "+ getWeight(i,j));
            }
        }
    }

    public int getSize() {
        return size;
    }

}
