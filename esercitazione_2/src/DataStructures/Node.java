package DataStructures;

public class Node {
    private final int id;
    private double coord_x;
    private double coord_y;

    public Node(int id, double coord_x, double coord_y){
        this.id=id;
        this.coord_x= coord_x;
        this.coord_y=coord_y;
    }

    public int getId() {
        return id;
    }

    public double getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(double coord_x) {
        this.coord_x = coord_x;
    }

    public double getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(double coord_y) {
        this.coord_y = coord_y;
    }
}
