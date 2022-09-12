package it.unicam.cs.twopie.tarnas.model.translator;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DBTranslatorTest {

    private final RNAFormatTranslator translator = new DBTranslator();

    @Test
    void translateToDB(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToDB(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToDBNoSequence(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToDBNoSequence(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToBPSEQ(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToBPSEQ(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToCT(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToCT(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToAAS(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToAAS(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToAASNoSequence(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToAASNoSequence(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }

    @Test
    void translateToFASTA(RNAFile RNAFileToTranslate, RNAFile expectedRNAFile) {
        Assertions.assertEquals(translator.translateToFASTA(RNAFileToTranslate).toString(), expectedRNAFile.toString());
    }
}