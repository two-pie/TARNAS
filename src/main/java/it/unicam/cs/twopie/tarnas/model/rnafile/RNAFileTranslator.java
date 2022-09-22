package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an RNA files translator.<br>
 * This class consists exclusively of static methods that operate on RNA secondary structure formats translations.<br>
 * Every static method has only a {@link RNAFile} parameter and translate it to the {@link RNAFormat} destination.<br>
 * The output of every static method is a {@link FormattedRNAFile}.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see RNAFormat
 * @see FormattedRNAFile
 */
public class RNAFileTranslator {

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#DB} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#DB} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#DB} format
     */
    public static FormattedRNAFile translateToDB(RNAFile rnaFile) {
        // create DB header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create DB body
        var body = createDBBody(rnaFile.getStructure(), true);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.DB);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#DB_NO_SEQUENCE} format
     */
    public static FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        // create DB no sequence header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create DB no sequence body
        var body = createDBBody(rnaFile.getStructure(), false);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.DB_NO_SEQUENCE);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#BPSEQ} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#BPSEQ} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#BPSEQ} format
     */
    public static FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        // create BPSEQ header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        //create BPSEQ body
        // create BPSEQ body
        var body = createBPSEQBody(rnaFile.getStructure());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.BPSEQ);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#CT} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#CT} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#CT} format
     */
    public static FormattedRNAFile translateToCT(RNAFile rnaFile) {
        // create CT header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create CT body
        var body = createCTBody(rnaFile.getStructure());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.CT);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#AAS} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#AAS} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#AAS} format
     */
    public static FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        // create AAS header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create AAS body
        var body = createAASBody(rnaFile.getStructure(), true);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.AAS);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#AAS_NO_SEQUENCE} format
     */
    public static FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        // create ASS no sequence header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create ASS no sequence body
        var body = createAASBody(rnaFile.getStructure(), false);
        // return a formatted rna file object
        return new FormattedRNAFile(header, List.of(body.toString()), RNAFormat.AAS_NO_SEQUENCE);
    }

    /**
     * Translates an {@link RNAFile} of any {@link RNAFormat} to a {@link FormattedRNAFile} with {@link RNAFormat#FASTA} format.
     *
     * @param rnaFile the {@code RNAFile} to translate in a {@code FormattedRNAFile} with {@link RNAFormat#FASTA} format.
     * @return the translated {@code RNAFile} in a {@code FormattedRNAFile} with {@link RNAFormat#FASTA} format
     */
    public static FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        var rnaSecondaryStructure = rnaFile.getStructure();
        // create FASTA header
        var header = createHeader(rnaFile.getHeader(), rnaFile.getFormat());
        // create FASTA body
        var body = List.of(rnaFile.getStructure().getSequence());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.FASTA);
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
        var p = rnaSecondaryStructure.getP();
        var structure = new StringBuilder();
        structure.append(".".repeat(p.length));
        for (int i = 1; i < p.length; i++)
            if (p[i] != 0 && structure.charAt(i) == '.' && structure.charAt(p[i]) == '.') {
                structure.setCharAt(i, '(');
                structure.setCharAt(p[i], ')');
            }
        structure.deleteCharAt(0);
        return addSequence ?
                List.of(rnaSecondaryStructure.getSequence(), structure.toString()) :
                List.of(structure.toString());
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
                    rnaSecondaryStructure.getP()[i] + "\n";
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
}
