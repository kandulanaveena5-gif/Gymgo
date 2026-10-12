package com.gymgo.controller;

import com.gymgo.model.GymClass;
import com.gymgo.repository.GymClassRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5175"
})
public class GymClassController {

    private final GymClassRepository repository;

    public GymClassController(GymClassRepository repository) {
        this.repository = repository;
    }

    // Get all gym classes
    @GetMapping
    public List<GymClass> getAllClasses() {
        return repository.findAll();
    }

    // Add a new gym class
    @PostMapping
    public GymClass addClass(@RequestBody GymClass gymClass) {
        return repository.save(gymClass);
    }

    // Delete a gym class
    @DeleteMapping("/{id}")
    public void deleteClass(@PathVariable Long id) {
        repository.deleteById(id);
    }
}



