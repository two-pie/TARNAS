package it.unicam.cs.twopier.tarnas.translator;

import it.unicam.cs.twopier.tarnas.rnafile.FormattedRNAFile;
import it.unicam.cs.twopier.tarnas.rnafile.RNAFile;
import it.unicam.cs.twopier.tarnas.rnafile.RNAFiles;
import it.unicam.cs.twopier.tarnas.rnafile.RNAFormatType;

import java.util.ArrayList;

public class CTTranslator implements RNAFormatTranslator{
    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header());
        // fill the body
        var body = RNAFiles.createDBBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body, RNAFormatType.DB);
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header());
        // fill the body
        var body = RNAFiles.createDBNoSequenceBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.DB_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> l.substring(1)).toList());
        // fill the body
        var body = RNAFiles.createBPSEQBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.BPSEQ);
    }

    @Override
    public FormattedRNAFile translateToCT(RNAFile rnaFile) {
        throw new NoSupportedTranslationException(RNAFormatType.CT.toString());
    }

    @Override
    public FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header());
        // fill the body
        var body = RNAFiles.createAASBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.AAS);
    }

    @Override
    public FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header());
        // fill the body
        var body = RNAFiles.createAASNoSequenceBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.AAS_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        var header = new ArrayList<>(rnaFile.header());
        // fill the body
        var body = RNAFiles.createFASTABody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.FASTA);
    }
}
