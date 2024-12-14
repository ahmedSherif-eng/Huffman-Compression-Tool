import HuffmanTree.Hheap;
import HuffmanTree.HuffmanBST;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//todo complete the decoding algorithm
public class Decoder {
    private static String fileName;
    private static Map<Character, Integer> freqMap = new HashMap<>();
    private static HuffmanBST tree;
    private static String stream;
    private static int indexOfText = 0;

    private Decoder() {
    }

    public static void Decode(String fileName) {
        Decoder.fileName = fileName;
        BuildFreqMap();
        BuildTree();
    }

    private static void BuildFreqMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            while (true) {
                char key = (char) reader.read();
                indexOfText++;
                String value = "";
                while (true) {
                    char c = (char) reader.read();
                    indexOfText++;
                    if (c == ',') break;
                    value += c;
                }
                if ((key == '-' && value.indexOf('-') == 0) || value.contains("--"))
                    break;
                freqMap.put(key, Integer.parseInt(value));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void BuildTree() {
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
            Hheap.insert(new HuffmanBST(entry.getKey(), entry.getValue()));
        }
         tree = HuffmanBST.buildTree();
    }
/*    private static void  Decode(){
        if(tree == null)
            throw new RuntimeException("Something Internally Went Wrong");

    }*/



    public static void main(String[] args) throws FileNotFoundException {
        fileName = "test.txt compressed";
        BuildFreqMap();
        BuildTree();

        System.out.println("Weight of tree: " + tree.weight() );
        System.out.println("Search for 'ë': " + tree.searchLeaf('ë'));
        System.out.println("code of 0011 :" + tree.getLeaf("001"));
        System.out.println("Index of last seperator = "+ indexOfText);

        try (RandomAccessFile file = new RandomAccessFile(new File(fileName), "r")) {
            file.seek(indexOfText); // move the file pointer to the specified position
            // read a line from the specified position
            System.out.println("Read from position " + indexOfText + ": " + (char)file.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
