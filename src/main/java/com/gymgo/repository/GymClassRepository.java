package com.gymgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gymgo.model.GymClass;

public interface GymClassRepository
        extends JpaRepository<GymClass, Long> {
}