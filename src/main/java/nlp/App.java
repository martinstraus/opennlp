package nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        var intentModelFile = args.length > 0 ? args[0] : "./src/main/models/es-intent.bin";
        var recognizer = new IntentRecognizer(new File(intentModelFile));
        var taggerModelFile = args.length > 1 ? args[1] : "./src/main/models/es-tagger.bin";
        var tagger = new Tagger(new File(taggerModelFile));
        try ( var reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter text: ");
                var line = reader.readLine();
                if (line != null && !line.isBlank()) {
                    var category = recognizer.recognize(line);
                    var tags = tagger.recognize(line);
                    System.out.printf(">>> %s", category);
                    for (String tag : tags) {
                        System.out.printf(" %s", tag);
                    }
                    System.out.println("");
                }
            }
        }
    }

}
