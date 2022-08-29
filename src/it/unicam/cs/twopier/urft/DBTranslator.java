package it.unicam.cs.twopier.urft;

import java.util.ArrayList;
import java.util.List;

public class DBTranslator implements RNAFormatTranslator {

    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        throw new NoSupportedTranslationException("Cannot convert to DB");
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        return null;
    }

    @Override
    public FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        if (!rnaFile.formatType().equals(RNAFormatType.BPSEQ))
            throw new IllegalArgumentException("Wrong format: " + rnaFile.formatType());
        // clean and add header
        List<String> header = rnaFile.header()
                .stream()
                .map(l -> l.substring(1)).toList();
        // read db structure and obtain pairing index
        List<String> body = new ArrayList<>();
        for (int i = 0; i < rnaFile.structure().getSequence().length(); i++) {
            String line = (i + 1) + " " +
                    rnaFile.structure().getSequence().charAt(i) + " " +
                    rnaFile.structure().getP()[i] + "\n";
            body.add(line);
        }
        return new FormattedRNAFile(header, body);
    }

    @Override
    public FormattedRNAFile translateToCT(RNAFile rnaFile) {
        return null;
    }

    @Override
    public FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        return null;
    }

    @Override
    public FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        return null;
    }

    @Override
    public FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        return null;
    }

}
