package it.unicam.cs.twopier.urft;

import java.util.ArrayList;

public class BpseqTranslator implements RNAFormatTranslator {


    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createDBBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.DB);
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createDBNoSequenceBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.DB_NO_SEQUENCE);
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
