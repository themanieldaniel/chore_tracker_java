package com.MiniAccount.Server.Dao;

import com.MiniAccount.Server.Models.Chore;

import java.util.List;

/*
Chore table

Stores Chore information

| Field         | Description                     |
| ------------- | ------------------------------- |
| id            | unique id                       |
| description   | description of Chore            |
| amount        | amount of money earned by Chore |
| completed     | whether Chore is completed      |
| dateCompleted | date Chore was completed        |

 */

public interface ChoreDao {
    // Get all Chores
    List<Chore> getAllChores();

    // Get Chore by id
    Chore getChoreById(Long id);

    // Add Chore
    Chore addChore(Chore chore);

    // Update Chore
    Chore updateChore(Chore chore);

    // Delete Chore by id
    int deleteChoreById(Long id);
}
