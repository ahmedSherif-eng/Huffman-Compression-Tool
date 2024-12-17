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
            System.out.println("Usage: java MainProgram <filePath> <operation {-e|-d}>");
            System.exit(1);
        }
        File file = new File(args[0]);
        Map<Character, Integer> freqMap = new HashMap<>();
        if (!file.exists()) {
            System.out.println("File does not exsit: " + args[0]);
            System.exit(2);
        }
        System.out.println("args[0] = "+ args[0]+" args[1] = "+args[1]);
        if (args[1] == "-e")
            Encoder.Encode(file);
        else if (args[1] == "-d")
            System.out.println(args[1]);
        else{
            System.out.println("Usage: java MainProgram <filePath> <operation {-e|-d}>");
            System.exit(3);
        }

    }
}
