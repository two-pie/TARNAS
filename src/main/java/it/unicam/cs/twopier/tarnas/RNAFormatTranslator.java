package it.unicam.cs.twopier.tarnas;

public interface RNAFormatTranslator {

    FormattedRNAFile translateToDB(RNAFile rnaFile);

    FormattedRNAFile translateToDBNoSequence(RNAFile rnaFile);

    FormattedRNAFile translateToBPSEQ(RNAFile rnaFile);

    FormattedRNAFile translateToCT(RNAFile rnaFile);

    FormattedRNAFile translateToAAS(RNAFile rnaFile);

    FormattedRNAFile translateToAASNoSequence(RNAFile rnaFile);

    FormattedRNAFile translateToFASTA(RNAFile rnaFile);

}
