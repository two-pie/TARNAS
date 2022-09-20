package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RNAFileTranslator {

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToDB(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = createDBBody(rnaFile.structure(), true);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.DB);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = createDBBody(rnaFile.structure(), false);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.DB_NO_SEQUENCE);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        //create body
        // read db structure and obtain pairing index
        var body = createBPSEQBody(rnaFile.structure());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.BPSEQ);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToCT(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = createCTBody(rnaFile.structure());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.CT);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = createAASBody(rnaFile.structure(), true);
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.AAS);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = createAASBody(rnaFile.structure(), false);
        // return a formatted rna file object
        return new FormattedRNAFile(header, List.of(body.toString()), RNAFormat.AAS_NO_SEQUENCE);
    }

    /**
     * @param rnaFile
     * @return
     */
    public static FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        var rnaSecondaryStructure = rnaFile.structure();
        // create header
        var header = createHeader(rnaFile.header(), rnaFile.format());
        // create body
        var body = List.of(rnaFile.structure().getSequence());
        // return a formatted rna file object
        return new FormattedRNAFile(header, body, RNAFormat.FASTA);
    }

    /**
     * @param rnaSecondaryStructure
     * @param addSequence
     * @return
     */
    private static List<String> createDBBody(RNASecondaryStructure rnaSecondaryStructure, boolean addSequence) {
        var p = rnaSecondaryStructure.getP();
        var size = rnaSecondaryStructure.getSize();
        var structure = new StringBuilder();
        structure.append(".".repeat(size - 1));
        for (int i = 1; i <= size; i++)
            if (p[i] != 0 && structure.charAt(i) == '.' && structure.charAt(p[i]) == '.') {
                structure.setCharAt(i, '(');
                structure.setCharAt(p[i], ')');
            }
        return addSequence ?
                List.of(rnaSecondaryStructure.getSequence(), structure.toString()) :
                List.of(structure.toString());
    }

    /**
     * @param rnaSecondaryStructure
     * @param addSequence
     * @return
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
     * @param rnaSecondaryStructure
     * @return
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
     * @param rnaSecondaryStructure
     * @return
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
     * @param header
     * @param destinationFormat
     * @return
     */
    private static List<String> createHeader(List<String> header, RNAFormat destinationFormat) {
        var newHeader = new ArrayList<String>();
        for (var line : header) {
            var isNotComment = !line.startsWith("#");
            if (destinationFormat != RNAFormat.BPSEQ && destinationFormat != RNAFormat.CT && isNotComment ) {
                line = "#" + line;
            }
            newHeader.add(line);
        }
        return newHeader;
    }
}
