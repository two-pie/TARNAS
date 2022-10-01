package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.antlr.RNAFileListener;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureLexer;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.List;

public class RNAFileConstructor {

    private static RNAFileConstructor instance;
    private final ParseTreeWalker walker;
    private final RNAFileListener rnaFilelistener;

    /**
     * Invisible constructor.
     */
    private RNAFileConstructor() {
        // Create a generic parse tree walker that can trigger callbacks
        this.walker = new ParseTreeWalker();
        // Create the specialised listener for the RNA secondary structure
        this.rnaFilelistener = new RNAFileListener();
    }

    /**
     * Factory method for the obtaining the {@link RNAFileConstructor} instance.
     *
     * @return the instance of this Singleton
     */
    public static RNAFileConstructor getInstance() {
        if (instance == null)
            instance = new RNAFileConstructor();
        return instance;
    }

    public RNAFile construct(List<String> content, String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        content.forEach(s -> sb.append(s).append("\n"));
        CharStream input = CharStreams.fromString(sb.toString());
        // create a lexer that feeds off of input CharStream
        RNASecondaryStructureLexer lexer = new RNASecondaryStructureLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        RNASecondaryStructureParser structureParser = new RNASecondaryStructureParser(tokens);
        // begin parsing at rna rule
        ParseTree tree = structureParser.rna_format();
        this.rnaFilelistener.setContent(content, fileName);
        // Walk the tree created during the parse, trigger callbacks
        this.walker.walk(rnaFilelistener, tree);
        return rnaFilelistener.getRnaFile();
    }

}
