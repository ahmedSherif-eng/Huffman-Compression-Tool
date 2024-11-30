import HuffmanTree.HuffmanBST;

import java.io.File;
import java.util.Map;

public class Encoder {
    private static Map prefixMap;
    private static File inputFile;

    private Encoder() {
    }

    //todo: complete
    public static boolean Encode(Map map, File inputFile) {
        Encoder.prefixMap = map;
        Encoder.inputFile = inputFile;
        return true;
    }
    //todo: complete
    private static boolean WriteMetaData(){
        return true;
    }
}
