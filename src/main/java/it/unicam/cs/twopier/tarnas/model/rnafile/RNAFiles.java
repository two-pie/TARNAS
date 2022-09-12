package it.unicam.cs.twopier.tarnas.model.rnafile;

import it.unicam.cs.twopier.tarnas.model.RNASecondaryStructure;
import it.unicam.cs.twopier.tarnas.model.translator.NoSupportedTranslationException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RNAFiles {

    /**
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createDBBody(RNASecondaryStructure rnaSecondaryStructure) {
        var body = new ArrayList<String>();
        body.add(rnaSecondaryStructure.getSequence());
        body.add(getDBBodyOf(rnaSecondaryStructure.getSize(), rnaSecondaryStructure.getP()));
        return body;
    }

    /**
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createDBNoSequenceBody(RNASecondaryStructure rnaSecondaryStructure) {
        // read db no sequence structure and obtain only structure
        return List.of(getDBBodyOf(rnaSecondaryStructure.getSize(), rnaSecondaryStructure.getP()));
    }

    /**
     * @param size
     * @param p
     * @return
     */
    private static String getDBBodyOf(int size, int[] p) {
        StringBuilder sb = new StringBuilder();
        sb.append(".".repeat(size - 1));
        for (int i = 1; i <= size; i++)
            if (p[i] != 0 && sb.charAt(i) == '.' && sb.charAt(p[i]) == '.') {
                sb.setCharAt(i, '(');
                sb.setCharAt(p[i], ')');
            }
        return sb.toString();
    }

    /**
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createBPSEQBody(RNASecondaryStructure rnaSecondaryStructure) {
        // read db structure and obtain pairing index
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
    public static List<String> createCTBody(RNASecondaryStructure rnaSecondaryStructure) {
        if (rnaSecondaryStructure.getSequence().isEmpty())
            throw new NoSupportedTranslationException("Cannot translate to CT");
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
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createAASBody(RNASecondaryStructure rnaSecondaryStructure) {
        var body = new ArrayList<String>();
        body.add(rnaSecondaryStructure.getSequence());
        body.addAll(createAASNoSequenceBody(rnaSecondaryStructure));
        return body;
    }

    /**
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createAASNoSequenceBody(RNASecondaryStructure rnaSecondaryStructure) {
        StringBuilder structure = new StringBuilder();
        for (var b : rnaSecondaryStructure.getBonds())
            structure.append("(")
                    .append(b.getLeft())
                    .append(",")
                    .append(b.getRight())
                    .append(");");
        // remove the character ';'
        if (structure.length() > 0 && structure.charAt(structure.length() - 1) == ';')
            structure.setLength(structure.length() - 1);
        return List.of(structure.toString());
    }

    /**
     * @param rnaSecondaryStructure
     * @return
     */
    public static List<String> createFASTABody(RNASecondaryStructure rnaSecondaryStructure) {
        return List.of(rnaSecondaryStructure.getSequence());
    }
}
