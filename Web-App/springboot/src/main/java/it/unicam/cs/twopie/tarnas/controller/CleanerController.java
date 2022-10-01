package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.service.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO: aggiungere controlli sia qui che sul service
@RestController
public class CleanerController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping
    public RNAFile cleanDBmergingLines(@RequestBody RNAFile rnaFile) {
        return this.cleanerService.mergeDBLines(rnaFile);
    }

    @PostMapping
    public RNAFile removeHeader(@RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeHeader(rnaFile);
    }

    @PostMapping
    public RNAFile removeLinesStartingWith(@RequestBody String prefix, @RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeLinesStartingWith(rnaFile, prefix);
    }

    @PostMapping
    public RNAFile removeLinesContaining(@RequestBody String word, @RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeLinesContaining(rnaFile, word);
    }

    @PostMapping
    public RNAFile removeWhiteSpaces(@RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeWhiteSpaces(rnaFile);
    }
}
