package it.unicam.cs.twopie.tarnas.model.translator;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.FormattedRNAFile;

/**
 *
 */
public interface RNAFormatTranslator {

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToDB(RNAFile rnaFile);

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile);

    FormattedRNAFile translateToBPSEQ(RNAFile rnaFile);

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToCT(RNAFile rnaFile);

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToAAS(RNAFile rnaFile);

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile);

    /**
     * @param rnaFile
     * @return
     */
    FormattedRNAFile translateToFASTA(RNAFile rnaFile);

}
