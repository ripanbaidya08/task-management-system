package com.organize.ripan.service;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.exception.TaskSubmissionException;
import com.organize.ripan.model.Submission;

import java.util.List;

public interface SubmissionService {
    Submission submitTask(Integer taskId, String githubLink, Integer userId, String jwt) throws TaskSubmissionException, TaskNotFoundException, NotAdminException;

    Submission getTaskSubmissionById(Integer submissionId) throws TaskSubmissionException;

    List<Submission> getAllSubmission(); //

    List<Submission> getTaskSubmissionsByTaskId(Integer taskId) throws TaskSubmissionException;

    Submission acceptOrDeclinedSubmission(Integer submissionId, String status) throws TaskNotFoundException; // admin can accept or declined a task

    void deleteTaskSubmission(Integer submissionId, Integer userId) throws TaskSubmissionException;

    Submission updateTaskSubmission(Integer submissionId, String githubLink, Integer userId) throws TaskSubmissionException;
}
