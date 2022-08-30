package it.unicam.cs.twopier.urft;

import java.util.ArrayList;

public class BpseqTranslator implements RNAFormatTranslator {


    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        if (rnaFile.formatType() != RNAFormatType.BPSEQ)
            throw new IllegalArgumentException();
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createDBBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body);
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        if (rnaFile.formatType() != RNAFormatType.BPSEQ)
            throw new IllegalArgumentException();
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        var body = new ArrayList<String>();
        // fill the body
        body.add(rnaFile.structure().getStructure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body);
    }

    @Override
    public FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        return null;
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
