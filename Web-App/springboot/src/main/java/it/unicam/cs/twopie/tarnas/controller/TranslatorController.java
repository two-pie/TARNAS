package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import it.unicam.cs.twopie.tarnas.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/translation")
public class TranslatorController {

    @Autowired
    private TranslatorService translatorService;

    @PostMapping("/translate/{dstFormat}")
    public RNAFile translate(@PathVariable RNAFormat dstFormat, @RequestBody RNAFile rnaFile) {
        rnaFile = checkSyntax(rnaFile);
        var translatedRnaFile = this.translatorService.translateTo(rnaFile, dstFormat);
        if (translatedRnaFile.isPresent())
            return translatedRnaFile.get();
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot translate from " + rnaFile.getFormat().getName() + "to " + dstFormat.getName());
    }

    @GetMapping("/available-translations")
    private List<RNAFormat> getAvaiableTranslation(RNAFile rnaFile) {
        rnaFile = checkSyntax(rnaFile);
        return this.translatorService.getAvailableTranslations(rnaFile.getFormat());
    }

    @PostMapping("/rnafile-syntax")
    private RNAFile checkSyntax(RNAFile rnaFile) {
        try {
            rnaFile = RNAFileConstructor.getInstance().construct(rnaFile.getContent(), rnaFile.getFileName());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The file has a wrong syntax");
        }
        return rnaFile;
    }
}
