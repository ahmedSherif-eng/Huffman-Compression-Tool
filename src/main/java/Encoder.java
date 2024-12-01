import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Encoder {
    private static Map prefixMap;
    private static File inputFile;

    private Encoder() {
    }

    //todo: complete
    public static boolean Encode(File inputFile, Map<Character, String> prefixMap, Map<Character, Integer> freqMap) {
        Encoder.prefixMap = prefixMap;
        Encoder.inputFile = inputFile;
        WriteMetaData(freqMap);
        return true;
    }

    //todo: complete
    public static boolean WriteMetaData(Map<Character, Integer> freqMap) {
        try (FileWriter writer = new FileWriter(inputFile.getName() + " " + "compressed", false)) {
            for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
                writer.write(entry.getKey() + entry.getValue().toString() + ",");
            }
            writer.write("--------------------");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
