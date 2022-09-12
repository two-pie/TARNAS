package it.unicam.cs.twopier.tarnas.model.translator;


import it.unicam.cs.twopier.tarnas.model.rnafile.FormattedRNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFiles;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFormatType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DBTranslator implements RNAFormatTranslator {

    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        return null;
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        return new FormattedRNAFile(rnaFile.header(), RNAFiles.createDBNoSequenceBody(rnaFile.structure()), RNAFormatType.DB_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        // clean and add header
        var header = rnaFile.header()
                .stream()
                .map(l -> l.substring(1)).toList();
        return new FormattedRNAFile(header, RNAFiles.createBPSEQBody(rnaFile.structure()), RNAFormatType.BPSEQ);
    }

    @Override
    public FormattedRNAFile translateToCT(RNAFile rnaFile) {
        // create default CT header
        var header = List.of(rnaFile.structure().getSequence().length() + "   dG =     0.00  [initially     0.0]");
        return new FormattedRNAFile(header, RNAFiles.createCTBody(rnaFile.structure()), RNAFormatType.CT);
    }

    @Override
    public FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createAASBody(rnaFile.structure()), RNAFormatType.AAS);
    }

    @Override
    public FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createAASNoSequenceBody(rnaFile.structure()), RNAFormatType.AAS_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createFASTABody(rnaFile.structure()), RNAFormatType.FASTA);
    }

}
