package com.techelevator.dao;

import com.techelevator.model.Garden;

import java.util.List;

public interface GardenDao {
    List<Garden> getGarden();
    Garden getGardenById(int GardenId);
    Garden createGarden(Garden garden);
    List<Garden> getGardenByUserId(int userId);
    Garden updateGarden(Garden garden);
}
