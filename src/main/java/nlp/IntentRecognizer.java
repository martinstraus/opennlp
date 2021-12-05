package nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import opennlp.tools.doccat.*;
import opennlp.tools.tokenize.*;

public class IntentRecognizer {

    private final Tokenizer tokenizer;
    private final DoccatModel model;
    private final DocumentCategorizerME categorizer;

    public IntentRecognizer(File file) throws FileNotFoundException, IOException {
        this.tokenizer = WhitespaceTokenizer.INSTANCE;
        try ( var inputStream = new FileInputStream(file)) {
            this.model = new DoccatModel(inputStream);
            this.categorizer = new DocumentCategorizerME(this.model);
        }
    }

    public String recognize(String text) {
        var tokens = tokenizer.tokenize(text);
        double[] outcomes = categorizer.categorize(tokens);
        return categorizer.getBestCategory(outcomes);
    }

}
