package it.unicam.cs.twopier.tarnas.rnafile;

import it.unicam.cs.twopier.tarnas.RNASecondaryStructure;

import java.util.List;

public record RNAFile(List<String> header, RNASecondaryStructure structure) {

}
