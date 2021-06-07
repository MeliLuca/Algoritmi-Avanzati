package DataStructures;

    /**
     * Questa classe permette di sarvare le performance dell'esecuzione dell'algoritmo
     * che saranno utilizzate per redigere la relazione finale.
     *
     * Il tempo dell'esecuzione della fullContraction verrà restituito come la media di
     * tutte le esecuzioni
     */
public class Measure {

    private long discoveryTime;
    private int minCutSize;
    private long totalTime;
    private long fullContrTime;
    private long fullContrExecution;

    public Measure() {
        this.fullContrExecution = 0;
        this.fullContrTime = 0;
    }

    public long getDiscoveryTime() {
        return discoveryTime;
    }

    public void setDiscoveryTime(long discoveryTime) {
        this.discoveryTime = discoveryTime;
    }

    public int getMinCutSize() {
        return minCutSize;
    }

    public void setMinCutSize(int minCutSize) {
        this.minCutSize = minCutSize;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public long getFullContrTime() {
        return fullContrTime/fullContrExecution;
    }

    public void addFullContrTime(long fullContrTime) {
        this.fullContrTime += fullContrTime;
        this.fullContrExecution++;
    }
}
