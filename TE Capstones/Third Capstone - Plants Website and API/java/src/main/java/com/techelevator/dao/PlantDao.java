package com.techelevator.dao;

import com.techelevator.model.Plant;

import java.util.List;

public interface PlantDao {
        List<Plant> getPlants();
        List<Plant> getPlantById(int id);
        List<Plant> getPlantByGardenId(int plantId);
        List<Plant> getPlantByUserId(int plantId);
        List<Plant> createPlant(Plant plant);
        List<Plant> updatePlant(Plant plant);
}
