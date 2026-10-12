
package com.gymgo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fitness_progress")
public class FitnessProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberEmail;
    private Double weightKg;
    private Double waistCm;
    private String workoutType;
    private String workoutDetails;
    private Integer caloriesBurned;
    private Integer steps;
    private Integer waterMl;
    private LocalDate progressDate;

    public FitnessProgress() {
    }

    public Long getId() { return id; }

    public String getMemberEmail() { return memberEmail; }
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public Double getWeightKg() { return weightKg; }
    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Double getWaistCm() { return waistCm; }
    public void setWaistCm(Double waistCm) {
        this.waistCm = waistCm;
    }

    public String getWorkoutType() { return workoutType; }
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutDetails() { return workoutDetails; }
    public void setWorkoutDetails(String workoutDetails) {
        this.workoutDetails = workoutDetails;
    }

    public Integer getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Integer getSteps() { return steps; }
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getWaterMl() { return waterMl; }
    public void setWaterMl(Integer waterMl) {
        this.waterMl = waterMl;
    }

    public LocalDate getProgressDate() { return progressDate; }
    public void setProgressDate(LocalDate progressDate) {
        this.progressDate = progressDate;
    }
}
