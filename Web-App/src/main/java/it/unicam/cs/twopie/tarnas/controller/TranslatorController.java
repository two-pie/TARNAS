package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import it.unicam.cs.twopie.tarnas.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/translation")
public class TranslatorController {

    @Autowired
    private TranslatorService translatorService;

    @GetMapping("/translate")
    public RNAFile translate(@RequestBody RNAFile rnaFile, @RequestBody RNAFormat dstFormat) {
        var translatedRnaFile = this.translatorService.translateTo(rnaFile, dstFormat);
        if (translatedRnaFile.isPresent())
            return translatedRnaFile.get();
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/available-translations/{dstFormat}")
    public List<RNAFormat> translate(@PathVariable RNAFormat dstFormat) {
        return this.translatorService.getAvailableTranslations(dstFormat);
    }

}
