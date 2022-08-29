package it.unicam.cs.twopier.urft;

import java.util.List;

public record RNAFile(List<String> header, RNAFormatType formatType, RNASecondaryStructure structure) {

}
