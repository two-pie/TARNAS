package it.unicam.cs.twopie.tarnas.model.antlr;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci il path del file: ");
        CharStream input = CharStreams.fromFileName(sc.next());
        // create a lexer that feeds off of input CharStream
        RNASecondaryStructureLexer lexer = new RNASecondaryStructureLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        RNASecondaryStructureParser structureParser = new RNASecondaryStructureParser(tokens);
        // begin parsing at rna rule
        ParseTree tree = structureParser.rna_format();
        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Create the specialised listener for the RNA secondary structure
        RNAFileConstructor constructor = new RNAFileConstructor("pippo");//TODO togliere questa classe
        // Walk the tree created during the parse, trigger callbacks
        walker.walk(constructor, tree);
        RNAFile rnaFile = constructor.getRnaFile();
        rnaFile.header().forEach(System.out::println);
    }
}
