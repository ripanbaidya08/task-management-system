package com.organize.ripan.service;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.exception.TaskSubmissionException;
import com.organize.ripan.model.Submission;
import com.organize.ripan.model.TaskDto;
import com.organize.ripan.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired private SubmissionRepository submissionRepository;
    @Autowired private TaskService taskService;
    @Autowired private UserService userService;

    @Override
    public Submission submitTask(Integer taskId, String githubLink, Integer userId, String jwt)
            throws TaskSubmissionException, TaskNotFoundException, NotAdminException {
        TaskDto task = taskService.getTaskById(taskId, jwt);
        if(task != null){
            Submission submission = new Submission();

            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());

            return submissionRepository.save(submission);
        } else {
            throw new TaskSubmissionException("Task not found with id "+taskId);
        }
    }

    @Override
    public Submission getTaskSubmissionById(Integer submissionId) throws TaskSubmissionException {
        return submissionRepository.findById(submissionId)
                .orElseThrow(()-> new TaskSubmissionException("Submission not found with id "+submissionId));
    }

    @Override
    public List<Submission> getAllSubmission() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Integer taskId) throws TaskSubmissionException {
        return submissionRepository.findByTaskId(taskId);
    }

    /**
     * Admin will set the status. status would be accepted or declined.
     * this will come from the frontend.
     * @param submissionId
     * @param status
     * @return
     * @throws TaskNotFoundException
     */
    @Override
    public Submission acceptOrDeclinedSubmission(Integer submissionId, String status) throws TaskNotFoundException {
        Submission submission = getTaskSubmissionById(submissionId);

        submission.setStatus(status); // update the submission status
        if(status.equals("ACCEPTED")){
            taskService.completeTask(submission.getTaskId());
        }
        return submissionRepository.save(submission);
    }

    /**
     *
     * @param submissionId
     * @param userId
     * @throws TaskSubmissionException
     */
    @Override
    public void deleteTaskSubmission(Integer submissionId, Integer userId) throws TaskSubmissionException {
        Submission submission = getTaskSubmissionById(submissionId); // this will throw exception if task is not found

        if(!submission.getUserId().equals(userId)) {
            throw new TaskSubmissionException("You don't have permission to delete this submission");
        }

        submissionRepository.deleteById(submission.getSubmissionId());
    }

    @Override
    public Submission updateTaskSubmission(Integer submissionId, String githubLink, Integer userId) throws TaskSubmissionException {
        Submission isSubmissionFound = getTaskSubmissionById(submissionId);

        if(!isSubmissionFound.getUserId().equals(userId)){
            throw new TaskSubmissionException("You don't have permission to delete this submission");
        }

        isSubmissionFound.setGithubLink(githubLink);
        return submissionRepository.save(isSubmissionFound);
    }
}