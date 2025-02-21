package com.organize.ripan.controller;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.model.Task;
import com.organize.ripan.model.TaskStatus;
import com.organize.ripan.model.UserDto;
import com.organize.ripan.service.TaskService;
import com.organize.ripan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired private TaskService taskService;
    @Autowired private UserService userService;

    /**
     * Create a task, and admin have the authority to create the task.
     * */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws NotAdminException {
        UserDto user = userService.getUserProfile(jwt);

        Task createdTask = taskService.createTask(task, user.getRole());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id,
                                            @RequestHeader("Authorization") String jwt) throws NotAdminException, TaskNotFoundException {
        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false)TaskStatus status,
                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false)TaskStatus status,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Task> tasks = taskService.getAllTasks(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Admin will crete a task
     * After creation of task, admin would be responsible to assign the task to other user.
     * */
    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Task> AssignedTaskToUser(@PathVariable Integer id, // task_id
                                                   @PathVariable Integer userId,
                                                   @RequestHeader("Authorization") String jwt) throws TaskException, TaskNotFoundException {
        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(userId, id); // (userId, taskId)
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id,
                                           @RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws TaskException, TaskNotFoundException {
        UserDto user = userService.getUserProfile(jwt);

        Task updatedTask = taskService.updateTask(id, task, user.getId());
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Integer id,
                                             @RequestHeader("Authorization") String jwt) throws TaskException, TaskNotFoundException {
        UserDto user = userService.getUserProfile(jwt);
        Task task = taskService.completeTask(id); // task id
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer id,
                                           @RequestHeader("Authorization") String jwt) throws TaskException, TaskNotFoundException {
        UserDto user = userService.getUserProfile(jwt);
        taskService.deleteTask(id);
        return new ResponseEntity<>("Task with id "+id+"deleted Successfully",HttpStatus.OK);
    }

}
