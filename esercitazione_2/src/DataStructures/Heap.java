package DataStructures;

import java.util.*;

public class Heap {
    public MSTNode[] heap; // array ordinato che rappresenta l'albero binario
    public Map<MSTNode, Integer> node_to_index; // mappa per mantenere la relazione tra nodo e posizione nell'heap
    public int heap_size; // permette di memorizzare il numero di elementi che sono all'interno dell'heap.

    public Heap(ArrayList<MSTNode> v) {
        this.heap = new MSTNode[v.size()];
        this.node_to_index = new HashMap<>();
        this.heap_size= 0;
        v.forEach(n ->{
            insertKey(n);
        });
    }

    public MSTNode extractMin() {
        // estraggo il primo elemento e riordino l'heap
        MSTNode min = heap[1];
        heap[1] = heap[heap_size-1];
        node_to_index.replace(heap[heap_size-1], 1);
        node_to_index.remove(min);
        heap_size --;
        minHeapify(1);
        return min;
    }

    public void insertKey(MSTNode k){
        // genero un nodo temporaneo in modo da poter utilizzare la funzione decreaseKey che scambia le posizioni dei
        // nodi all'interno dell'heap
        this.heap_size ++;
        MSTNode tmp = new MSTNode(0);
        tmp.setKey(Double.POSITIVE_INFINITY);
        heap[heap_size-1] = tmp;
        decreaseKey(heap_size-1, k);
    }

    private void decreaseKey(int i, MSTNode k) {
        // inserisce alla posizione i il nodo k e procede a mantenere la propiretà fondamentale nell'heap
        heap[i] = k;
        node_to_index.put(k, i);
        while(i > 1 && heap[i].getKey() < heap[parent(i)].getKey()){
            scambia(i, parent(i));
            i = parent(i);
        }
    }

    public void deleteKey(int i){
        if(heap_size == 1) heap_size = 0;
        else{
            MSTNode value = heap[i]; // memorizzo il nodo alla posizione i-esima
            heap[i] = heap[heap_size-1]; // inserisco l'ultimo elemento alla posizione i-esima
            node_to_index.replace(heap[heap_size-1], i); // scambio i valori nella Mappa
            node_to_index.remove(value);
            heap_size --; // diminuisco il contatore dei nodi che fanno parte dell'heap
            // procedo verificando se l'heap rispetterà la proprietà fondametale
            if(value.getKey() < heap[i].getKey()){
                minHeapify(i);
            }else{
                while(i > 1 && heap[i].getKey() < heap[parent(i)].getKey()){
                    scambia(i, parent(i));
                    i = parent(i);
                }
            }
        }
    }

    public void minHeapify(Integer i){
        // metodo che mantiene la proprietà fondametale dell'heap
        int l = left(i);
        int r = right(i);
        int min;
        if((l < heap_size) && (heap[l].getKey() < heap[i].getKey())){
            min= l;
        }else{
            min = i;
        }
        if(r < heap_size && heap[r].getKey() < heap[min].getKey()){
            min = r;
        }
        if(min != i){
            scambia(i, min);
            minHeapify(min);
        }
    }

    public void scambia(int i, int j){
        node_to_index.replace(heap[i], j);
        node_to_index.replace(heap[j], i);
        MSTNode tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public int left(int i){
        return 2*i;
    }

    public int right(int i){
        return 2*i+1;
    }

    public int parent(int i){
        return (int) Math.ceil(i/2);
    }

    public boolean contains(MSTNode node) {
        return node_to_index.containsKey(node);
    }

    public int indexOf(MSTNode node) {
        return node_to_index.get(node);
    }
}
