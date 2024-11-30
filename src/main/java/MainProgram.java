import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainProgram {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java MainProgram <filePath>");
            System.exit(1);
        }
        Map<Character, Integer> freqMap = new HashMap<>();
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File does not exsit: " + args[0]);
            System.exit(2);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int c;
            while((c = reader.read()) != -1){
                char character = (char) c;
                freqMap.put(character, freqMap.getOrDefault(character,0)+1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Map.Entry<Character,Integer> entry: freqMap.entrySet() )
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
