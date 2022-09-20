package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.List;

public record RNAFile(List<String> header, RNASecondaryStructure structure, RNAFormat format) {
}
