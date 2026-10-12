
package com.gymgo.repository;

import com.gymgo.model.FitnessProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FitnessProgressRepository
        extends JpaRepository<FitnessProgress, Long> {

    List<FitnessProgress>
        findByMemberEmailOrderByProgressDateDesc(String memberEmail);
}
