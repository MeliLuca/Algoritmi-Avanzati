import DataStructures.GeneratorData;
import DataStructures.Graph;
import DataStructures.Measure;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
//        String path = "mincut_dataset/input/input_random_4_6.txt";
//        String path = "mincut_dataset/input/input_random_5_10.txt";
//        String path = "mincut_dataset/input/input_random_15_50.txt";
//        String path = "mincut_dataset/input/input_random_21_100.txt";
//        String path = "mincut_dataset/input/input_random_27_125.txt";
////        String path = "mincut_dataset/input/input_random_30_150.txt";
////        String path = "mincut_dataset/input/input_random_34_175.txt";
////        String path = "mincut_dataset/input/input_random_40_200.txt";
////        probabilità di sbagliare --> 1/n^d
//        String[] file_name = path.split(".txt")[0].split("_");
//        Graph g = GeneratorData.generationDataFromFile(new File(path));
//
//        int n= Integer.parseInt(file_name[file_name.length-1]);
//        int d = 1;
//        int k = (int) (d*(Math.pow(n,2)/2)*Math.log(n));
//////        System.out.println("k is "+k);
//        DataStructures.Measure result = Karger.minCut(new File(path), k, 5);
////        System.out.println("####################");
//        System.out.println("min cut is "+ result.getMinCutSize());
//        System.out.println("tempo totale algoritmo "+ result.getTotalTime());
//        System.out.println("discovery time "+ result.getDiscoveryTime());
//        System.out.println("tempo medio full contraction "+ result.getFullContrTime());

//        Serve per avviare su tutti i file
//        RunAll();
    }

    public static void RunAll() throws Exception{
        FileWriter csvOutput = new FileWriter("output.csv");
        csvOutput.append("indice_grafo");
        csvOutput.append(",");
        csvOutput.append("num_Nodi");
        csvOutput.append(",");
        csvOutput.append("Full_Contraction_time");
        csvOutput.append(",");
        csvOutput.append("Discovery_Time");
        csvOutput.append(",");
        csvOutput.append("Total_Time");
        csvOutput.append(",");
        csvOutput.append("Soluzione_Trovata");
        csvOutput.append(",");
        csvOutput.append("Soluzione_Esatta");
        csvOutput.append("\n");

        File input_list = new File("mincut_dataset/input");
        Map<Integer,Integer> mapIndexToResult = GeneratorData.generatorOutputFileList("mincut_dataset/output");
        int i=0;
        long start = System.currentTimeMillis();
        for(File f : input_list.listFiles()){
            String[] file_name = f.getName().split(".txt")[0].split("_");
            System.out.println("processo "+ f.getName());
            int numberNodes= Integer.parseInt(file_name[3]);

            int indexFile = Integer.parseInt(file_name[2]);
            int d = 1;
            int k = (int) (d*(Math.pow(numberNodes,2)/2)*Math.log(numberNodes));
            int minute = 5;
            Measure result = Karger.minCut(f, k, minute);

            csvOutput.append(""+indexFile);
            csvOutput.append(",");
            csvOutput.append(""+numberNodes);
            csvOutput.append(",");
            csvOutput.append(""+result.getFullContrTime());
            csvOutput.append(",");
            csvOutput.append(""+(result.getDiscoveryTime()));
            csvOutput.append(",");
            csvOutput.append(""+result.getTotalTime());
            csvOutput.append(",");
            csvOutput.append(""+result.getMinCutSize());
            csvOutput.append(",");
            csvOutput.append(""+mapIndexToResult.get(indexFile));
            csvOutput.append("\n");

        }
        csvOutput.flush();
        csvOutput.close();
        System.out.println("finito in secondi "+ ((System.currentTimeMillis()-start)/1000));
    }
}

