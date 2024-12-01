import HuffmanTree.Hheap;
import HuffmanTree.HuffmanBST;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//todo complete the decoding algorithm
public class Decoder {
    private static String fileName;
    private static Map<Character, Integer> freqMap = new HashMap<>();

    private Decoder() {
    }

    public static void Decode(String fileName) {
        Decoder.fileName = fileName;
        BuildFreqMap();
    }

    private static void BuildFreqMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            while (true) {
                char key = (char) reader.read();
                String value = "";
                while (true){
                    char c = (char)reader.read();
                    if(c == ',') break;
                    value += c;
                }
                if((key == '-' && value.indexOf('-') == 0) || value.contains("--"))
                    break;
                freqMap.put(key,Integer.parseInt(value));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        fileName = "test.txt compressed";
        int count = 0;
        BuildFreqMap();
        for(Map.Entry<Character,Integer> entry: freqMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            Hheap.insert(new HuffmanBST(entry.getKey(), entry.getValue()));
            count += entry.getValue();
        }
        HuffmanBST tree = HuffmanBST.buildTree();
        System.out.println("Weight of tree: " + tree.weight() + " Total count: " + count);
        System.out.println("Search for 'ë': " + tree.searchLeaf('ë'));
    }
}
