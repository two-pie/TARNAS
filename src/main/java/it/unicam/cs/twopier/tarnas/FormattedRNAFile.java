package it.unicam.cs.twopier.tarnas;

import java.util.List;

public record FormattedRNAFile(List<String> header, List<String> body,RNAFormatType rnaFormatType) {

}