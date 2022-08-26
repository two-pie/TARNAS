package it.unicam.cs.twopier.urft;

public class BpseqTranslator implements FormatTranslator{


    @Override
    public String translateToDB(RNAsecondaryStructure rnaSecondaryStructure) {
        if (rnaSecondaryStructure.format() != FormatType.BPSEQ)
            throw new IllegalArgumentException();
        StringBuilder db = new StringBuilder();
        // add header
        for(var line : rnaSecondaryStructure.header())
            db.append('#').append(line);
        db.append("\n");
        // add sequence
        db.append(rnaSecondaryStructure.sequence());
        // add structure
        db.append(rnaSecondaryStructure.structure());
        return db.toString();
    }

    @Override
    public String translateToDBNoSequence(RNAsecondaryStructure rnaSecondaryStructure) {
        if (rnaSecondaryStructure.format() != FormatType.BPSEQ)
            throw new IllegalArgumentException();
        String db = rnaSecondaryStructure.sequence() +
                // add structure
                rnaSecondaryStructure.structure();
        return db;
    }

    @Override
    public String translateToBPSEQ(RNAsecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToCT(RNAsecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToAAS(RNAsecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToAASNoSequence(RNAsecondaryStructure rnaSecondaryStructure) {
        return null;
    }

    @Override
    public String translateToFASTA(RNAsecondaryStructure rnaSecondaryStructure) {
        return null;
    }
}
