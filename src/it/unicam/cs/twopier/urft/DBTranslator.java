package it.unicam.cs.twopier.urft;

import java.util.List;

public class DBTranslator implements FormatTranslator {

    @Override
    public RNAFile translateToDB(RNAFile rnaFile) {
        return null;
    }

    @Override
    public RNAFile translateToDBNoSequence(RNAFile rnaFile) {
        return null;
    }

    @Override
    public RNAFile translateToBPSEQ(RNAFile rnaFile) {
        if (!rnaFile.formatType().equals(FormatType.BPSEQ))
            throw new IllegalArgumentException("Wrong format: " + rnaFile.formatType());
        StringBuilder bpseq = new StringBuilder();
        // clean and add header
        List<String> headerLines = rnaFile.header()
                .stream()
                .map(l -> l.substring(1)).toList();
        for (var l : headerLines)
            bpseq.append(l).append("\n");
        // read db structure and obtain pairing index
        for (int i = 0; i < rnaFile.structure().getSequence().length(); i++) {
            bpseq.append((i + 1))
                    .append(" ")
                    .append(rnaFile.structure().getSequence().charAt(i))
                    .append(" ")
                    .append(rnaFile.structure().getP()[i])
                    .append("\n");
        }
        return bpseq.toString();
    }

    @Override
    public RNAFile translateToCT(RNAFile rnaFile) {
        return null;
    }

    @Override
    public RNAFile translateToAAS(RNAFile rnaFile) {
        return null;
    }

    @Override
    public RNAFile translateToAASNoSequence(RNAFile rnaFile) {
        return null;
    }

    @Override
    public RNAFile translateToFASTA(RNAFile rnaFile) {
        return null;
    }
}
