package it.unicam.cs.twopie.tarnas.model.cleaner;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileTranslator;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * A representation of an RNA files cleaner.<br>
 * This class provides several clean operations on {@link RNAFile } like {@link RNAFormat#DB} lines merging,
 * the removal of lines that starts with a specified word or symbol,
 * the removal of white spaces.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see RNAFormat
 */
public class RNAFileCleaner {

    /**
     * Merges the lines ?????????
     *
     * @param rnaFile
     * @return
     */
    public List<String> mergeDBLines(RNAFile rnaFile) {
        return rnaFile.getFormat() == RNAFormat.DB ?
                RNAFileTranslator.translateToDB(rnaFile).body() :
                RNAFileTranslator.translateToDBNoSequence(rnaFile).body();
    }

    /**
     * Removes all the header lines of the {@code rnafile} that starts with the specified {@code symbol}.
     *
     * @param rnaFile the {@link RNAFile} from which removing any header lines
     * @param symbol  the symbol filter to remove any lines from the header of the {@code rnafile}
     * @return the new header, which has all the lines that not starting with the specified {@code symbol}
     */
    public List<String> removeLinesStartingWith(RNAFile rnaFile, char symbol) {
        return removeIf(rnaFile.getHeader(), line -> !line.startsWith(Character.toString(symbol)));
    }

    /**
     * Removes all the header lines of the {@code rnafile} that contains the specified {@code word}.
     *
     * @param rnaFile the {@link RNAFile} from which removing any header lines
     * @param word    the word filter to remove any lines from the header of the {@code rnafile}
     * @return the new header, which has all the lines that not contains the specified {@code symbol}
     */
    public List<String> removeLinesContaining(RNAFile rnaFile, String word) {
        return removeIf(rnaFile.getHeader(), line -> !line.contains(word));
    }

    /**
     * Removes all the blank header lines of the {@code rnafile}.
     *
     * @param rnaFile the {@link RNAFile} from which removing any header lines
     * @return the new header, which has all the lines that are not blank
     */
    public List<String> removeWhiteSpaces(RNAFile rnaFile) {
        return removeIf(rnaFile.getHeader(), line -> !line.isBlank());
    }

    /**
     * Removes all the lines that satisfy the {@code predicate} from the {@code header}.
     *
     * @param header    the header from which remove all the lines satisfy the {@code predicate}.
     * @param predicate the {@link Predicate} to satisfy for header lines removal
     * @return the new header with all the lines that no satisfies the {@code predicate}
     */
    private List<String> removeIf(List<String> header, Predicate<String> predicate) {
        var newHeader = new ArrayList<String>();
        for (var line : header)
            if (predicate.test(line))
                newHeader.add(line);
        return newHeader;
    }

}
