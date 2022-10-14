package it.unicam.cs.twopie.tarnas.model.rnafile;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RNAFileTranslatorTest {

    private static Path archaea90110AllType, datasetFunctional, benchmark5S16S23S, aspralign;
    private final static String fs = File.separator;

    @BeforeAll
    static void initializePath() throws URISyntaxException {
        archaea90110AllType = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/Archaea-90-110-allType")).toURI());
        datasetFunctional = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/DatasetFunctional")).toURI());
        benchmark5S16S23S = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/Benchmark-5S-16S-23S")).toURI());
        aspralign = Paths.get(Objects.requireNonNull(RNAFileTranslatorTest.class.getResource("/datasets/aspralignTest")).toURI());
    }

    @ParameterizedTest
    @MethodSource({"benchmarkArchaea90110AllTypeFormatFolders", "benchmarkDatasetFunctional", "moleculesPseudoknotfreeArchaea5S", "moleculesPseudoknotfreeBacteria5S", "moleculesPseudoknotfreeEukaryota5S", "moleculesArchaea16S", "moleculesBacteria16S", "moleculesEukaryota16S", "moleculesArchaea23S", "moleculesBacteria23S", "moleculesEukaryota23S", "benchmarkAspralign"})
    public void translateFormatToAll(Path srcDataset, String srcFolder, List<String> expectedFolder, List<String> expectedExtension, List<RNAFormat> rnaFormat) throws IOException {
        Files.walk(Paths.get(srcFolder)).parallel().filter(Files::isRegularFile).forEach(
                f -> {
                    try {
                        RNAFile toTranslate = RNAFileConstructor.getInstance().construct(f);
                        for (int i = 0; i < expectedFolder.size(); i++) {
                            String tmp = srcDataset.toString() + expectedFolder.get(i) +
                                    this.getFileNameWithoutExtension(f.getFileName()) + expectedExtension.get(i);
                            if (Files.exists(Paths.get(tmp))) {
                                RNAFile expected = RNAFileConstructor.getInstance().construct(Paths.get(tmp));
                                // header can not be the same in all files...
                                toTranslate = new RNAFile(toTranslate.getFileName(), new ArrayList<>(), toTranslate.getBody(), toTranslate.getStructure(), toTranslate.getFormat());
                                expected = new RNAFile(expected.getFileName(), new ArrayList<>(), expected.getBody(), expected.getStructure(), expected.getFormat());
                                // with pseudoktnots comparing only weakbonds
                                if (toTranslate.getStructure().isPseudoknotted() || expected.getStructure().isPseudoknotted()) {
                                    toTranslate = new RNAFile(toTranslate.getFileName(), new ArrayList<>(), new ArrayList<>(), toTranslate.getStructure(), toTranslate.getFormat());
                                    expected = new RNAFile(expected.getFileName(), new ArrayList<>(), new ArrayList<>(), expected.getStructure(), expected.getFormat());
                                }
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
                Arguments.of(archaea90110AllType, archaea90110AllType + fs + "BPSeqFiles",
                        List.of(fs + "CTFiles" + fs, fs + "CTFilesNH" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNH" + fs, fs + "FastaFiles" + fs),
                        List.of(".ct", ".ct", ".db", ".db", ".fasta"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA)),
                // CTFiles
                Arguments.of(archaea90110AllType, archaea90110AllType + fs + "CTFiles",
                        List.of(fs + "BPSeqFiles" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNH" + fs, fs + "FastaFiles" + fs),
                        List.of(".bpseq", ".db", ".db", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.DB, RNAFormat.DB, RNAFormat.FASTA)),
                // DBFiles
                Arguments.of(archaea90110AllType, archaea90110AllType + "/DBNFiles",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNH" + fs, fs + "FastaFiles" + fs),
                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA)),
                // DBFilesNH
                Arguments.of(archaea90110AllType, archaea90110AllType + fs + "DBNFilesNH",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNH" + fs, fs + "FastaFiles" + fs),
                        List.of(".bpseq", ".ct", ".ct", ".fasta"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.FASTA))//,
                // FastaFiles
                // Fasta cannot be translated to others format
                /*Arguments.of(archaea90110AllType, archaea90110AllType + fs + "FastaFiles",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNH" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNH" + fs),
                        List.of(".bpseq", ".ct", ".ct", ".dbn", "dbn"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB))*/
        );
    }

    private static Stream<Arguments> benchmarkDatasetFunctional() {
        return Stream.of(
                // BPSSeqFiles
                Arguments.of(datasetFunctional, datasetFunctional + fs + "BPSeqFiles",
                        List.of(fs + "CTFiles" + fs, fs + "CTFilesNoHeader" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNoHeader" + fs),
                        List.of(".ct", ".ct", ".db", ".db"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB)),
                Arguments.of(datasetFunctional, datasetFunctional + fs + "BPSeqFiles",
                        List.of(fs + "CTFiles" + fs, fs + "CTFilesNoHeader" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNoHeader" + fs),
                        List.of(".ct", ".ct", ".db", ".db"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB)),
                // CTFiles
                Arguments.of(datasetFunctional, datasetFunctional + fs + "CTFiles",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNoHeader" + fs, fs + "DBNFiles" + fs, fs + "DBNFilesNoHeader" + fs),
                        List.of(".bpseq", ".ct", ".ct", ".db", ".db"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB)),
                // DBNFiles
                Arguments.of(datasetFunctional, datasetFunctional + fs + "DBNFiles",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNoHeader" + fs),
                        List.of(".bpseq", ".ct", ".ct"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT)),
                // DBNFilesNoHeader
                Arguments.of(datasetFunctional, datasetFunctional + fs + "DBNFilesNoHeader",
                        List.of(fs + "BPSeqFiles" + fs, fs + "CTFiles" + fs, fs + "CTFilesNoHeader" + fs),
                        List.of(".bpseq", ".ct", ".ct"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT))
        );
    }

    private static Stream<Arguments> benchmarkMoleculesPseudoknotfree(String domain, String sClass) {
        return Stream.of(
                // bpseq domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "bpseq" + fs + domain + fs + sClass,
                        List.of(fs + "ct" + fs + domain + fs + sClass + fs, fs + "ct-nH" + fs + domain + fs + sClass + fs, fs + "db" + fs + domain + fs + sClass + fs, fs + "db-nH" + fs + domain + fs + sClass + fs),
                        List.of(".ct", ".ct", ".db", ".db"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB)),
                // bpseq-nH domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "bpseq-nH" + fs + domain + fs + sClass,
                        List.of(fs + "ct" + fs + domain + fs + sClass + fs, fs + "ct-nH" + fs + domain + fs + sClass + fs, fs + "db" + fs + domain + fs + sClass + fs, fs + "db-nH" + fs + domain + fs + sClass + fs),
                        List.of(".ct", ".ct", ".db", ".db"),
                        List.of(RNAFormat.CT, RNAFormat.CT, RNAFormat.DB, RNAFormat.DB)),
                // ct domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "ct" + fs + domain + fs + sClass,
                        List.of(fs + "bpseq" + fs + domain + fs + sClass + fs, fs + "bpseq-nH" + fs + domain + fs + sClass + fs, fs + "db" + fs + domain + fs + sClass + fs, fs + "db-nH" + fs + domain + fs + sClass + fs),
                        List.of(".bpseq", ".bpseq", ".db", ".db"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.BPSEQ, RNAFormat.DB, RNAFormat.DB)),
                // ct-nH domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "ct-nH" + fs + domain + fs + sClass,
                        List.of(fs + "bpseq" + fs + domain + fs + sClass + fs, fs + "bpseq-nH" + fs + domain + fs + sClass + fs, fs + "db" + fs + domain + fs + sClass + fs, fs + "db-nH" + fs + domain + fs + sClass + fs),
                        List.of(".bpseq", ".bpseq", ".db", ".db"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.BPSEQ, RNAFormat.DB, RNAFormat.DB)),
                // db domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "db" + fs + domain + fs + sClass,
                        List.of(fs + "bpseq" + fs + domain + fs + sClass + fs, fs + "bpseq-nH" + fs + domain + fs + sClass + fs, fs + "ct" + fs + domain + fs + sClass + fs, fs + "ct-nH" + fs + domain + fs + sClass + fs),
                        List.of(".bpseq", ".bpseq", ".ct", ".ct"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT)),
                // db-nH domain
                Arguments.of(benchmark5S16S23S, benchmark5S16S23S + fs + "db-nH" + fs + domain + fs + sClass,
                        List.of(fs + "bpseq" + fs + domain + fs + sClass + fs, fs + "bpseq-nH" + fs + domain + fs + sClass + fs, fs + "ct" + fs + domain + fs + sClass + fs, fs + "ct-nH" + fs + domain + fs + sClass + fs),
                        List.of(".bpseq", ".bpseq", ".ct", ".ct"),
                        List.of(RNAFormat.BPSEQ, RNAFormat.BPSEQ, RNAFormat.CT, RNAFormat.CT))
        );
    }

    private static Stream<Arguments> moleculesPseudoknotfreeArchaea5S() {
        return benchmarkMoleculesPseudoknotfree("Archaea", "5S");
    }

    private static Stream<Arguments> moleculesPseudoknotfreeBacteria5S() {
        return benchmarkMoleculesPseudoknotfree("Bacteria", "5S");
    }

    private static Stream<Arguments> moleculesPseudoknotfreeEukaryota5S() {
        return benchmarkMoleculesPseudoknotfree("Eukaryota", "5S");
    }

    private static Stream<Arguments> moleculesArchaea16S() {
        return benchmarkMoleculesPseudoknotfree("Archaea", "16S");
    }

    private static Stream<Arguments> moleculesBacteria16S() {
        return benchmarkMoleculesPseudoknotfree("Bacteria", "16S");
    }

    private static Stream<Arguments> moleculesEukaryota16S() {
        return benchmarkMoleculesPseudoknotfree("Eukaryota", "16S");
    }

    private static Stream<Arguments> moleculesArchaea23S() {
        return benchmarkMoleculesPseudoknotfree("Archaea", "23S");
    }

    private static Stream<Arguments> moleculesBacteria23S() {
        return benchmarkMoleculesPseudoknotfree("Bacteria", "23S");
    }

    private static Stream<Arguments> moleculesEukaryota23S() {
        return benchmarkMoleculesPseudoknotfree("Eukaryota", "23S");
    }

    private static Stream<Arguments> benchmarkAspralign() {
        return Stream.of(
                // aas
                Arguments.of(aspralign, aspralign + fs + "aas",
                        List.of(fs + "aas-no-sequence" + fs, fs + "edbn" + fs, fs + "edbn-no-sequence" + fs),
                        List.of(".txt", ".dp", ".dp"),
                        List.of(RNAFormat.AAS_NO_SEQUENCE, RNAFormat.DB, RNAFormat.DB_NO_SEQUENCE)),
                // aas-no-sequence
                Arguments.of(aspralign, aspralign + fs + "aas-no-sequence",
                        List.of(fs + "edbn-no-sequence" + fs),
                        List.of(".dp"),
                        List.of(RNAFormat.DB_NO_SEQUENCE)),
                // edbn
                Arguments.of(aspralign, aspralign + fs + "edbn",
                        List.of(fs + "aas" + fs, fs + "aas-no-sequence" + fs, fs + "edbn-no-sequence" + fs),
                        List.of(".txt", ".txt", ".dp"),
                        List.of(RNAFormat.AAS, RNAFormat.AAS_NO_SEQUENCE, RNAFormat.DB_NO_SEQUENCE)),
                // edbn-no-sequence
                Arguments.of(aspralign, aspralign + fs + "edbn-no-sequence",
                        List.of(fs + "aas-no-sequence" + fs),
                        List.of(".dp"),
                        List.of(RNAFormat.DB_NO_SEQUENCE))
        );
    }


    private void translateToDB(RNAFile rnaFile, RNAFile expectedRNAFile) throws IOException {
        //System.out.println("INSIDE\nr: " + rnaFile + "\nt: " + expectedRNAFile);
        var fRNAFile = RNAFileTranslator.translateToDB(rnaFile);
        //System.out.println("translated: " + fRNAFile);
        //var fExpectedRNAFile = RNAFileTranslator.translateToDB(expectedRNAFile);
        //System.out.println("\ntranslated: " + fExpectedRNAFile);
        assertEquals(expectedRNAFile, fRNAFile);
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
        //System.out.println("actual: " + rnaFile.getFileName());
        //System.out.println("expected: " + expectedRNAFile.getFileName());
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

    private String getFileNameWithoutExtension(Path filePath) {
        return filePath.getFileName().toString().substring(0, filePath.getFileName().toString().lastIndexOf('.'));
    }

}