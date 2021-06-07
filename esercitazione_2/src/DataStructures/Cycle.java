package DataStructures;

// la classe Cycle permette di memorizzare un ciclo e le eventuali caratteristiche.
public class Cycle {
    private int weight;
    private double size;

    public Cycle(){
        this.weight= 0;
        this.size = 0;
    }

    public Cycle(Double w, double s){
        this.weight=w.intValue();
        this.size=s;
    }

    public void addEdge(double w){
        weight += w;
        size ++;
    }

    public double getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }
}
