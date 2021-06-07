package Algorithms;

import DataStructures.*;
import java.util.ArrayList;
import java.util.Stack;

public class Approx2 {

    public static Cycle TSP(Graph G){

        ArrayList<MSTNode> T = Prim(G, 1);
        ArrayList<Integer> preList = preorder(T, 1);
        preList.add(1);
        // costruisco il ciclo
        Cycle c = new Cycle();
        for(int i=1; i< preList.size(); i++) {
            double edge = G.getWeight(preList.get(i-1), preList.get(i));
            c.addEdge(edge);
        }
        return c;
    }

    private static ArrayList<Integer> preorder(ArrayList<MSTNode> T, int s) {
        // Per effettuare la preorder utilizzo una visita in profondità effettuata con uno Stack
        // inserendo i nodi in ordine inverso rispetto all'ordine mantenuto sul campo children.
        Stack<MSTNode> node_to_visit = new Stack<>();
        // conterrà i nodi visitati
        ArrayList<Integer> resultList = new ArrayList<>();


        MSTNode initVertex = T.get(s);
        resultList.add(T.get(s).getId());
        ArrayList<MSTNode> initVertexChildren = initVertex.getChildren();
        // Inserisco i nodi in ordine inverso nello stack
        for(int i= initVertexChildren.size()-1; i>=0; i--){
            node_to_visit.push(initVertexChildren.get(i));
        }

        while(!node_to_visit.empty()){
            // prendo il primo nodo presente nello stak, lo visito ed inserisco i figli
            // in ordine inverso.
            MSTNode currNode = node_to_visit.pop();
            resultList.add(currNode.getId());
            ArrayList<MSTNode> children = currNode.getChildren();
            for(int i= children.size()-1; i>=0; i--){
                node_to_visit.push(children.get(i));
            }
        }

        return resultList;
    }

    public static ArrayList<MSTNode> Prim(Graph G, Integer s) {
        // setto i valori dei campi dei nodi
        ArrayList<MSTNode> V = new ArrayList<>();
        // aggiungo nodo iniziale 0 per effettuare la giusta corrispondenza con i Nodi del grafo che partono da 1
        V.add(new MSTNode(0));
        G.getAllNodes().forEach(n ->{
            MSTNode u = new MSTNode(n.getId());
            u.setKey(Double.POSITIVE_INFINITY);
            u.setParent(null);
            V.add(u);
        });
        V.get(s).setKey(0);
        // inizializzo l'heap
        Heap Q = new Heap(V);

        while (Q.heap_size > 0){
            MSTNode extract_node = Q.extractMin();
            // scorro tutti i lati adiacenti del nodo
            V.forEach(adj_node->{
                if(adj_node != extract_node){
                    double weightEdge = G.getWeight(extract_node.getId(), adj_node.getId());
                    // recupero il nodo opposto
                    // verifico se non faccia già parte dell'Heap. Operazioni costanti grazie alla hash-map
                    if(Q.contains(adj_node) && weightEdge < adj_node.getKey()){
                        int index_node = Q.indexOf(adj_node); // recupero in tempo costante l'indice del nodo da modificare
                        Q.deleteKey(index_node);
                        // aggiorno la lista dei figli dell'eventuale padre che ho rimosso
                        if(adj_node.getParent() != null) adj_node.getParent().removeChild(adj_node);

                        extract_node.addChild(adj_node);
                        adj_node.setParent(extract_node);
                        adj_node.setKey(weightEdge);
                        Q.insertKey(adj_node);
                    }
                }
            });
        }
        return V;
    }
}
