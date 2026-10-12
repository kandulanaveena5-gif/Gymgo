package com.gymgo.service;

import com.gymgo.model.GymClass;
import com.gymgo.repository.GymClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymClassService {

    private final GymClassRepository repository;

    public GymClassService(GymClassRepository repository) {
        this.repository = repository;
    }

    public List<GymClass> getAllClasses() {
        return repository.findAll();
    }

    public Optional<GymClass> getClassById(Long id) {
        return repository.findById(id);
    }

    public GymClass saveClass(GymClass gymClass) {
        return repository.save(gymClass);
    }

    public void deleteClass(Long id) {
        repository.deleteById(id);
    }
}

