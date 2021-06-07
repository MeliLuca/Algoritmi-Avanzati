package DataStructures;

/**
 * L'oggetto Edge viene implementato attraverso due campi interi che identificano gli
 * id dei nodi.
 * I campi non sono final in quanto si permette la modifica dell'oggetto per una maggiore
 * efficenza ed una maggiore semplicità
 */

public class Edge {
    private int node1;
    private int node2;

    public Edge(int node1, int node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    public int getIdNode1() {
        return node1;
    }

    public int getIdNode2() {
        return node2;
    }

    public void setNode1(int id1){
        node1 = id1;
    }

    public void setNode2(int id2){
        node2 = id2;
    }

    public boolean equalById(int id1, int id2) {
        return node1 == id1 && node2==id2;
    }

    public boolean isConnecting(int id1, int id2){
        return equalById(id1,id2) || equalById(id2,id1);
    }

    public void replaceNode(int oldId, int newId){
        if(node1 == oldId) node1 = newId;
        else node2 = newId;
    }
}
