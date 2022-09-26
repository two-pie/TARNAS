package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * TODO
 */
public class IOController {

    public static void saveFilesTo(List<RNAFile> rnaFiles, File folder) throws IOException {
        if (!folder.isDirectory())
            throw new IllegalArgumentException("Not a folder!");
        for (var f : rnaFiles) {
            var fixedPath = folder.toPath().resolve(f.getFileName());
            Files.write(fixedPath, f.getContent());
        }
    }
}
