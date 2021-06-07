package DataStructures;

import java.util.ArrayList;

/**
 * Classe Node implementata attraverso un campo id ed una lista di adiacenza che
 * memorizza oggetti di tipo Edge che toccano il nodo.
 * Usando un puntatore all'oggetto arco, se avviene una modifica di un determinato arco da parte di un'altro
 * nodo l'oggetto cambierà anche per l'altro nodo che punterà a quel determinato arco.
 */

public class Node {
    private final int id;
    private ArrayList<Edge> adj;

    public Node(int id) {
        this.id = id;
        this.adj = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Edge> getAllAdj(){
        return adj;
    }

    public void addAdj(Edge e) {
        adj.add(e);
    }

}
