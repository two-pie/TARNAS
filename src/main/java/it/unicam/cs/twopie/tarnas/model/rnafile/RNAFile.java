package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.RNASecondaryStructure;

import java.util.List;

public record RNAFile(List<String> header, RNASecondaryStructure structure, RNAFormatType format) {
}
