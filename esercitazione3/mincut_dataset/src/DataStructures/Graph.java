package DataStructures;
import java.util.*;

/**
 * Il grafo viene implementato per mezzo di due ArrayList per memorizzare gli archi e i Nodi
 * e due mappe che consentono di effettuare operazioni costanti per quanto riguarda 
 * la get di un nodo in base al suo identificatore e la rimozione di un arco dalla lista E
 */
public class Graph {
    private ArrayList<Node> V;
    private ArrayList<Edge> E;
    private Map<Edge,Integer> mapEdgeIndex; // permette di effettuare la rimozione di un arco in O(1)
    private Map<Integer, Node> mapIdNode; // permette di effettuare la get di un nodo in tempo O(1)
    private int lastId; // permette di chiamare un nodo con un nuovo identificatore

    public Graph() {
        V = new ArrayList<>();
        E = new ArrayList<>();
        mapIdNode = new HashMap<>();
        mapEdgeIndex = new HashMap<>();
        lastId= 1;
    }

    public void addNode(Node n){
        // aggiunta del nodo sia nella lista V che nella mappa
        mapIdNode.put(n.getId(), n);
        V.add(n);
        lastId ++;

    }

    public void addEdge(Edge e){
        // inserisco l'oggetto Edge nella mappa come chiave e come valore memorizzo la sua
        // posizione nella Lista. Questa operazione funziona in quanto la .add() mette
        // l'oggetto alla fine della lista
        mapEdgeIndex.put(e, E.size());
        E.add(e);
    }


    public Edge getEdgeByIndex(int index) {
        return E.get(index);
    }

    public int vertexSize() {
        return V.size();
    }
    
    public int getEdgeSize(){
        return E.size();
    }

    public Node getNodeById(int id){ return mapIdNode.get(id); }


    private void removeNode(int id) {
        Node n = mapIdNode.get(id);
        V.remove(n);
        mapIdNode.remove(id);
    }

    private Node createNode() {
        return new Node(lastId);
    }

    /**
     * per effettuare l'operazione di rimozione in tempo costante, metto l'ultimo
     * elemento della lista in posizione dell'elemento da eliminare tramite il metodo
     * .set( ... ) che risulta essere O(1) e successivamente elimino il puntatore dell'ultima posizione.
     * In Java l'operazione di remove() sull'ultimo oggetto sarà costante in quanto
     * non si dovranno eseguire operazioni di shifting dei puntatori.
     */
    private void removeEdge(Edge e) {
        Edge lastEdge = E.get(E.size()-1);
        int indexCurrEdge = mapEdgeIndex.get(e);
        E.set(indexCurrEdge, lastEdge); // O(1)
        E.remove(E.size()-1); // O(1)
        mapEdgeIndex.remove(e);
        mapEdgeIndex.replace(lastEdge, indexCurrEdge);
    }

    /**
     * Il metodo contraction permette di eliminare l'arco ricevuto in input ed effettuare la contrazione del grafo
     * Questa verrà fatta mediante la creazione di un nuovo nodo che conterrà i lati adiacenti dei nodi
     * n1 ed n2 collegati dal lato da eliminare.
     * Per eseguire ciò, si effettua un ciclo for sui nodi adiacendi del primo nodo, andando ad eliminare
     * dalla lista E tutti gli archi che collegano n1 ad n2, e modificando tutti gli altri archi rimpiazzando
     * l'identificatore del nodo n1 con quello del nodo creato. Nel ciclare i nodi adiacenti di n2 non si andranno
     * a togliere archi da E, ma si modificheranno e si aggiungeranno al nuovo nodo.
     * 
     * Possiamo notare come le operazioni utilizzate sono tutte O(1) e quindi la complessità totale di questo
     * algoritmo risulta essere O(deg(n1)) + O(deg(n2)) che quindi possiamo scrivere come O(n).
     */

    public void contraction(Edge e) {
        int idNode1 = e.getIdNode1();
        int idNode2 = e.getIdNode2();
        Node node1 = mapIdNode.get(idNode1);
        Node node2 = mapIdNode.get(idNode2);
        // creazione del nuovo nodo
        Node unify = createNode();

        // scorro la lista degli adiacenti del nodo n1 ed elimino gli archi che connettono n1 ad n2
        for(Edge currEdge: node1.getAllAdj()){
            if(currEdge.isConnecting(idNode1, idNode2)){
                removeEdge(currEdge);
            }else{ // modifica degli archi ed inserimento nella lista di adiacenza del nodo creato
                currEdge.replaceNode(idNode1, unify.getId());
                unify.addAdj(currEdge);
            }
        }
        // stesso procedimento per il nodo n2 ma senza l'eliminazione degli archi che connettono i due nodi
        for(Edge currEdge: node2.getAllAdj()){
            if(!currEdge.isConnecting(idNode1, idNode2)){
                currEdge.replaceNode(idNode2, unify.getId());
                unify.addAdj(currEdge);
            }
        }
        // rimozione dei nodi dalla lista V ed aggiunta del nuovo nodo
        removeNode(idNode1);
        removeNode(idNode2);
        addNode(unify);
    }
}
