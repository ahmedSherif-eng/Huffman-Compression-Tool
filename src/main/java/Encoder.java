import java.io.*;
import java.util.Map;

public class Encoder {
    private static Map prefixMap;
    private static File inputFile;
    private static int currentByte = 0;
    private static int numBitsFilled = 0;

    private Encoder() {
    }

    public static boolean Encode(File inputFile, Map<Character, String> prefixMap, Map<Character, Integer> freqMap) {
        Encoder.prefixMap = prefixMap;
        Encoder.inputFile = inputFile;
        WriteMetaData(freqMap);
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileOutputStream outputFileStream = new FileOutputStream(inputFile.getName() + " " + "compressed", true);
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

    public static boolean WriteMetaData(Map<Character, Integer> freqMap) {
        try (FileWriter writer = new FileWriter(inputFile.getName() + " " + "compressed", false)) {
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

    public static void main(String[] args) {
        String s = "0110";
        System.out.println(s.getBytes());
    }
}
