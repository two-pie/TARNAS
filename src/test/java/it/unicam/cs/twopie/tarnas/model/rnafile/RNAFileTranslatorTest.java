package it.unicam.cs.twopie.tarnas.model.rnafile;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RNAFileTranslatorTest {

    private final Path archaea90110AllType;

    RNAFileTranslatorTest() throws URISyntaxException {
        this.archaea90110AllType = Paths.get(Objects.requireNonNull(this.getClass().getResource("/datasets/Archaea-90-110-allType")).toURI());
    }

    @Test
    public void prova() {
        System.out.println(this.archaea90110AllType);
    }


    @Test
    void translateToDB() {
    }

    @Test
    void translateToDBNoSequence() {
    }

    @Test
    void translateToBPSEQ() {
    }

    @Test
    void translateToCT() {
    }

    @Test
    void translateToAAS() {
    }

    @Test
    void translateToAASNoSequence() {
    }

    @Test
    void translateToFASTA() {
    }
}