package it.unicam.cs.twopier.tarnas.model.rnafile;

import java.util.List;

public record FormattedRNAFile(List<String> header, List<String> body,RNAFormatType rnaFormatType) {

}
