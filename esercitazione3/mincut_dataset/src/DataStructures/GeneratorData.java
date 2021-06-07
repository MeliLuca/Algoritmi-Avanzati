package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Permette di creare un grafo da File. Verrà richiamata molteplici volte per creare nuove copie
 * del grafo da utilizzare nel metodo FullContraction.
 * Tramite i puntatori all'oggetto sarà possibile modificare il lato una sola volta migliorando l'efficenza
 * dell'algoritmo.
 */

public class GeneratorData {
    public static Graph generationDataFromFile(File input) throws Exception{
        Scanner myReader = new Scanner(input);
        Graph G = new Graph();
        int numberNodes = Integer.parseInt(input.getName().split(".txt")[0].split("_")[3]);
        // aggiunta dei nodi al grafo
        for(int i=1;i<=numberNodes;i++){
            G.addNode(new Node(i));
        }
        while (myReader.hasNext()){
            String[] line = myReader.nextLine().split(" ");
            Node n = G.getNodeById(Integer.parseInt(line[0]));
            for(int i=1; i<line.length; i++){
                int opposite = Integer.parseInt(line[i]);
                // dato che il grafo è NON diretto i lati verranno creati ed aggiunti una sola volta al grafo
                // e saranno inseriti nelle rispettive liste di adiacenza dei nodi che collegano.
                if(opposite > n.getId()){
                    Edge e = new Edge(n.getId(), opposite);
                    n.addAdj(e);
                    G.getNodeById(opposite).addAdj(e);
                    G.addEdge(e);
                }
            }
        }
        return G;
    }


    // Questo metodo permette di generare una mappa che contenga i risultati esatti dell'algoritmo in base
    // all'identificatore del file. Viene utilizzata per aumentare l'efficenza al momento dell'esecuzione
    // su tutti i file.

    public static Map<Integer, Integer> generatorOutputFileList(String path) throws FileNotFoundException {
        File myFile = new File(path);
        Map<Integer,Integer> mapFile = new HashMap<>();
        for(File f: myFile.listFiles()){
            Scanner scanner = new Scanner(f);
            Integer value = Integer.parseInt(scanner.nextLine());
            Integer key = Integer.parseInt(f.getName().split(".txt")[0].split("_")[2]);
            mapFile.put(key,value);
        }
        return mapFile;
    }
}
