package DataStructures;

import java.io.File;
import java.util.*;
import java.util.function.BiFunction;

public class GenerationData {

    public static Graph createGraphFromFile(String input) throws Exception {
        File myFile = new File(input);
        Scanner myReader = new Scanner(myFile);
        int dimension = 1;
        String weightTipe = " ";
        Map<Integer, Node> nodeMap = new HashMap<>();

        // inizializzo la funzione della distanza euclidea, nel caso la cambierò
        BiFunction<Node, Node, Double> computeDistance = GenerationData::euclideanDistance;

        while (myReader.hasNext()) {
            String currLine = myReader.nextLine();
            if (currLine.contains("DIMENSION")) {
                String[] line = currLine.split(" ");
                dimension += Integer.parseInt(line[line.length - 1]);
            } else if (currLine.contains("EDGE_WEIGHT_TYPE")) {
                String[] line = currLine.split(" ");
                weightTipe =line[line.length - 1];
                if (weightTipe.equals("GEO")) computeDistance = GenerationData::geoDistance;
            } else if (currLine.contains("NODE_COORD_SECTION")) {
                String nodeToInsert = myReader.nextLine();

                while (!nodeToInsert.contains("EOF")) {
                    String[] tmp = Arrays.stream(nodeToInsert.split(" ")).filter(s -> !s.equals("")).toArray(String[]::new);
                    Node n = new Node(Integer.parseInt(tmp[0]), Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]));

                    // converto le coordinate
                    if (weightTipe.equals("GEO")) {
                        n.setCoord_x(geoToRadiant(n.getCoord_x()));
                        n.setCoord_y(geoToRadiant(n.getCoord_y()));
                    }
                    nodeMap.put(n.getId(), n);
                    nodeToInsert = myReader.nextLine();
                }
            }
        }
        Graph G = new Graph(dimension);
        G.setMapNode(nodeMap);
        for (int i = 1; i < dimension; i++) {
            for (int j = 1; j < dimension; j++) {
                G.addEdge(i, j, computeDistance.apply(nodeMap.get(i), nodeMap.get(j)));
            }
        }

        return G;
    }

    public static double euclideanDistance(Node node1, Node node2) {
        double sumPow = Math.pow(node1.getCoord_x() - node2.getCoord_x(), 2) + Math.pow(node1.getCoord_y() - node2.getCoord_y(), 2);
        Double res = Math.sqrt(sumPow);
        return res.intValue();
    }

    public static double geoDistance(Node node1, Node node2) {
        double RRR = 6378.388;

        double q1 = Math.cos(node1.getCoord_y() - node2.getCoord_y());
        double q2 = Math.cos(node1.getCoord_x() - node2.getCoord_x());
        double q3 = Math.cos(node1.getCoord_x() + node2.getCoord_x());

        Double res = RRR * Math.acos(0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3)) + 1.0;
        return res.intValue();
    }

    public static double geoToRadiant(double x) {
        double PI = 3.141592;
        int deg = (int) x;
        double min = x - deg;
        return PI * (deg + 5.0 * min / 3.0) / 180.0;
    }
}
