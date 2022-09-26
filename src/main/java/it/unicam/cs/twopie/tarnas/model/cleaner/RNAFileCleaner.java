package it.unicam.cs.twopie.tarnas.model.cleaner;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;

import java.util.List;
import java.util.function.Function;

/**
 * A representation of an RNA files cleaner.<br>
 * This class allows a cleaning function to be applied to any {@link RNAFile}.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see RNAFormat
 */
public class RNAFileCleaner {

    /**
     * Apply a cleaning function
     *
     * @param rnaFile the {@link RNAFile} on which to apply the function
     * @param function the function to be applied
     * @return the result of the function
     */
    public static RNAFile applyCleanOption(RNAFile rnaFile, Function<RNAFile, RNAFile> function) {
        return function.apply(rnaFile);
    }

}
