package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.service.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// TODO: aggiungere controlli sia qui che sul service
@RestController
@RequestMapping("/clean")
public class CleanerController {

    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/merge-db-lines/{fileName}")
    public RNAFile cleanDBmergingLines(@RequestBody Map<String, Object> content, @PathVariable String fileName) {
        return this.cleanerService.mergeDBLines(TranslatorController.getRNAFileOf((String) content.get("content"), fileName));
    }

    @PostMapping("/remove-header/{fileName}")
    public RNAFile removeHeader(@RequestBody Map<String, Object> content, @PathVariable String fileName) {
        return this.cleanerService.removeHeader(TranslatorController.getRNAFileOf((String) content.get("content"), fileName));
    }

    @PostMapping("/remove-lines-start-with/{fileName}")
    public RNAFile removeLinesStartingWith(@PathVariable String prefix, @RequestBody Map<String, Object> content, @PathVariable String fileName) {
        return this.cleanerService.removeLinesStartingWith(TranslatorController.getRNAFileOf((String) content.get("content"), fileName), prefix);
    }

    @PostMapping("/remove-lines-contains/{fileName}")
    public RNAFile removeLinesContaining(@PathVariable String word, @RequestBody Map<String, Object> content, @PathVariable String fileName) {
        return this.cleanerService.removeLinesContaining(TranslatorController.getRNAFileOf((String) content.get("content"), fileName), word);
    }

    @PostMapping("/remove-white-spaces/{fileName}")
    public RNAFile removeWhiteSpaces(@RequestBody Map<String, Object> content, @PathVariable String fileName) {
        return this.cleanerService.removeWhiteSpaces(TranslatorController.getRNAFileOf((String) content.get("content"), fileName));
    }

}
