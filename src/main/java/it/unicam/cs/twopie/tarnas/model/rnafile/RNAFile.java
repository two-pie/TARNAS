package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.List;
import java.util.Objects;

public class RNAFile {
    private final String fileName;
    private final List<String> header;
    private final RNASecondaryStructure structure;
    private final RNAFormat format;

    public RNAFile(String fileName, List<String> header, RNASecondaryStructure structure, RNAFormat format) {
        this.fileName = fileName;
        this.header = header;
        this.structure = structure;
        this.format = format;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getHeader() {
        return header;
    }

    public RNASecondaryStructure getStructure() {
        return structure;
    }

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
