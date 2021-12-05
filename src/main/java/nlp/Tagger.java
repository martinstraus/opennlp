package nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import opennlp.tools.postag.*;
import opennlp.tools.tokenize.*;

public class Tagger {

    private final Tokenizer tokenizer;
    private final POSModel model;
    private final POSTaggerME tagger;

    public Tagger(File file) throws FileNotFoundException, IOException {
        this.tokenizer = WhitespaceTokenizer.INSTANCE;
        try ( var inputStream = new FileInputStream(file)) {
            this.model = new POSModel(inputStream);
            this.tagger = new POSTaggerME(this.model);
        }
    }

    public String[] recognize(String text) {
        var tokens = tokenizer.tokenize(text);
        String[] tags = tagger.tag(tokens);
        return tags;
    }

}
