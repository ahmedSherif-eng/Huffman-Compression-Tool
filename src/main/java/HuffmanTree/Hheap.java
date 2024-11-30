package HuffmanTree;

import java.util.PriorityQueue;

public class Hheap {
    private static PriorityQueue<HuffmanBST> heap = new PriorityQueue<>((a, b) -> a.weight() - b.weight());

    public static int heapsize() {
        return heap.size();
    }

    public static void insert(HuffmanBST tree) {
        heap.add(tree);
    }

    public static HuffmanBST removemin() {
        return heap.poll();
    }
}