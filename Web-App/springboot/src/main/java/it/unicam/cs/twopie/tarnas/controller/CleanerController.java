package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.service.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/clean")
public class CleanerController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/merge-db-lines")
    public RNAFile cleanDBmergingLines(@RequestBody RNAFile rnaFile) {
        var cleanedFile = this.cleanerService.mergeDBLines(rnaFile);
        if (cleanedFile.isPresent())
            return cleanedFile.get();
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot merge lines of "+rnaFile.getFormat().getName()+" format");
    }

    @PostMapping("/remove-header")
    public RNAFile removeHeader(@RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeHeader(rnaFile);
    }

    @PostMapping("/remove-lines-start-with/{prefix}")
    public RNAFile removeLinesStartingWith(@PathVariable Character prefix, @RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeLinesStartingWith(rnaFile, prefix);
    }

    @PostMapping("/remove-lines-contains/{word}")
    public RNAFile removeLinesContaining(@PathVariable String word, @RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeLinesContaining(rnaFile, word);
    }

    @PostMapping("/remove-white-spaces")
    public RNAFile removeWhiteSpaces(@RequestBody RNAFile rnaFile) {
        return this.cleanerService.removeWhiteSpaces(rnaFile);
    }

    private RNAFile checkSyntax(RNAFile rnaFile) {
        try {
            rnaFile = RNAFileConstructor.getInstance().construct(rnaFile.getContent(), rnaFile.getFileName());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The file has a wrong syntax");
        }
        return rnaFile;
    }

}
