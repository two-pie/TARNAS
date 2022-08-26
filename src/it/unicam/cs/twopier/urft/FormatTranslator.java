package it.unicam.cs.twopier.urft;

public interface FormatTranslator {

    String translateToDB(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToDBNoSequence(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToBPSEQ(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToCT(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToAAS(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToAASNoSequence(RNAsecondaryStructure rnaSecondaryStructure);

    String translateToFASTA(RNAsecondaryStructure rnaSecondaryStructure);

}
