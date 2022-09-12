package it.unicam.cs.twopie.tarnas.model.translator;

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
