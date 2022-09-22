package it.unicam.cs.twopie.tarnas.model.rnafile;

import java.util.List;
import java.util.Objects;

public record FormattedRNAFile(List<String> header, List<String> body, RNAFormat rnaFormatType) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //header.forEach(sb::append);
        body.forEach(sb::append);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormattedRNAFile that = (FormattedRNAFile) o;
        return /*Objects.equals(header, that.header) &&*/ Objects.equals(body, that.body) && rnaFormatType == that.rnaFormatType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, body, rnaFormatType);
    }
}
