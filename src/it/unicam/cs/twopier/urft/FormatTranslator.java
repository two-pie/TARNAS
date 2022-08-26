package it.unicam.cs.twopier.urft;

public interface FormatTranslator {

    String translateToDB(RNASecondaryStructure rnaSecondaryStructure);

    String translateToDBNoSequence(RNASecondaryStructure rnaSecondaryStructure);

    String translateToBPSEQ(RNASecondaryStructure rnaSecondaryStructure);

    String translateToCT(RNASecondaryStructure rnaSecondaryStructure);

    String translateToAAS(RNASecondaryStructure rnaSecondaryStructure);

    String translateToAASNoSequence(RNASecondaryStructure rnaSecondaryStructure);

    String translateToFASTA(RNASecondaryStructure rnaSecondaryStructure);

}
