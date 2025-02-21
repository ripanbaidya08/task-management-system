package com.organize.ripan.repository;

import com.organize.ripan.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    public List<Submission> findByTaskId(Integer taskId);
}
