package it.unicam.cs.twopier.tarnas.model.cleaner;

import it.unicam.cs.twopier.tarnas.model.rnafile.FormattedRNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopier.tarnas.model.rnafile.RNAFormatType;

public class Cleaner {

    public FormattedRNAFile mergeDBLines(RNAFile rnaFile) {
        if(rnaFile.format() != RNAFormatType.DB || rnaFile.format() != RNAFormatType.DB_NO_SEQUENCE)
            throw new IllegalArgumentException();
        return "";
        //todo
    }

    public String removeLinesStartingWith(char symbol) {
        return "";
        //todo
    }

    public String removeLinesContaining(String word) {
        return "";
        //todo
    }

    public String removeWhiteSpaces() {
        return "";
        //todo
    }

}
