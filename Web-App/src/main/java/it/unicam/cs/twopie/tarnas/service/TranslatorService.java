package it.unicam.cs.twopie.tarnas.service;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileTranslator;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

/**
 * // TODO: javadoc
 * An implementation of Translator Controller that accepts input from the {@link IOService} and converts that
 * input to commands for the Model or View.
 * This Controller takes care translation operations and file loading/saving/deleting and directory loading/saving.
 * In particular, it provides paralleled translation operations when multiple files are loaded, to have better performance.
 * Moreover, this Controller executes checking for I/O errors.
 *
 * @author Piero Hierro, Piermichele Rosati
 * @see RNAFile
 * @see Stream#parallel()
 */
@Service
public class TranslatorService {

    /**
     * This conversion matrix has the X {@link RNAFormat} as key-map and
     * every key has the list of possible Y, Z, W destination {@code RNAFormat} to which convert X, as value-map.
     */
    private final Map<RNAFormat, List<RNAFormat>> conversionMatrix;

    /**
     * Creates a Translator Controller.
     * It initializes the conversion matrix for translation operations.
     */
    @Autowired
    public TranslatorService() {
        conversionMatrix = Map.of(
                AAS, List.of(AAS_NO_SEQUENCE, BPSEQ, CT, DB, DB_NO_SEQUENCE, FASTA),
                AAS_NO_SEQUENCE, List.of(DB_NO_SEQUENCE),
                BPSEQ, List.of(AAS, AAS_NO_SEQUENCE, CT, DB, DB_NO_SEQUENCE, FASTA),
                CT, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, DB, DB_NO_SEQUENCE, FASTA),
                DB, List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, CT, DB_NO_SEQUENCE, FASTA),
                DB_NO_SEQUENCE, List.of(AAS_NO_SEQUENCE),
                FASTA, List.of());
    }

    /**
     *
     * @param rnaFormat
     * @return
     */
    public List<RNAFormat> getAvailableTranslations(RNAFormat rnaFormat) {
        return switch (rnaFormat) {
            case AAS -> List.of(AAS_NO_SEQUENCE, BPSEQ, CT, DB, DB_NO_SEQUENCE, FASTA);
            case AAS_NO_SEQUENCE -> List.of(DB_NO_SEQUENCE);
            case BPSEQ -> List.of(AAS, AAS_NO_SEQUENCE, CT, DB, DB_NO_SEQUENCE, FASTA);
            case CT -> List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, DB, DB_NO_SEQUENCE, FASTA);
            case DB -> List.of(AAS, AAS_NO_SEQUENCE, BPSEQ, CT, DB_NO_SEQUENCE, FASTA);
            case DB_NO_SEQUENCE -> List.of(AAS_NO_SEQUENCE);
            case FASTA -> List.of();
        };
    }

    /**
     *
     * @param rnaFile
     * @param dstRNAFormat
     * @return
     * @throws RNAFormatTranslationException
     */
    public Optional<RNAFile> translateTo(RNAFile rnaFile, RNAFormat dstRNAFormat) {
        if (this.conversionMatrix.get(rnaFile.getFormat()).contains(dstRNAFormat))
            return Optional.of(this.noCheckingtranslateTo(rnaFile, dstRNAFormat));
        return Optional.empty();
    }

    /**
     *
     * @param rnaFile
     * @param rnaFormat
     * @return
     */
    private RNAFile noCheckingtranslateTo(RNAFile rnaFile, RNAFormat rnaFormat) {
        return switch (rnaFormat) {
            case AAS -> RNAFileTranslator.translateToAAS(rnaFile);
            case AAS_NO_SEQUENCE -> RNAFileTranslator.translateToAASNoSequence(rnaFile);
            case BPSEQ -> RNAFileTranslator.translateToBPSEQ(rnaFile);
            case CT -> RNAFileTranslator.translateToCT(rnaFile);
            case DB -> RNAFileTranslator.translateToDB(rnaFile);
            case DB_NO_SEQUENCE -> RNAFileTranslator.translateToDBNoSequence(rnaFile);
            case FASTA -> RNAFileTranslator.translateToFASTA(rnaFile);
        };
    }
}
