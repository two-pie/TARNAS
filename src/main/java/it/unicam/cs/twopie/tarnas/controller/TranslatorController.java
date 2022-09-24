package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

/**
 * An implementation of Translator Controller that accepts input like files, format translation and converts that
 * input to commands for the Model or View.
 * This Controller takes care translation operations and file loading/saving/deleting and directory loading/saving.
 * In particular, it provides paralleled translation operations when multiple files are loaded, to have better performance.
 * Moreover, this Controller executes checking for I/O errors.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see Stream#parallel()
 */
public class TranslatorController {

    /**
     * Loaded {@link RNAFile}s to translate.
     */
    private final List<RNAFile> loadedRNAFiles;

    /**
     * Translated loaded {@link RNAFile}s.
     */
    private final List<RNAFile> formattedLoadedRNAFiles;

    /**
     * This conversion matrix has the X {@link RNAFormat} as key-map and
     * every key has the list of possible Y, Z, W destination {@code RNAFormat} to which convert X, as value-map.
     */
    private final Map<RNAFormat, List<RNAFormat>> conversionMatrix;

    /**
     * Creates a Translator Controller.
     * It initializes the conversion matrix for translation operations.
     */
    public TranslatorController() {
        loadedRNAFiles = new ArrayList<>();
        formattedLoadedRNAFiles = new ArrayList<>();
        conversionMatrix = Map.of(
                AAS, List.of(AAS_NO_SEQUENCE, BPSEQ, CT, DB, DB_NO_SEQUENCE, FASTA),
                AAS_NO_SEQUENCE, List.of(DB_NO_SEQUENCE),
                BPSEQ, List.of(AAS, AAS_NO_SEQUENCE, CT, DB, DB_NO_SEQUENCE, FASTA),
                CT, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, DB, DB_NO_SEQUENCE, FASTA),
                DB, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, CT, DB_NO_SEQUENCE, FASTA),
                DB_NO_SEQUENCE, List.of(AAS_NO_SEQUENCE),
                FASTA, List.of());
    }


    /**
     * Loads a file from the specified {@code srcFilePath} and stores it in the list of loaded files to translate.
     *
     * @param srcFilePath the {@link Path} of the file to translate.
     * @throws IOException           if an I/O error occurs
     * @throws FileNotFoundException if the path not exists
     */
    public void loadFile(Path srcFilePath) throws IOException {
        if (!Files.exists(srcFilePath))
            throw new FileNotFoundException("Non existent file with path: " + srcFilePath);
        this.loadedRNAFiles.add(this.getRNAFileOf(srcFilePath));
    }

    /**
     * Loads a directory from the specified {@code srcDirectoryPath} and stores all the contained files in the list of loaded files to translate.
     *
     * @param srcDirectoryPath the {@link Path} of the directory to translate.
     * @throws IOException           if an I/O error occurs
     * @throws FileNotFoundException if the path not exists
     */
    public void loadDirectory(Path srcDirectoryPath) throws IOException {
        Files.newDirectoryStream(srcDirectoryPath).forEach(
                p -> {
                    try {
                        if (Files.isRegularFile(p))
                            this.loadFile(p);
                        else if (Files.isDirectory(p))
                            this.loadDirectory(p);
                        else
                            throw new FileNotFoundException("Non existent path: " + p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /**
     * Reads the content of the specified {@code srcFilePath} and gets the corresponding {@link RNAFile}.
     *
     * @param srcFilePath the {@link Path} of file to get the {@code RNAFile}.
     * @return the {@code RNAFile} that represents the content of the {@code srcFilePath}
     * @throws IOException if an I/O error occurs
     */
    public RNAFile getRNAFileOf(Path srcFilePath) throws IOException {
        return RNAFileConstructor.getInstance().construct(srcFilePath);
    }

    /**
     * Translates all loaded files to the specified {@code dstRNAFormat} and
     * returns the list of all translated loaded files.
     *
     * @param dstRNAFormat the destination {@link RNAFormat} to which translate all loaded files.
     * @return the list of all translated loaded files
     */
    public List<RNAFile> translateAllLoadedFiles(RNAFormat dstRNAFormat) {
        this.loadedRNAFiles.parallelStream().forEach(f -> {
            try {
                this.formattedLoadedRNAFiles.add(this.translateTo(f, dstRNAFormat));
            } catch (RNAFormatTranslationException e) {
                throw new RuntimeException(e);
            }
        });
        return this.formattedLoadedRNAFiles;
    }

    /**
     * Translates the specified {@link RNAFile} to the specified {@code dstRNAFormat} and
     * returns the {@link RNAFile} that represents the translated specified {@code rnaFile}.
     *
     * @param rnaFile      the {@code RNAFile} to translate to the specified {@code dstRNAFormat}.
     * @param dstRNAFormat the destination {@link RNAFormat} to which translate the specified {@code rnaFile}
     * @return the {@code FormattedRNAFile}, so the translation of the specified {@code rnaFile}
     * @throws RNAFormatTranslationException if a translation error occurs
     */
    private RNAFile translateTo(RNAFile rnaFile, RNAFormat dstRNAFormat) throws RNAFormatTranslationException {
        RNAFile formattedRNAFile;
        if (this.conversionMatrix.get(rnaFile.getFormat()).contains(dstRNAFormat))
            formattedRNAFile = this.noCheckingtranslateTo(rnaFile, dstRNAFormat);
        else
            throw new RNAFormatTranslationException("Cannot translate from " + rnaFile.getFormat() + " to " + dstRNAFormat);
        return formattedRNAFile;
    }

    /**
     * Useful method to translate an {@link RNAFile} to the specified {@code rnaFormat} without translation checking.
     *
     * @param rnaFile   the {@code RNAFile} to translate.
     * @param rnaFormat the destination {@link RNAFormat}
     * @return the {@link RNAFile} that represent the translation of the specified {@code rnaFile}
     * in the destination {@code rnaFormat}
     */
    private RNAFile noCheckingtranslateTo(RNAFile rnaFile, RNAFormat rnaFormat) {
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

    /**
     * Saves the specified {@code formattedRNAFile} to the specified {@code dstFilePath}.
     * The file will be saved with its {@link RNAFile#getFileName()} ()} by default.
     *
     * @param formattedRNAFile the file to save.
     * @param dstFilePath      the destination saving {@link Path}.
     * @throws IOException if an I/O error occurs
     */
    public void saveFile(RNAFile formattedRNAFile, Path dstFilePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        formattedRNAFile.getHeader().forEach(l -> sb.append(l).append("\n"));
        //sb.append("\n");
        formattedRNAFile.getBody().forEach(l -> sb.append(l).append("\n"));
        Files.write(dstFilePath, sb.toString().getBytes());
        // TODO: mettersi d'accordo se fare gli \n qui per ogni linea o sul Translator...
    }

    /**
     * @param formattedRNAFiles
     * @param dstDirectoryPath
     * @return
     */
    // TODO: javadoc
    public boolean saveDirectory(List<RNAFile> formattedRNAFiles, Path dstDirectoryPath) {
        formattedRNAFiles.parallelStream().forEach(f -> {
            try {
                this.saveFile(f, dstDirectoryPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return true;
    }

    /**
     * Removes the specified {@code rnaFile} from loaded files.
     *
     * @param rnaFile the {@link RNAFile} to remove
     * @throw IllegalArgumentException if the list of loaded files not contains the specified {@code rnaFile}
     */
    public void deleteFile(RNAFile rnaFile) {
        if (!this.loadedRNAFiles.contains(rnaFile))
            throw new IllegalArgumentException("RNAFile not found in the list of loaded files");
        this.loadedRNAFiles.remove(rnaFile);
    }

    /**
     * Resets all data structures of this Controller.
     */
    public void resetAll() {
        this.loadedRNAFiles.clear();
        this.formattedLoadedRNAFiles.clear();
    }


}
