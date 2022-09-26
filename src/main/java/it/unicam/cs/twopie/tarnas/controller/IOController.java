package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * // TODO: javadoc
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
public class IOController {


    private RNAFormat recognizedFormat;

    private static IOController instance;

    /**
     * Loaded file paths
     */
    private final List<RNAFile> loadedRNAFiles;


    /**
     * Invisible constructor.
     */
    private IOController() {
        this.loadedRNAFiles = new ArrayList<>();
    }

    /**
     * Factory method for the obtaining the {@link IOController} instance.
     *
     * @return the instance of this Singleton
     */
    public static IOController getInstance() {
        if (instance == null)
            instance = new IOController();
        return instance;
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
     * Returns the list of all loaded file paths.
     *
     * @return the list of all loaded file paths
     */
    public List<RNAFile> getLoadedRNAFiles() {
        return this.loadedRNAFiles;
    }

    /**
     * Loads a file from the specified {@code srcFilePath} and stores it in the list of loaded files to translate.
     *
     * @param srcFilePath the {@link Path} of the file to translate.
     * @throws IOException              if an I/O error occurs
     * @throws FileNotFoundException    if the path not exists
     * @throws IllegalArgumentException if the format of corresponding TODO
     * @
     */
    public RNAFile loadFile(Path srcFilePath) throws IOException {
        if (!Files.exists(srcFilePath))
            throw new FileNotFoundException("Non existent file with path: " + srcFilePath);
        var rnaFile = this.getRNAFileOf(srcFilePath);
        this.loadedRNAFiles.add(rnaFile);
        if (this.recognizedFormat == null)
            this.recognizedFormat = rnaFile.getFormat();
        else if (this.recognizedFormat != rnaFile.getFormat())
            throw new IllegalArgumentException("All loaded files must be of the same format!");
        return rnaFile;
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
     * Saves the specified {@code rnaFiles} to the specified {@code dstFilePath}.
     * Every file will be saved with its {@link RNAFile#getFileName()} by default.
     *
     * @param rnaFiles the list of all files to save
     * @param dstPath  the destination path where save the files
     * @throws IOException if an I/O error occurs
     */
    public void saveFilesTo(List<RNAFile> rnaFiles, Path dstPath) throws IOException {
        if (!Files.isDirectory(dstPath))
            throw new IllegalArgumentException(dstPath + "is not a directory");
        for (var f : rnaFiles)
            Files.write(dstPath.resolve(f.getFileName()), f.getContent());
    }

    /**
     * Removes the specified {@code rnaFile} from loaded rnaFiles.
     *
     * @param rnaFile the {@link RNAFile} to remove
     * @throws IllegalArgumentException if the list of loaded files not contains the specified {@code rnaFile}
     */
    public void deleteFile(RNAFile rnaFile) {
        if (!this.loadedRNAFiles.contains(rnaFile))
            throw new IllegalArgumentException("RNAFile not found in the list of loaded files");
        this.loadedRNAFiles.remove(rnaFile);
        if (this.loadedRNAFiles.isEmpty())
            this.recognizedFormat = null;
    }

    /**
     * Resets all data structures of this Controller, {@link TranslatorController} and {@link CleanerController}.
     */
    public void clearAllDataStructures() {
        this.loadedRNAFiles.clear();
        this.recognizedFormat = null;
    }

    /**
     * Get the recognized format
     *
     * @return the recognized format
     */
    public RNAFormat getRecognizedFormat() {
        return this.recognizedFormat;
    }
}
