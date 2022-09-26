package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.List;
import java.util.stream.Stream;

/**
 * A representation of file that contains an RNA secondary structure.<br>
 * An RNAFile stores:
 * <ul>
 *  <li>{@code fileName}<br>
 *  The name of this RNAFile, included its extension.
 *    </li>
 * <li>{@code header}<br>
 *  The header of this RNAFile.
 *  </li>
 *  <li>{@code structure}<br>
 *  The represented {@link RNASecondaryStructure} in this RNAFile.
 *  </li>
 *  <li>{@code format}<br>
 *  The {@link RNAFormat} of this RNAFile.
 *  </li>
 *  </ul>
 * The included file's extension in the name of the file is not relevant.
 * The valid format of the file is stored in the {@link RNAFormat} field.
 * The {@code fileName} field stores only the name (included extension) of
 * this {@code RNAFile}, not confuse with the {@link java.nio.file.Path} of the file.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNASecondaryStructure
 * @see RNAFormat
 */
public class RNAFile {
    private final String fileName;
    private final List<String> header;
    private final RNASecondaryStructure structure;
    private final RNAFormat format;
    private final List<String> body;
    private List<String> content;

    /**
     * Create an RNAFile with specified file name, the header of the file,
     * the {@link RNASecondaryStructure} which this RNAFile represents and the RNA format of this RNAFile.
     *
     * @param fileName  the name of this {@code RNAFile}
     * @param header    the header of this {@code RNAFile}
     * @param structure the represented {@code RNASecondaryStructure} in this {@code RNAFile}
     * @param format    the {@link RNAFormat} of this {@code RNAFile}
     */
    public RNAFile(String fileName, List<String> header, List<String> body, RNASecondaryStructure structure, RNAFormat format) {
        this.fileName = fileName;
        this.header = header;
        this.body = body;
        this.structure = structure;
        this.format = format;
        this.content = Stream.concat(this.header.stream(), this.body.stream()).toList();
    }

    /**
     * Returns the name of this {@code RNAFile}, included its extension.
     *
     * @return the name of this {@code RNAFile}
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Returns the header of this {@code RNAFile}.
     *
     * @return the header of this {@code RNAFile}
     */
    public List<String> getHeader() {
        return this.header;
    }

    /**
     * Returns the body of this {@code RNAFile}.
     *
     * @return the body of this {@code RNAFile}
     */
    public List<String> getBody() {
        return this.body;
    }

    /**
     * Returns the {@link RNASecondaryStructure} of this {@code RNAFile}.
     *
     * @return the {@code RNASecondaryStructure} of this {@code RNAFile}
     */
    public RNASecondaryStructure getStructure() {
        return this.structure;
    }

    /**
     * Returns the {@link RNAFormat} of this {@code RNAFile}
     *
     * @return the {@code RNAFormat} of this {@code RNAFile}
     */
    public RNAFormat getFormat() {
        return this.format;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns the content of this {@code RNAFile}
     *
     * @return the content of this {@code RNAFile}
     */
    public List<String> getContent() {
        return this.content;
    }

    /**
     * @param content
     */
    public void setContent(List<String> content) {
        this.content = content;
    }

}
