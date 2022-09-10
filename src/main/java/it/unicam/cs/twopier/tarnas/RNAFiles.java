package it.unicam.cs.twopier.tarnas;

import java.util.ArrayList;
import java.util.List;

public class RNAFiles {

    static List<String> createDBBody(RNASecondaryStructure rnaSecondaryStructure) {
        var body = new ArrayList<String>();
        body.add(rnaSecondaryStructure.getSequence());
        body.add(getDBBodyOf(rnaSecondaryStructure.size, rnaSecondaryStructure.p));
        return body;
    }

    static List<String> createDBNoSequenceBody(RNASecondaryStructure rnaSecondaryStructure) {
        // read db no sequence structure and obtain only structure
        return List.of(getDBBodyOf(rnaSecondaryStructure.size, rnaSecondaryStructure.p));
    }

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

    static List<String> createBPSEQBody(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    static List<String> createCTBody(RNASecondaryStructure rnaSecondaryStructure) {
        if (rnaSecondaryStructure.getSequence().isEmpty())
            throw new NoSupportedTranslationException("Cannot translate to CT");
        var body = new ArrayList<String>();
        var rnaSequence = rnaSecondaryStructure.getSequence();
        var p = rnaSecondaryStructure.getP();
        for (int i = 1; i <= rnaSecondaryStructure.size; i++) {
            var line = i + " " + rnaSequence.charAt(i - 1) + " " + (i - 1) + " " + (i + 1) + " " + p[i] + " " + i;
            body.add(line);
        }
        return body;
    }

    static List<String> createAADBody(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    static List<String> createAASNoSequenceBody(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    static List<String> createFASTABody(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }
}
