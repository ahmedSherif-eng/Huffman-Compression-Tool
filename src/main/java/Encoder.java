import HuffmanTree.Hheap;
import HuffmanTree.HuffmanBST;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Encoder {
    private static Map<Character,String> prefixMap = new HashMap();
    private static File inputFile;
    private static int currentByte = 0;
    private static int numBitsFilled = 0;
    private static HuffmanBST tree;

    private Encoder() {
    }

    public static boolean Encode(File file) {
        Encoder.inputFile = file;
        String fileName = inputFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        fileName = fileName.substring(0, dotIndex);
        Map<Character, Integer> freqMap = BuildFreqMap(file);
        WriteMetaData(freqMap,fileName);
        BuildPrefixMap(freqMap);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileOutputStream outputFileStream = new FileOutputStream(fileName + " compressed", true)
        ) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                writeBit(prefixMap.get(character), outputFileStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private static boolean WriteMetaData(Map<Character, Integer> freqMap, String fileName) {
        try (FileWriter writer = new FileWriter(fileName+ " compressed", false)) {
            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                writer.write(entry.getKey() + entry.getValue().toString() + ",");
            }
            writer.write("--\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private static void writeBit(String s, FileOutputStream output) throws IOException {
        int bit;
        for (char ch : s.toCharArray()) {
            bit = ch == '0' ? 0 : 1;
            currentByte = (currentByte << 1) | bit;
            numBitsFilled++;
            if (numBitsFilled == 8) {
                output.write(currentByte);
                numBitsFilled = 0;
                currentByte = 0;
            }
        }
    }

    private static Map<Character,Integer> BuildFreqMap(File file){
        Map<Character, Integer> freqMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int c;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                freqMap.put(character, freqMap.getOrDefault(character, 0) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return freqMap;
    }
    private static void BuildPrefixMap(Map<Character,Integer> freqMap){
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet())
            Hheap.insert(new HuffmanBST(entry.getKey(), entry.getValue()));
        tree = HuffmanBST.buildTree();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            prefixMap.put(entry.getKey(), tree.searchLeaf(entry.getKey()));
            System.out.println(entry.getKey() + ": " + tree.searchLeaf(entry.getKey()));
        }


    }
    public static void main(String[] args) {
        String s = "0110";
        System.out.println(s.getBytes());
    }
}
