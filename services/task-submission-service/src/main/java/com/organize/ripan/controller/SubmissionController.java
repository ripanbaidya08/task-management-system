package com.organize.ripan.controller;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.model.Submission;
import com.organize.ripan.model.UserDto;
import com.organize.ripan.service.SubmissionService;
import com.organize.ripan.service.TaskService;
import com.organize.ripan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    @Autowired private SubmissionService submissionService;
    @Autowired private TaskService taskService;
    @Autowired private UserService userService;

    @PostMapping
    public ResponseEntity<Submission> submitTask(@RequestParam Integer taskId,
                                                 @RequestParam String githubLink,
                                                 @RequestHeader("Authorization") String jwt) throws TaskNotFoundException, NotAdminException {
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId, githubLink, user.getId(), jwt);

        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Integer id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception    {
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(id);

        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllTaskSubmissionsByTaskId(@PathVariable Integer taskId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getTaskSubmissionsByTaskId(taskId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmission(@RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submission = submissionService.getAllSubmission();
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDeclinedSubmission(@PathVariable Integer id, // submission id
                                                                 @RequestParam String status,
                                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        // status: ACCEPT
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptOrDeclinedSubmission(id, status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTaskSubmission(@PathVariable Integer id,
                                                       @RequestHeader("Authorization") String jwt){
        UserDto user = userService.getUserProfile(jwt);
        submissionService.deleteTaskSubmission(id, user.getId());
        return new ResponseEntity<>("Task deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}/update-link")
    public ResponseEntity<Submission> updateTaskSubmission(@PathVariable Integer id,
                                                           @RequestParam String githubLink,
                                                           @RequestHeader("Authorization") String jwt){
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.updateTaskSubmission(id, githubLink, user.getId());
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}
