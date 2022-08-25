public class Translator {
    boolean[][] translationMatrix;

    public Translator() {
        this.translationMatrix = new boolean[][]{
                {false, false, false, false, false, true, false},
                {true, false, true, true, true, true, true},
                {true, true, false, true, true, true, true},
                {false, false, false, false, false, false},
                {true, true, true, true, false, true, true},
                {true, false, false, false, false, false, false},
                {true, true, true, true, true, true, false}
        };
    }

    public String translateToDB(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();

        return "";
        //todo
    }

    public String translateToDBNoSequence(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return "";
        //todo
    }

    public String translateToBPSEQ(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return "";
        //todo
    }

    public String translateToCT(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return "";
        //todo
    }

    public String translateToAAS(RNAsecondaryStructure rnaSecondaryStructure, FormatType type, boolean sequence) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return "";
        //todo
    }

    public String translateToAASNoSequence(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return "";
        //todo
    }

    public String translateToFASTA(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        if (this.cannotTranslateTo(rnaSecondaryStructure, type))
            throw new NoSupportedTranslationException();
        return rnaSecondaryStructure.sequence();
    }

    private boolean cannotTranslateTo(RNAsecondaryStructure rnaSecondaryStructure, FormatType type) {
        return !this.translationMatrix[rnaSecondaryStructure.format().getIndex()][type.getIndex()];
    }


}
