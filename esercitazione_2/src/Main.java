import Algorithms.Approx2;
import Algorithms.Euristic;
import Algorithms.HeldKarp;
import DataStructures.Cycle;
import DataStructures.GenerationData;
import DataStructures.Graph;

import java.io.File;
import java.io.FileWriter;

public class Main {
    private File actual;

    public static void main(String[] args) throws Exception {
        // class to test the algorithm
        String path= "tsp_dataset/dsj1000.tsp";
//        String path ="tsp_dataset/kroD100.tsp";
//        String path= "tsp_dataset/eil51.tsp";
//        String path= "tsp_dataset/berlin52.tsp";
//        String path= "tsp_dataset/ulysses16.tsp";
//        String path = "tsp_dataset/burma14.tsp";
//        String path = "tsp_dataset/d493.tsp";
        Graph G = GenerationData.createGraphFromFile(path);
//
        long start_time_HK = System.currentTimeMillis();
        // come secondo parametro inserire il tempo in minuti
        Cycle result_HKTSP = HeldKarp.TSP(G, 10);
        long end_time_HK = System.currentTimeMillis() - start_time_HK;
        System.out.println("HK       "+result_HKTSP.getWeight()+ " tempo "+ end_time_HK );
//
////
//        long start_time_A2 = System.currentTimeMillis();
//        Cycle result_approx2TSP = Approx2.TSP(G);
//        long end_time_A2 = System.currentTimeMillis() - start_time_A2;
//        System.out.println("2-Approx  "+result_approx2TSP.getWeight()+ " tempo "+ end_time_A2);
//
//        long start_time_EU = System.currentTimeMillis();
//        Cycle result_euristicTSP = Euristic.TSP(G);
//        long end_time_EU = System.currentTimeMillis() - start_time_EU;
//        System.out.println("Euristico "+result_euristicTSP.getWeight()+ " tempo "+ end_time_EU);

//
//        File actual = new File("tsp_dataset");
//        FileWriter out = new FileWriter("output_Approssimato.csv");
//        out.append("istanza");
//        out.append(",");
//        out.append("Held-Karp sol");
//        out.append(",");
//        out.append("Held-Karp time");
//        out.append(",");
//        out.append("Euristico sol");
//        out.append(",");
//        out.append("Euristico time");
//        out.append(",");
//        out.append("2-Approx sol");
//        out.append(",");
//        out.append("2-Approx time");
//        out.append("\n");
//        int minutes = 10;
//
//        for( File f : actual.listFiles()){
//
//            Graph G = GenerationData.createGraphFromFile(f.getPath());
////
////            long start_time_HK = System.currentTimeMillis();
////            Cycle result_HKTSP = HeldKarp.TSP(G, minutes);
////            long end_time_HK = System.currentTimeMillis() - start_time_HK;
////            System.out.println("HK       "+result_HKTSP.getWeight()+ " tempo "+ end_time_HK );
//
//            long start_time_A2 = System.currentTimeMillis();
//            Cycle result_approx2TSP = Approx2.TSP(G);
//            long end_time_A2 = System.currentTimeMillis() - start_time_A2;
////            System.out.println("2-Approx  "+result_approx2TSP.getWeight()+ " tempo "+ end_time_A2);
//
//            long start_time_EU = System.currentTimeMillis();
//            Cycle result_euristicTSP = Euristic.TSP(G);
//            long end_time_EU = System.currentTimeMillis() - start_time_EU;
////            System.out.println("Euristico "+result_euristicTSP.getWeight()+ " tempo "+ end_time_EU);
//
//            out.append(f.getName());
//            out.append(',');
////            out.append(String.valueOf(result_HKTSP.getWeight()));
//            out.append("0");
//            out.append(',');
//            out.append("0");
////            out.append(String.valueOf(end_time_HK/1000));
//            out.append(',');
//            out.append(String.valueOf(result_euristicTSP.getWeight()));
//            out.append(',');
//            out.append(String.valueOf(end_time_EU));
//            out.append(',');
//            out.append(String.valueOf(result_approx2TSP.getWeight()));
//            out.append(',');
//            out.append(String.valueOf(end_time_A2));
//            out.append("\n");
//
//        }
//        out.flush();
//        out.close();
    }
}
