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

    private static Path archaea90110AllType, datasetFunctional, moleculesPseudoknotfree;


    @BeforeAll
    static void initializePath() throws URISyntaxException {
        archaea90110AllType = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/Archaea-90-110-allType")).toURI());
        datasetFunctional = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/DatasetFunctional")).toURI());
        moleculesPseudoknotfree = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/Molecules-pseudoknotfree")).toURI());
    }

    @ParameterizedTest
    @MethodSource({"benchmarkArchaea90110AllTypeFormatFolders", "benchmarkDatasetFunctional", "benchmarkMoleculesPseudoknotfree"})
    public void translateFormatToAll(Path srcDataset, String srcFolder, List<String> expectedFolder, List<String> expectedExtension, List<RNAFormat> rnaFormat) throws IOException {
        Files.walk(Paths.get(srcFolder)).filter(Files::isRegularFile).forEach(
                f -> {
                    try {
                        RNAFile toTranslate = this.readFile(f.toString());
                        for (int i = 0; i < expectedFolder.size(); i++) {
                            RNAFile expected = this.readFile(srcDataset.toString() + expectedFolder.get(i) +
                                    this.getFileNameWithoutExtension(f.getFileName()) + expectedExtension.get(i));
                            //System.out.println("actual: "+toTranslate.getFileName());
                            //System.out.println("expected: "+expected.getFileName());
                            switch (rnaFormat.get(i)) {
                                case AAS -> this.translateToAAS(toTranslate, expected);
                                case AAS_NO_SEQUENCE -> this.translateToAASNoSequence(toTranslate, expected);
                                case BPSEQ -> this.translateToBPSEQ(toTranslate, expected);
                                case CT -> this.translateToCT(toTranslate, expected);
                                case DB -> this.translateToDB(toTranslate, expected);
                                case DB_NO_SEQUENCE -> this.translateToDBNoSequence(toTranslate, expected);
                                case FASTA -> this.translateToFASTA(toTranslate, expected);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    private static Stream<Arguments> benchmarkArchaea90110AllTypeFormatFolders() {
        return Stream.of(
                // BPSSeqFiles
//                Arguments.of(archaea90110AllType, archaea90110AllType + "/BPSeqFiles",
//                        List.of("/CTFiles/", "/CTFilesNH/", "/DBNFiles/", "/DBNFilesNH/", "/FastaFiles/"),
//                        List.of(".ct", ".ct", ".dbn", ".dbn", ".fasta"),
//                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA)),
//                // CTFiles
//                Arguments.of(archaea90110AllType, archaea90110AllType + "/CTFiles",
//                        List.of("/BPSeqFiles/", "/DBNFiles/", "/DBNFilesNH/", "/FastaFiles/"),
//                        List.of(".bpseq", ".dbn", ".dbn", ".fasta"),
//                        List.of(RNAFormat.BPSEQ, RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA)),
//                // DBFiles
//                Arguments.of(archaea90110AllType, archaea90110AllType + "/DBNFiles",
//                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/FastaFiles/"),
//                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
//                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA)),
//                // DBFilesNH
//                Arguments.of(archaea90110AllType, archaea90110AllType + "/DBNFilesNH",
//                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/FastaFiles/"),
//                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
//                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA)),
//                // FastaFiles
//                Arguments.of(archaea90110AllType, archaea90110AllType + "/FastaFiles",
//                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/DBNFiles/", "/DBNFilesNH/"),
//                        List.of(".bpseq", ".ct", ".ct", ".dbn", "dbn"),
//                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB))
        );
    }

    private static Stream<Arguments> benchmarkDatasetFunctional() {
        return Stream.of(
                // BPSSeqFiles
                /*Arguments.of(datasetFunctional, datasetFunctional + "/BPSeqFiles",
                        List.of("/CTFiles/", "/CTFilesNoHeader/", "/DBNFiles/", "/DBNFilesNoHeader/"),
                        List.of(".ct", ".ct", ".dbn", ".dbn"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB))*/
                // CTFiles
                /*Arguments.of(archaea90110AllType + "/CTFiles",
                        List.of("/BPSeqFiles/", "/DBNFiles/", "/DBNFilesNH/", "/FastaFiles/"),
                        List.of(".bpseq", ".dbn", ".dbn", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA)),*/
                // DBFiles
                /*Arguments.of(archaea90110AllType + "/DBNFiles",
                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/FastaFiles/"),
                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA)),*/
                // DBFilesNH
                /*Arguments.of(archaea90110AllType + "/DBNFilesNH",
                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/FastaFiles/"),
                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA)),*/
                // FastaFiles
               /* Arguments.of(archaea90110AllType + "/FastaFiles",
                        List.of("/BPSeqFiles/", "/CTFiles/", "/CTFilesNH/", "/DBNFiles/", "/DBNFilesNH/"),
                        List.of(".bpseq", ".ct", ".ct", ".dbn", "dbn"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB))*/
        );
    }

    private static Stream<Arguments> benchmarkMoleculesPseudoknotfree() {
        // BPSSeqFiles
        return Stream.of(
                // bpseq Archaea5S
                Arguments.of(moleculesPseudoknotfree, moleculesPseudoknotfree + "/bpseq/Archaea/5S",
                        List.of("/ct/Archaea/5S/", "/ct-nH/Archaea/5S/", "/db/Archaea/5S/"),
                        List.of(".ct", ".ct", ".db"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB)),
                // ct Archaea5S
                Arguments.of(moleculesPseudoknotfree, moleculesPseudoknotfree + "/ct/Archaea/5S",
                        List.of("/bpseq/Archaea/5S/"/*, "/ct-nH/Archaea/5S/"*/, "/db/Archaea/5S/"),
                        List.of(".bpseq", /*".ct",*/ ".db"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.DB)),
                // ct-nH Archaea5S
                Arguments.of(moleculesPseudoknotfree, moleculesPseudoknotfree + "/ct-nH/Archaea/5S",
                        List.of("/bpseq/Archaea/5S/", /*"/ct/Archaea/5S/",*/ "/db/Archaea/5S/"),
                        List.of(".bpseq", /*".ct",*/ ".db"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.DB)),
                // db Archaea5S
                Arguments.of(moleculesPseudoknotfree, moleculesPseudoknotfree + "/db/Archaea/5S",
                        List.of("/bpseq/Archaea/5S/", "/ct/Archaea/5S/", "/ct-nH/Archaea/5S/"),
                        List.of(".bpseq", ".ct", ".ct"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT))
        );
    }

    private void translateToDB(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        //System.out.println("translated: "+RNAFileTranslator.translateToDB(rnaFile));
        //System.out.println("expected: "+ RNAFileTranslator.translateToDB(expectedRNAFile));
        var fRNAFile = RNAFileTranslator.translateToDB(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToDB(expectedRNAFile);
        System.out.println("E: "+fExpectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);
    }


    private void translateToDBNoSequence(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToDBNoSequence(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToDBNoSequence(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);
    }

    private void translateToBPSEQ(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToBPSEQ(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToBPSEQ(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);
    }

    private void translateToCT(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        System.out.println("actual: " + rnaFile.getFileName());
        System.out.println("expected: " + expectedRNAFile.getFileName());
        var fRNAFile = RNAFileTranslator.translateToCT(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToCT(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);

    }

    private void translateToAAS(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToAAS(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToAAS(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);

    }

    private void translateToAASNoSequence(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToAASNoSequence(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToAASNoSequence(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);

    }

    private void translateToFASTA(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        var fRNAFile = RNAFileTranslator.translateToFASTA(rnaFile);
        var fExpectedRNAFile = RNAFileTranslator.translateToFASTA(expectedRNAFile);
        assertEquals(fExpectedRNAFile, fRNAFile);

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
        RNAFileConstructor constructor = new RNAFileConstructor(filePath);
        // Walk the tree created during the parse, trigger callbacks
        walker.walk(constructor, tree);
        return constructor.getRnaFile();
    }

    private String getFileNameWithoutExtension(Path filePath) {
        return filePath.getFileName().toString().substring(0, filePath.getFileName().toString().lastIndexOf('.'));
    }

}