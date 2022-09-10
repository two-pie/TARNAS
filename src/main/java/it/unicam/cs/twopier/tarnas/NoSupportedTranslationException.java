package it.unicam.cs.twopier.tarnas;

/**
 *
 */
public class NoSupportedTranslationException extends RuntimeException{

    /**
     * @param wrongFormat
     */
    public NoSupportedTranslationException(String wrongFormat) {
        super("Cannot convert to " + wrongFormat);
    }
}
