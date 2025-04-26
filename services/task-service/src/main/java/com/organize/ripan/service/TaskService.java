package com.organize.ripan.service;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.model.Task;
import com.organize.ripan.model.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task, String requesterRole) throws TaskException, NotAdminException;
    Task getTaskById(Integer taskId) throws TaskNotFoundException;

    /**
     * We can apply filter
     * Which task we want to show
     * @param status
     * @return Task
     */
    List<Task> getAllTasks(TaskStatus status);

    Task updateTask(Integer taskId, Task task, Integer userId) throws TaskException, TaskNotFoundException;
    void deleteTask(Integer taskId) throws TaskException, TaskNotFoundException;

    // Admin will assign task to any User
    Task assignedToUser(Integer userId, Integer taskId) throws TaskException, TaskNotFoundException;

    List<Task> assignedUsersTask(Integer userId, TaskStatus status) throws Exception;

    Task completeTask(Integer taskId) throws TaskException, TaskNotFoundException;
}
