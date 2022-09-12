package it.unicam.cs.twopie.tarnas.model.translator;

import it.unicam.cs.twopie.tarnas.RNASecondaryStructure;
import it.unicam.cs.twopie.tarnas.model.rnafile.FormattedRNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFiles;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormatType;

import java.util.ArrayList;

/**
 *
 */
public class AASTranslator implements RNAFormatTranslator {

    @Override
    public FormattedRNAFile translateToDB(RNAFile rnaFile) {
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createDBBody(rnaFile.structure()), RNAFormatType.DB);
    }

    @Override
    public FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile) {
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createDBNoSequenceBody(rnaFile.structure()), RNAFormatType.DB_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToBPSEQ(RNAFile rnaFile) {
        this.checkNoSequence(rnaFile, RNAFormatType.BPSEQ);
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createBPSEQBody(rnaFile.structure()), RNAFormatType.BPSEQ);
    }

    @Override
    public FormattedRNAFile translateToCT(RNAFile rnaFile) {
        this.checkNoSequence(rnaFile, RNAFormatType.CT);
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createCTBody(rnaFile.structure()), RNAFormatType.CT);
    }

    @Override
    public FormattedRNAFile translateToAAS(RNAFile rnaFile) {
        throw new NoSupportedTranslationException(RNAFormatType.AAS.toString());
    }

    @Override
    public FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile) {
        this.checkNoSequence(rnaFile, RNAFormatType.AAS_NO_SEQUENCE);
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createAASNoSequenceBody(rnaFile.structure()), RNAFormatType.AAS_NO_SEQUENCE);
    }

    @Override
    public FormattedRNAFile translateToFASTA(RNAFile rnaFile) {
        this.checkNoSequence(rnaFile, RNAFormatType.FASTA);
        return new FormattedRNAFile(new ArrayList<>(), RNAFiles.createFASTABody(rnaFile.structure()), RNAFormatType.FASTA);
    }


    /**
     * Useful method to check if the {@code rnaFile} with {@code rnaFormatType} has sequence.
     *
     * @param rnaFile       the {@link RNAFile} to check if it has sequence of the {@link RNASecondaryStructure}
     * @param rnaFormatType the {@link RNAFormatType} of the {@code RNAFile}
     * @throws NoSupportedTranslationException if there is no sequence inside the {@code RNAFile}
     */
    private void checkNoSequence(RNAFile rnaFile, RNAFormatType rnaFormatType) {
        if (rnaFile.structure().getSequence().isEmpty())
            throw new NoSupportedTranslationException(rnaFormatType + ": no sequence found in " + rnaFormatType + " file");
    }
}
