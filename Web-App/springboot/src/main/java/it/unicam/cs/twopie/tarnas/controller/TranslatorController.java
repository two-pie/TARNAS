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

    @PostMapping("/translate/{fileName}/{dstFormat}")
    public RNAFile translate(@PathVariable String fileName, @RequestBody Map<String, Object> content, @PathVariable RNAFormat dstFormat) {
        var translatedRnaFile = this.translatorService.translateTo(getRNAFileOf((String) content.get("content"), fileName), dstFormat);
        if (translatedRnaFile.isPresent())
            return translatedRnaFile.get();
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/available-translations/{dstFormat}")
    public List<RNAFormat> getAvaiableTranslation(@PathVariable RNAFormat dstFormat) {
        return this.translatorService.getAvailableTranslations(dstFormat);
    }

    public static RNAFile getRNAFileOf(String content, String fileName) {
        RNAFile rnaFile;
        try {
            rnaFile = RNAFileConstructor.getInstance().construct(Arrays.asList(content.split("\n")), fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rnaFile;
    }

}
