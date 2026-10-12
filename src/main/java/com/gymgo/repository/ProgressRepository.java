
package com.gymgo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gymgo.model.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    List<Progress> findByUserIdOrderByRecordedDateDesc(Long userId);
}
