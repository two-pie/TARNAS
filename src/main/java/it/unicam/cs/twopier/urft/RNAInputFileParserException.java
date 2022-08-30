package it.unicam.cs.twopier.urft;

import java.io.Serial;

/**
 * Exception to signal any syntax error in input files containing the
 * description of an RNA secondary structure in any supported format.
 *
 * @author Luca Tesei
 *
 */
public class RNAInputFileParserException extends RuntimeException {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4540612561494091099L;

    /**
     *
     */
    public RNAInputFileParserException() {
    }

    /**
     * @param message Description message
     */
    public RNAInputFileParserException(String message) {
        super(message);
    }

    /**
     * @param cause Cause of the exception
     */
    public RNAInputFileParserException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message Description message
     * @param cause Cause of the exception
     */
    public RNAInputFileParserException(String message, Throwable cause) {
        super(message, cause);
    }

}