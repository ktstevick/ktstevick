package com.techelevator.controller;

import com.techelevator.dao.JdbcPlantDao;
import com.techelevator.dao.PlantDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Plant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class PlantController {
    private final JdbcPlantDao plantDao;

    public PlantController(JdbcPlantDao plantDao) {
        this.plantDao = plantDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/plants", method = RequestMethod.GET)
    public List<Plant> getAllPlants() {
        return plantDao.getPlants();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/plants/{id}", method = RequestMethod.GET)
    public List<Plant> getPlantById(@PathVariable int id) {
        List<Plant> plant = plantDao.getPlantById(id);
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant Not Found");
        } else {
            return plantDao.getPlantById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/plants/garden/{id}", method = RequestMethod.GET)
    public List<Plant> getPlantByGardenId(@PathVariable int id) {
        List<Plant> plant = plantDao.getPlantById(id);
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant Not Found");
        } else {
            return plantDao.getPlantByGardenId(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/plants/user/{id}", method = RequestMethod.GET)
    public List<Plant> getPlantByUserId(@PathVariable int id) {
        List<Plant> plant = plantDao.getPlantById(id);
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant Not Found");
        } else {
            return plantDao.getPlantByUserId(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/plants", method = RequestMethod.POST)
    public List<Plant> create(@RequestBody @Valid Plant plant) {
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Event Not Created");
        } else {
            return plantDao.createPlant(plant);
        }
    }

    @RequestMapping(path = "/plants/{id}", method = RequestMethod.PUT)
    public List<Plant> update(@Valid @RequestBody Plant plant, @PathVariable int id){
        plant.setId(id);

        try{
            List<Plant> updatedPlant = plantDao.updatePlant(plant);
            return updatedPlant;
        }catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update");
        }
    }
}


