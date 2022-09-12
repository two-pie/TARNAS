package it.unicam.cs.twopier.tarnas.model.rnafile;

import it.unicam.cs.twopier.tarnas.model.RNASecondaryStructure;

import java.util.List;

public record RNAFile(List<String> header, RNASecondaryStructure structure,RNAFormatType format) {
}
