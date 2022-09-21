package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.antlr.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureLexer;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RNAFileTranslatorTest {

    private static Path archaea90110AllType;


    @BeforeAll
    static void initializePath() throws URISyntaxException {
        archaea90110AllType = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/Archaea-90-110-allType")).toURI());
    }

    @Test
    @DisplayName("Archaea-90-110-allType Benchmark")
    public void ArchaeaAllTYpe() throws IOException {
        //System.out.println(this.archaea90110AllType);
        // this.translateFormatToAll(archaea90110AllType.toString() + "/BPSeqFiles");

    }

    @ParameterizedTest
    @MethodSource("Archaea90110AllTypeFormatFolders")
    public void translateFormatToAll(String formatFolderPath, List<String> expectedFolder, List<String> expectedExtension, List<RNAFormat> rnaFormat) throws IOException {
        Files.walk(Paths.get(formatFolderPath)).filter(Files::isRegularFile).forEach(
                f -> {
                    try {
                        RNAFile toTranslate = this.readFile(f.toString());
                        for (int i = 0; i < expectedFolder.size(); i++) {
                            RNAFile expected = this.readFile(archaea90110AllType.toString() + expectedFolder.get(i) +
                                    this.getFileNameWithoutExtension(f.getFileName()) + expectedExtension.get(i));
                            switch (rnaFormat.get(i)) {
                                case AAS -> this.translateToAAS(toTranslate, expected);
                                case AAS_NO_SEQUENCE -> this.translateToAASNoSequence(toTranslate, expected);
                                case BPSEQ -> this.translateToBPSEQ(toTranslate, expected);
                                case CT -> this.translateToCT(toTranslate, expected);
                                case DB -> this.translateToDB(toTranslate, expected);
                                case DB_NO_SEQUENCE -> this.translateToDBNoSequence(toTranslate, expected);
                            }
                        }
                        /*expectedFileAndFormat.forEach(format -> {
                                    RNAFile toTranslate = this.readFile(f.toString());
                                    RNAFile expected = this.readFile(archaea90110AllType.toString() + "/DBNFilesNH/" + this.getFileNameWithoutExtension(f.getFileName()) + ".dbn");
                                    this.translateToDBNoSequence(toTranslate, expected);
                                }
                        );
                        expected = this.readFile(archaea90110AllType.toString() + "/DBNFiles/" + this.getFileNameWithoutExtension(f.getFileName()) + ".dbn");
                        this.translateToDB(toTranslate, expected);
                        expected = this.readFile(archaea90110AllType.toString() + "/CTFiles/" + this.getFileNameWithoutExtension(f.getFileName()) + ".ct");
                        this.translateToCT(toTranslate, expected);
                        expected = this.readFile(archaea90110AllType.toString() + "/FastaFiles/" + this.getFileNameWithoutExtension(f.getFileName()) + ".fasta");
                        this.translateToFASTA(toTranslate, expected);*/
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private static Stream<Arguments> Archaea90110AllTypeFormatFolders() {
        return Stream.of(
                // BPSSeqFiles
                Arguments.of(archaea90110AllType.toString() + "/BPSeqFiles",
                        List.of("/CTFiles/", "/CTFilesNH/", "/DBNFiles/", "/DBNFiles/", "/CTFilesNH/"),
                        List.of(".ct", ".ct", ".fasta", ".ct", ".ct"),
                        List.of(RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA, RNAFormat.CT, RNAFormat.CT)),
                // CTFiles
                Arguments.of(archaea90110AllType.toString() + "/CTFiles",
                        List.of("/DBNFilesNH/", "/DBNFiles/", "/FastaFiles/", "/CTFiles/"),
                        List.of(".dbn", ".dbn", ".fasta", ".ct"),
                        List.of(RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA, RNAFormat.CT))
        );
    }


    private void translateToDB(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        //System.out.println("translated: "+RNAFileTranslator.translateToDB(rnaFile));
        //System.out.println("expected: "+ RNAFileTranslator.translateToDB(expectedRNAFile));
        var fRNAFile = RNAFileTranslator.translateToDB(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToDB(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }


    private void translateToDBNoSequence(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToDBNoSequence(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToDBNoSequence(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private void translateToBPSEQ(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToBPSEQ(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToBPSEQ(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private void translateToCT(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToCT(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToCT(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private void translateToAAS(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToAAS(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToAAS(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private void translateToAASNoSequence(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToAASNoSequence(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToAASNoSequence(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private void translateToFASTA(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToFASTA(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToFASTA(expectedRNAFile);
        assertEquals(fRNAFile, fExpectedRNAFile);
    }

    private RNAFile readFile(String filePath) throws IOException {
        CharStream input = CharStreams.fromFileName(filePath);
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
        RNAFileConstructor constructor = new RNAFileConstructor();
        // Walk the tree created during the parse, trigger callbacks
        walker.walk(constructor, tree);
        return constructor.getRnaFile();
    }

    private String getFileNameWithoutExtension(Path filePath) {
        return filePath.getFileName().toString().substring(0, filePath.getFileName().toString().lastIndexOf('.'));
    }

}