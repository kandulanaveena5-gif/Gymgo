
package com.gymgo.controller;

import com.gymgo.model.FitnessProgress;
import com.gymgo.repository.FitnessProgressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5175",
    "http://localhost:8080"
})
public class ProgressController {

    private final FitnessProgressRepository repository;

    public ProgressController(FitnessProgressRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> saveProgress(
            @RequestBody FitnessProgress progress) {

        if (progress.getMemberEmail() == null
                || progress.getMemberEmail().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Member email is required.");
        }

        if (progress.getProgressDate() == null) {
            progress.setProgressDate(LocalDate.now());
        }

        FitnessProgress saved = repository.save(progress);

        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<FitnessProgress> getProgress(
            @RequestParam String email) {

        return repository
                .findByMemberEmailOrderByProgressDateDesc(email);
    }
}
