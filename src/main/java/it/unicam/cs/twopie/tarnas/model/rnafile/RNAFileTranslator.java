package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;
import it.unicam.cs.twopie.tarnas.model.utils.Region;

import java.util.*;

/**
 * A representation of an RNA files translator.<br>
 * This class consists exclusively of static methods that operate on RNA secondary structure formats translations.<br>
 * Every static method has only a {@link RNAFile} parameter and translate it to the {@link RNAFormat} destination.<br>
 * The output of every static method is a {@link RNAFile}.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see RNAFormat
 */
public class RNAFileTranslator {

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#DB} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#DB} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#DB} format
     */
    public static RNAFile translateToDB(RNAFile rnaFile) {
        // create DB header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.DB);
        // create DB body
        var body = createDBBody(rnaFile.getStructure(), true);
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "db"), header, body, rnaFile.getStructure(), RNAFormat.DB);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format
     */
    public static RNAFile translateToDBNoSequence(RNAFile rnaFile) {
        // create DB no sequence header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.DB_NO_SEQUENCE);
        // create DB no sequence body
        var body = createDBBody(rnaFile.getStructure(), false);
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "db"), header, body, rnaFile.getStructure(), RNAFormat.DB_NO_SEQUENCE);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#BPSEQ} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#BPSEQ} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#BPSEQ} format
     */
    public static RNAFile translateToBPSEQ(RNAFile rnaFile) {
        // create BPSEQ header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.BPSEQ);
        //create BPSEQ body
        // create BPSEQ body
        var body = createBPSEQBody(rnaFile.getStructure());
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "bpseq"), header, body, rnaFile.getStructure(), RNAFormat.BPSEQ);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#CT} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#CT} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#CT} format
     */
    public static RNAFile translateToCT(RNAFile rnaFile) {
        // create CT header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.CT);
        // create CT body
        var body = createCTBody(rnaFile.getStructure());
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "ct"), header, body, rnaFile.getStructure(), RNAFormat.CT);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#AAS} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#AAS} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#AAS} format
     */
    public static RNAFile translateToAAS(RNAFile rnaFile) {
        // create AAS header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.AAS);
        // create AAS body
        var body = createAASBody(rnaFile.getStructure(), true);
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "aas"), header, body, rnaFile.getStructure(), RNAFormat.AAS);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format
     */
    public static RNAFile translateToAASNoSequence(RNAFile rnaFile) {
        // create ASS no sequence header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.AAS_NO_SEQUENCE);
        // create ASS no sequence body
        var body = createAASBody(rnaFile.getStructure(), false);
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "aas"), header, body, rnaFile.getStructure(), RNAFormat.AAS_NO_SEQUENCE);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link RNAFile} with {@link RNAFormat#FASTA} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#FASTA} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#FASTA} format
     */
    public static RNAFile translateToFASTA(RNAFile rnaFile) {
        var rnaSecondaryStructure = rnaFile.getStructure();
        // create FASTA header
        var header = createHeader(rnaFile.getHeader(), RNAFormat.FASTA);
        // create FASTA body
        var body = List.of(rnaFile.getStructure().getSequence());
        // return a formatted rna file object
        return new RNAFile(getFileNameWithDstExtension(rnaFile.getFileName(), "fasta"), header, body, rnaFile.getStructure(), RNAFormat.FASTA);
    }

    /**
     * Creates the {@link RNAFormat#DB} body for the specified {@code rnaSecondaryStructure}.
     * If {@code addSequence} parameter is true, adds the sequence to the body.
     *
     * @param rnaSecondaryStructure the {@link RNASecondaryStructure} from which create the {@link RNAFormat#DB} body.
     * @param addSequence           boolean flag to add the sequence to the {@link RNAFormat#DB} body
     * @return the {@link RNAFormat#DB} body as a {@link List} of {@code String}
     */
    private static List<String> createDBBody(RNASecondaryStructure rnaSecondaryStructure, boolean addSequence) {
        var regs = findAllPairedRegions(rnaSecondaryStructure);
        var n = regs.size();
        regs = sortRegionsByStartPoint(regs);
        setRegionsOrder(regs, n);
        var structure = encodeBasePairs(regs, rnaSecondaryStructure.getSize());
        return addSequence ?
                List.of(rnaSecondaryStructure.getSequence(), structure) :
                List.of(structure);
    }

    /**
     * Creates the {@link RNAFormat#AAS} body for the specified {@code rnaSecondaryStructure}.
     * If {@code addSequence} parameter is true, adds the sequence to the body.
     *
     * @param rnaSecondaryStructure the {@link RNASecondaryStructure} from which create the {@link RNAFormat#AAS} body.
     * @param addSequence           boolean flag to add the sequence to the {@link RNAFormat#AAS} body
     * @return the {@link RNAFormat#AAS} body as a {@link List} of {@code String}
     */
    private static List<String> createAASBody(RNASecondaryStructure rnaSecondaryStructure, boolean addSequence) {
        var structure = new StringBuilder();
        for (var b : rnaSecondaryStructure.getBonds())
            structure.append("(")
                    .append(b.getLeft())
                    .append(",")
                    .append(b.getRight())
                    .append(");");
        // remove the character ';'
        if (structure.length() > 0)
            structure.setLength(structure.length() - 1);
        return addSequence ?
                List.of(rnaSecondaryStructure.getSequence(), structure.toString()) :
                List.of(structure.toString());
    }

    /**
     * Creates the {@link RNAFormat#BPSEQ} body for the specified {@code rnaSecondaryStructure}.
     *
     * @param rnaSecondaryStructure the {@link RNASecondaryStructure} from which create the {@link RNAFormat#BPSEQ} body.
     * @return the {@link RNAFormat#BPSEQ} body as a {@link List} of {@code String}
     */
    private static List<String> createBPSEQBody(RNASecondaryStructure rnaSecondaryStructure) {
        var body = new ArrayList<String>();
        for (int i = 1; i <= rnaSecondaryStructure.getSequence().length(); i++) {
            var line = i + " " +
                    rnaSecondaryStructure.getSequence().charAt(i - 1) + " " +
                    rnaSecondaryStructure.getP()[i];
            body.add(line);
        }
        return body;
    }

    /**
     * Creates the {@link RNAFormat#CT} body for the specified {@code rnaSecondaryStructure}.
     *
     * @param rnaSecondaryStructure the {@link RNASecondaryStructure} from which create the {@link RNAFormat#CT} body.
     * @return the {@link RNAFormat#CT} body as a {@link List} of {@code String}
     */
    private static List<String> createCTBody(RNASecondaryStructure rnaSecondaryStructure) {
        var body = new ArrayList<String>();
        var rnaSequence = rnaSecondaryStructure.getSequence();
        var p = rnaSecondaryStructure.getP();
        for (int i = 1; i <= rnaSecondaryStructure.getSize(); i++) {
            var line = i + " " + rnaSequence.charAt(i - 1) + " " + (i - 1) + " " + (i + 1) + " " + p[i] + " " + i;
            body.add(line);
        }
        return body;
    }

    /**
     * Creates the header for the file with destination {@link RNAFormat}.
     * Reads every String in the {@code header} and adds the '#' symbol at the start of that header String.
     *
     * @param header            the {@link List} of String of the starting header
     * @param destinationFormat the destination {@code RNAFormat}
     * @return the commented header for the file with the destination {@code RNAFormat}
     */
    private static List<String> createHeader(List<String> header, RNAFormat destinationFormat) {
        var newHeader = new ArrayList<String>();
        for (var line : header) {
            var isNotComment = !line.startsWith("#");
            if (destinationFormat != RNAFormat.BPSEQ && destinationFormat != RNAFormat.CT && isNotComment) {
                line = "#" + line;
            }
            newHeader.add(line);
        }
        return newHeader;
    }

    private static List<Region> findAllPairedRegions(RNASecondaryStructure rnaSecondaryStructure) {
        return rnaSecondaryStructure.getBonds().stream().map(Region::new).toList();
    }

    /**
     * @param regs
     */
    private static List<Region> sortRegionsByStartPoint(List<Region> regs) {
        var tmp = new ArrayList<>(regs);
        tmp.sort(Comparator.comparingInt(r -> r.getWeakBond().getLeft()));
        return tmp;
    }

    /**
     * @param regs
     * @param n
     */
    private static void setRegionsOrder(List<Region> regs, int n) {
        if (n < 2) return;
        regs.get(0).setOrder(0);
        for (int i = 1; i <n; i++) {
            var globalOrder = 0;
            for (int j = 0; j <= i-1 ; j++) {
                if (regs.get(j).getOrder() == globalOrder && areRegionsConflicting(regs.get(i), regs.get(j)))
                    globalOrder += 1;
            }
            regs.get(i).setOrder(globalOrder);
        }
    }
    private static boolean areRegionsConflicting(Region r1, Region r2) {
        var wb1 = r1.getWeakBond();
        var wb2 = r2.getWeakBond();
        // ( [ ) ]
        var firstCase = wb1.getLeft() < wb2.getLeft() && wb1.getRight() > wb2.getLeft() && wb2.getRight() > wb1.getRight();
        // [ ( ] )
        var secondCase = wb2.getLeft() < wb1.getLeft() && wb2.getRight() > wb1.getLeft() && wb1.getRight() > wb2.getRight();
        return firstCase || secondCase;
    }

    private static String encodeBasePairs(List<Region> regs, int size) {
        var structure = new StringBuilder();
        structure.append(".".repeat(size));
        for (var r : regs) {
            structure.setCharAt(r.getWeakBond().getLeft() - 1, getOpeningBracket(r.getOrder()));
            structure.setCharAt(r.getWeakBond().getRight() - 1, getClosingBracket(r.getOrder()));
        }
        return structure.toString();
    }

    private static Character getOpeningBracket(int order) {
        return switch (order) {
            case 0 -> '(';
            case 1 -> '[';
            case 2 -> '{';
            case 3 -> '<';
            case 4 -> 'A';
            case 5 -> 'B';
            case 6 -> 'C';
            case 7 -> 'D';
            case 8 -> 'E';
            default -> throw new IllegalArgumentException("Maximum order is 8!");
        };
    }

    private static Character getClosingBracket(int order) {
        return switch (order) {
            case 0 -> ')';
            case 1 -> ']';
            case 2 -> '}';
            case 3 -> '>';
            case 4 -> 'a';
            case 5 -> 'b';
            case 6 -> 'c';
            case 7 -> 'd';
            case 8 -> 'e';
            default -> throw new IllegalArgumentException("Maximum order is 8!");
        };
    }

    private static String getFileNameWithDstExtension(String fileName, String dstExtension) {
        return fileName.substring(0, fileName.lastIndexOf('.') + 1) + dstExtension;
    }


}
