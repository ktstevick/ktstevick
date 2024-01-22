package com.techelevator.controller;

import com.techelevator.model.DiseaseFact;
import com.techelevator.services.DiseaseFactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DiseaseFactsController {

    private DiseaseFactService diseaseFactService;

    public DiseaseFactsController(DiseaseFactService diseaseFactService) {
        this.diseaseFactService = diseaseFactService;
    }

    //Methods
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/diseases/{name}", method = RequestMethod.GET)
    public List<DiseaseFact> getDiseaseFactByName(@PathVariable String name) {
        List<DiseaseFact> results = diseaseFactService.getDiseaseFactByName(name);
        return results;

    }
}

