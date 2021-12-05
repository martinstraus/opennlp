package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import opennlp.tools.postag.POSDictionary;

public class TagDictionaryCreator {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        var file = new File("./src/main/models/symbols.txt");
        try ( var reader = new BufferedReader(new FileReader(file))) {
            var dictionary = new POSDictionary(false);
            var line = reader.readLine();
            while (line != null) {
                dictionary.put(line.trim(), "SYMBOL");
                line = reader.readLine();
            }
            try ( var output = new FileOutputStream("./src/main/models/symbols.xml")) {
                dictionary.serialize(output);
            }
        }

        System.out.println("¡Listo!");
    }
}
