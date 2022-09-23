package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

public class TranslatorController {

    private final List<RNAFile> rnaFiles;
    private final List<FormattedRNAFile> formattedRNAFiles;

    private final Map<RNAFormat, List<RNAFormat>> conversionMatrix;

    public TranslatorController() {
        rnaFiles = new ArrayList<>();
        formattedRNAFiles = new ArrayList<>();
        conversionMatrix = Map.of(
                AAS, List.of(AAS_NO_SEQUENCE, BPSEQ, CT, DB, DB_NO_SEQUENCE, FASTA),
                AAS_NO_SEQUENCE, List.of(DB_NO_SEQUENCE),
                BPSEQ, List.of(AAS, AAS_NO_SEQUENCE, CT, DB, DB_NO_SEQUENCE, FASTA),
                CT, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, DB, DB_NO_SEQUENCE, FASTA),
                DB, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, CT, DB_NO_SEQUENCE, FASTA),
                DB_NO_SEQUENCE, List.of(AAS_NO_SEQUENCE),
                FASTA, List.of());
    }

    public boolean loadFile(Path srcFilePath) throws IOException {
        if (!Files.exists(srcFilePath))
            throw new FileNotFoundException("Nonexistent file with path: " + srcFilePath);
        this.rnaFiles.add(this.getRNAFileOf(srcFilePath));
        return true;
    }

    public void loadDirectory(Path srcDirectoryPath) throws IOException {
        Files.newDirectoryStream(srcDirectoryPath).forEach(
                p -> {
                    try {
                        if (Files.exists(p)) {
                            if (Files.isRegularFile(p))
                                this.loadFile(p);
                            else if (Files.isDirectory(p))
                                this.loadDirectory(p);
                        } else
                            throw new FileNotFoundException("Nonexistent path: " + p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public RNAFile getRNAFileOf(Path srcFilePath) throws IOException {
        return RNAFileConstructor.getInstance().construct(srcFilePath);
    }

    public List<FormattedRNAFile> translateAllLoadedFiles(RNAFormat dstRNAFormat) {
        this.rnaFiles.forEach(f -> {
            try {
                this.formattedRNAFiles.add(this.translateTo(f, dstRNAFormat));
            } catch (RNAFormatTranslationException e) {
                throw new RuntimeException(e);
            }
        });
        return this.formattedRNAFiles;
    }

    private FormattedRNAFile translateTo(RNAFile rnaFile, RNAFormat dstFormat) throws RNAFormatTranslationException {
        FormattedRNAFile formattedRNAFile;
        if (this.conversionMatrix.get(rnaFile.getFormat()).contains(dstFormat))
            formattedRNAFile = this.noCheckingtranslateTo(rnaFile, dstFormat);
        else
            throw new RNAFormatTranslationException("Cannot translate from " + rnaFile.getFormat() + " to " + dstFormat);
        return formattedRNAFile;
    }

    private FormattedRNAFile noCheckingtranslateTo(RNAFile rnaFile, RNAFormat rnaFormat) {
        return switch (rnaFormat) {
            case AAS -> RNAFileTranslator.translateToAAS(rnaFile);
            case AAS_NO_SEQUENCE -> RNAFileTranslator.translateToAASNoSequence(rnaFile);
            case BPSEQ -> RNAFileTranslator.translateToBPSEQ(rnaFile);
            case CT -> RNAFileTranslator.translateToCT(rnaFile);
            case DB -> RNAFileTranslator.translateToDB(rnaFile);
            case DB_NO_SEQUENCE -> RNAFileTranslator.translateToDBNoSequence(rnaFile);
            case FASTA -> RNAFileTranslator.translateToFASTA(rnaFile);
        };
    }

    public boolean saveFile(FormattedRNAFile formattedRNAFile, Path dstFilePath) throws IOException {
        //if (!Files.exists(dstFilePath))
        //  throw new FileNotFoundException("Nonexistent file with path: " + dstFilePath);
        StringBuilder sb = new StringBuilder();
        formattedRNAFile.header().forEach(sb::append);
        formattedRNAFile.body().forEach(sb::append);
        Files.write(dstFilePath, sb.toString().getBytes());
        return true;
    }

    public boolean saveDirectory(List<FormattedRNAFile> formattedRNAFiles, Path dstDirectoryPath) {
        formattedRNAFiles.forEach(f -> {
            try {
                this.saveFile(f, dstDirectoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }

    public void resetAll() {
        this.rnaFiles.forEach(System.out::println);
        this.rnaFiles.clear();
        this.formattedRNAFiles.clear();
    }


}
