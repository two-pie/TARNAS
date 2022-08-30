package it.unicam.cs.twopier.urft;

import java.util.List;

public class RNAFiles {

    static List<String> createDBBody(RNASecondaryStructure rnaSecondaryStructure) {

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

    }

    static List<String> createCTBody(RNASecondaryStructure rnaSecondaryStructure) {
    }

    static List<String> createAADBody(RNASecondaryStructure rnaSecondaryStructure) {
    }

    static List<String> createAASNoSequenceBody(RNASecondaryStructure rnaSecondaryStructure) {
    }

    static List<String> createFASTABody(RNASecondaryStructure rnaSecondaryStructure) {
    }
}
