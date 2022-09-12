package it.unicam.cs.twopier.tarnas.model.translator;

import it.unicam.cs.twopier.tarnas.model.rnafile.FormattedRNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFiles;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFormatType;

import java.util.ArrayList;

public class BPSEQTranslator implements RNAFormatTranslator {


    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createDBBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body, RNAFormatType.DB);
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
        throw new NoSupportedTranslationException(RNAFormatType.BPSEQ.toString());
    }

    @Override
    public FormattedRNAFile translateToCT(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createCTBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.CT);
    }

    @Override
    public FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createAASBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.AAS);
    }

    @Override
    public FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createAASNoSequenceBody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.AAS_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        // add '# before each line of the header'
        var header = new ArrayList<>(rnaFile.header().stream().map(l -> "#" + l).toList());
        // fill the body
        var body = RNAFiles.createFASTABody(rnaFile.structure());
        // create and return the formatted file as object
        return new FormattedRNAFile(header,body,RNAFormatType.FASTA);
    }
}
