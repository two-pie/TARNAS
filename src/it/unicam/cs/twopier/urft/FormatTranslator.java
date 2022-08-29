package it.unicam.cs.twopier.urft;

public interface FormatTranslator {

    RNAFile translateToDB(RNAFile rnaFile);

    RNAFile translateToDBNoSequence(RNAFile rnaFile);

    RNAFile translateToBPSEQ(RNAFile rnaFile);

    RNAFile translateToCT(RNAFile rnaFile);

    RNAFile translateToAAS(RNAFile rnaFile);

    RNAFile translateToAASNoSequence(RNAFile rnaFile);

    RNAFile translateToFASTA(RNAFile rnaFile);

}
