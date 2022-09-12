package it.unicam.cs.twopie.tarnas.model.rnafile;

import java.util.List;

public record FormattedRNAFile(List<String> header, List<String> body, RNAFormatType rnaFormatType) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        header.forEach(sb::append);
        body.forEach(sb::append);
        return sb.toString();
    }
}
