import HuffmanTree.Hheap;
import HuffmanTree.HuffmanBST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainProgram {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MainProgram <filePath>");
            System.exit(1);
        }
        int count = 0;
        File file = new File(args[0]);
        Map<Character, Integer> freqMap = new HashMap<>();
        if (!file.exists()) {
            System.out.println("File does not exsit: " + args[0]);
            System.exit(2);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                freqMap.put(character, freqMap.getOrDefault(character, 0) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count += entry.getValue();
            Hheap.insert(new HuffmanBST(entry.getKey(), entry.getValue()));
        }
        HuffmanBST tree = HuffmanBST.buildTree();
        System.out.println("Weight of tree: " + tree.weight() + " Total count: " + count);
        System.out.println("Search for 'ë': " + tree.searchLeaf('ë'));
        Map<Character, String> prefixMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            prefixMap.put(entry.getKey(), tree.searchLeaf(entry.getKey()));
            System.out.println(entry.getKey() + ": " + tree.searchLeaf(entry.getKey()));
        }
        Encoder.Encode(file, prefixMap, freqMap);
    }
}
