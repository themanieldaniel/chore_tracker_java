package com.MiniAccount.Server.Controllers;

import com.MiniAccount.Server.Dao.ChoreDao;
import com.MiniAccount.Server.Models.Chore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chore")
@RestController
public class ChoreController {

    private final ChoreDao choreDao;

    public ChoreController(ChoreDao choreDao) {
        this.choreDao = choreDao;
    }

    @GetMapping
    public List<Chore> getAllChores() {
        return choreDao.getAllChores();
    }

    @GetMapping("/{id}")
    public Chore getChoreById(@PathVariable Long id) {
        return choreDao.getChoreById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Chore addChore(@RequestBody Chore chore) {
        Chore createdChore = null;

        try {
            createdChore = choreDao.addChore(chore);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createdChore;
    }

    @PutMapping
    public Chore updateChore(@RequestBody Chore chore) {
        Chore updatedChore = null;

        try {
            updatedChore = choreDao.updateChore(chore);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedChore;
    }

    @DeleteMapping("/{id}")
    public int deleteChoreById(@PathVariable Long id) {
        return choreDao.deleteChoreById(id);
    }
}
