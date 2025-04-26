package com.organize.ripan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer submissionId;

    private Integer taskId;

    /**
     * Here, the Idea is, once user has Completed his task, he needs to push his submission to the github or any other platform
     * then he needs to put the link here, so that admin can check the submission.
     */
    private String githubLink;

    private Integer userId; // id or the user, who has submitted the task
    /**
     * by default, it's PENDING; once the user submits the task,
     * then the Submission will be checked by the respected admin and admin will update the status
     */
    private String status = "PENDING";
    private LocalDateTime submissionTime;
}
