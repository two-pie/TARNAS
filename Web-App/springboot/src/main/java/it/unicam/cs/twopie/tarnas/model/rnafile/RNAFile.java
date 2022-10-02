package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
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
@Getter
@Setter
public class RNAFile {
    private String fileName;
    private List<String> content;
    private List<String> header;
    private RNASecondaryStructure structure;
    private RNAFormat format;
    private List<String> body;

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

    public RNAFile() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RNAFile rnaFile)) return false;
        return getFileName().equals(rnaFile.getFileName()) && getHeader().equals(rnaFile.getHeader()) && getStructure().equals(rnaFile.getStructure()) && getFormat().equals(rnaFile.getFormat()) && getBody().equals(rnaFile.getBody()) && getContent().equals(rnaFile.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getHeader(), getStructure(), getFormat(), getBody(), getContent());
    }

    @Override
    public String toString() {
        return "RNAFile{" +
                "fileName='" + fileName + '\'' +
                ", header=" + header +
                ", structure=" + structure +
                ", format=" + format +
                ", body=" + body +
                ", content=" + content +
                '}';
    }
}
