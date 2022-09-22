package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.FormattedRNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TranslatorController {

    private final List<RNAFile> rnaFiles;
    private final List<FormattedRNAFile> formattedRNAFiles;

    public TranslatorController() {
        rnaFiles = new ArrayList<>();
        formattedRNAFiles = new ArrayList<>();
    }

    public boolean loadFile(Path srcFilePath) {
        return false;
    }

    public boolean loadDirectory(Path srcDirectoryPath) {
        return false;
    }

    public RNAFile getRNAFileOf(Path srcFilePath) {
        return null;
    }

    public FormattedRNAFile translateTo(RNAFormat rnaFormat) {
        return null;
    }

    public boolean saveFile(FormattedRNAFile formattedRNAFile, Path dstFilePath) {
        return false;
    }

    public boolean saveDirectory(List<FormattedRNAFile> formattedRNAFiles, Path dstDirectoryPath) {
        return false;
    }

    public void resetAll() {

    }


}
