package it.unicam.cs.twopier.urft;

public class BpseqTranslator implements RNAFormatTranslator {


    @Override
    public String translateToDB(RNASecondaryStructure rnaSecondaryStructure) {
        return "";
    }

    @Override
    public String translateToDBNoSequence(RNASecondaryStructure rnaSecondaryStructure) {
        return "";
    }

    @Override
    public String translateToBPSEQ(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToCT(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToAAS(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToAASNoSequence(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToFASTA(RNASecondaryStructure rnaSecondaryStructure) {
        return null;
    }
}
