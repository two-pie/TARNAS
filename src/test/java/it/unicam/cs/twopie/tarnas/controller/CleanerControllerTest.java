package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CleanerControllerTest {

    private static Path db, aas, mergedLines, noBlankLines;

    private final static String fs = File.separator;

    private final CleanerController cleanerController = CleanerController.getInstance();

    @BeforeAll
    static void initializePath() throws URISyntaxException {
        db = Paths.get(Objects.requireNonNull(CleanerControllerTest.class.getResource("/cleanFilesTest/test/db")).toURI());
        aas = Paths.get(Objects.requireNonNull(CleanerControllerTest.class.getResource("/cleanFilesTest/test/aas")).toURI());
        mergedLines = Paths.get(Objects.requireNonNull(CleanerControllerTest.class.getResource("/cleanFilesTest/expected/merged-lines")).toURI());
        noBlankLines = Paths.get(Objects.requireNonNull(CleanerControllerTest.class.getResource("/cleanFilesTest/expected/no-blank-lines")).toURI());
    }

    @Test
    void mergeDBLines() throws IOException {
        Files.walk(db).parallel().filter(Files::isRegularFile).forEach(
                f -> {
                    try {
                        RNAFile toClean = RNAFileConstructor.getInstance().construct(f);
                        RNAFile cleaned = cleanerController.mergeDBLines(toClean);
                        RNAFile expected = RNAFileConstructor.getInstance().construct(mergedLines.resolve(f.getFileName()));
                                assertEquals(expected, cleaned);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Test
    void removeHeader() {

    }

    @Test
    void removeLinesStartingWith() {

    }

    @Test
    void removeLinesContaining() {

    }

    @Test
    void removeWhiteSpaces() {

    }
}