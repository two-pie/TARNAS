package it.unicam.cs.twopie.tarnas.controller;

import it.unicam.cs.twopie.tarnas.service.CleanerService;
import it.unicam.cs.twopie.tarnas.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CleanerController {

    @Autowired
    private CleanerService cleanerService;
}
