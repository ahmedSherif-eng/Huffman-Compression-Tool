import HuffmanTree.Hheap;
import HuffmanTree.HuffmanBST;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
    private static final Map<Character, Integer> freqMap = new HashMap<>();
    private static String fileName;
    private static HuffmanBST tree;

    private Decoder() {
    }

    public static void Decode(String fileName) {
        Decoder.fileName = fileName;
        BuildFreqMap();
        BuildTree();
        DecodeFile();
    }

    private static void BuildFreqMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                char key = (char) reader.read();
                StringBuilder value = new StringBuilder();
                while (true) {
                    char c = (char) reader.read();
                    System.out.println(c);
                    if (c == ',') break;
                    value.append(c);
                }
                if ((key == '-' && value.toString().indexOf('-') == 0) || value.toString().contains("--")) {
                    System.out.println("last char: " + (char) reader.read());
                    break;
                }
                freqMap.put(key, Integer.parseInt(value.toString()));
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

    private static void DecodeFile() {
        if (tree == null)
            throw new RuntimeException("Something Internally Went Wrong");
        try (FileInputStream reader = new FileInputStream(fileName);
             FileOutputStream writer = new FileOutputStream(fileName + ".txt")) {
            int c1, c2 = reader.read();
            do {
                c1 = c2;
                c2 = reader.read();

            } while (c1 != '-' || c2 != '-');
            StringBuilder Binary = new StringBuilder();
            int x = reader.read();  //get rid of new line
            while ((x = reader.read()) != -1) {
                String binaryString = String.format("%8s", Integer.toBinaryString(x)).replace(' ', '0');
                Binary.append(binaryString);
                int i = 1;
                while (i <= Binary.length()) {
                    Character ch = tree.getLeaf(Binary.substring(0, i));

                    if (ch != null) {
                        Binary = new StringBuilder(Binary.substring(i));
                        writer.write(ch);
                        break;
                    }
                    i++;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
