package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.List;
import java.util.Objects;

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

    /**
     * Create an RNAFile with specified file name, the header of the file,
     * the {@link RNASecondaryStructure} which this RNAFile represents and the RNA format of this RNAFile.
     *
     * @param fileName  the name of this {@code RNAFile}
     * @param header    the header of this {@code RNAFile}
     * @param structure the represented {@code RNASecondaryStructure} in this {@code RNAFile}
     * @param format    the {@link RNAFormat} of this {@code RNAFile}
     */
    public RNAFile(String fileName, List<String> header, RNASecondaryStructure structure, RNAFormat format) {
        this.fileName = fileName;
        this.header = header;
        this.structure = structure;
        this.format = format;
    }

    /**
     * Returns the name of this {@code RNAFile}, included its extension.
     *
     * @return the name of this {@code RNAFile}
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the header of this {@code RNAFile}.
     *
     * @return the header of this {@code RNAFile}
     */
    public List<String> getHeader() {
        return header;
    }

    /**
     * Returns the {@link RNASecondaryStructure} of this {@code RNAFile}.
     *
     * @return the {@code RNASecondaryStructure} of this {@code RNAFile}
     */
    public RNASecondaryStructure getStructure() {
        return structure;
    }

    /**
     * Returns the {@link RNAFormat} of this {@code RNAFile}
     *
     * @return the {@code RNAFormat} of this {@code RNAFile}
     */
    public RNAFormat getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RNAFile rnaFile = (RNAFile) o;
        return Objects.equals(fileName, rnaFile.fileName) && Objects.equals(header, rnaFile.header) && Objects.equals(structure, rnaFile.structure) && format == rnaFile.format;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, header, structure, format);
    }

}
