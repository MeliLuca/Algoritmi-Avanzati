package DataStructures;

import java.util.ArrayList;

// questa classe verrà utilizzata per l'algoritmo di Prim e presenta tutti i
// campi necessari per una efficiente esecuzione dell'algoritmo
public class MSTNode {

    private final int id;
    private double key;
    private ArrayList<MSTNode> children;
    private MSTNode parent;

    public MSTNode(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public MSTNode getParent() {
        return parent;
    }

    public void setParent(MSTNode parent) {
        this.parent = parent;
    }

    public ArrayList<MSTNode> getChildren() {
        return children;
    }

    public void addChild(MSTNode child) {
        this.children.add(child);
    }

    public void removeChild(MSTNode c) {
        children.remove(c);
    }
}


