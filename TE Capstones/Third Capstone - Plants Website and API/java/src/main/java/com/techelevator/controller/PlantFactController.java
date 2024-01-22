package com.techelevator.controller;


import com.techelevator.model.PlantFact;
import com.techelevator.services.RestPlantFactService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class PlantFactController {

    private RestPlantFactService plantFactService;

    public PlantFactController(RestPlantFactService plantFactService) {
        this.plantFactService = plantFactService;
    }

    //Methods
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "{name}", method = RequestMethod.GET)
    public List<PlantFact> getPlantFactByName(@PathVariable String name) {
        List<PlantFact> results = plantFactService.getPlantFactByName(name);
        return results;

    }
}
