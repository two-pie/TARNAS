package it.unicam.cs.twopie.tarnas.model.cleaner;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFiles;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormatType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Cleaner {

    public List<String> mergeDBLines(RNAFile rnaFile) {
        return rnaFile.format() == RNAFormatType.DB ?
                RNAFiles.createDBBody(rnaFile.structure()) :
                RNAFiles.createDBNoSequenceBody(rnaFile.structure());
    }

    public List<String> removeLinesStartingWith(RNAFile rnaFile, char symbol) {
        return removeIf(rnaFile.header(), line -> !line.startsWith(Character.toString(symbol)));
    }

    public List<String> removeLinesContaining(RNAFile rnaFile, String word) {
        return removeIf(rnaFile.header(), line -> !line.contains(word));
    }

    public List<String> removeWhiteSpaces(RNAFile rnaFile) {
        return removeIf(rnaFile.header(), line -> !line.isBlank());
    }

    private List<String> removeIf(List<String> header, Predicate<String> predicate){
        var newHeader = new ArrayList<String>();
        for (var line : header)
            if (predicate.test(line))
                newHeader.add(line);
        return newHeader;
    }

}
