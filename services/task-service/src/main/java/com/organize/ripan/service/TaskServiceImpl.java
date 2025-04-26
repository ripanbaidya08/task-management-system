package com.organize.ripan.service;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.model.Task;
import com.organize.ripan.model.TaskStatus;
import com.organize.ripan.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired private TaskRepository taskRepository;

    /**
     * Creates a new task. Only an admin user can create a task.
     *
     * @param task The task details provided by the user.
     * @param requesterRole The role of the user requesting task creation.
     * @return The newly created task.
     * @throws TaskException If there is an issue with task creation.
     * @throws NotAdminException If the user is not an admin.
     */
    public Task createTask(Task task, String requesterRole) throws TaskException, NotAdminException {
        // Validate if the requester has admin privileges
        if (!"ROLE_ADMIN".equals(requesterRole))  throw new NotAdminException("Access denied: User is not an admin.");

        // Validate task details (e.g., title and description should not be empty)
        // The Task title and the description can't be empty or null.
        if (task.getTitle() == null || task.getTitle().isEmpty()) throw new TaskException("Task title cannot be empty.");
        if (task.getDescription() == null || task.getDescription().isEmpty())  throw new TaskException("Task description cannot be empty.");

        // Create a new task object and set default values
        Task createdTask = new Task();

        createdTask.setTitle(task.getTitle());
        createdTask.setDescription(task.getDescription());
        createdTask.setDeadline(task.getDeadline());
        createdTask.setImage(task.getImage());
        createdTask.setTechStacks(task.getTechStacks());

        createdTask.setAssignedUserId(null); // Initially, the task is not assigned to anyone

        // Initially, we never assigned any task to any user, and if, but still status should not be anything
        // but it would be PENDING
        createdTask.setTaskStatus(TaskStatus.PENDING); // Default status is PENDING,
        createdTask.setCreatedAt(LocalDateTime.now());

        // Save the task in the database
        return taskRepository.save(createdTask);
    }

    @Override
    public Task getTaskById(Integer taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task not found with id"+taskId));
    }

    /**
     * Admin can get all the task, and he can apply filters and based on that Admin can get the list of task.
     *
     * @param status
     */
    @Override
    public List<Task> getAllTasks(TaskStatus status) {
        List<Task> tasks = taskRepository.findAll(); // get the all tasks
        if(status == null) return tasks;

        return tasks.stream()
                .filter(task -> task.getTaskStatus() != null && task.getTaskStatus() == status)
                .collect(Collectors.toList());

    }

    @Override
    public Task updateTask(Integer taskId, Task task, Integer userId) throws TaskException, TaskNotFoundException {
        Task existingTask = getTaskById(taskId); // get the existing task by its id

        if(existingTask.getTitle() != null) existingTask.setTitle(task.getTitle());
        if(existingTask.getDescription() != null) existingTask.setDescription(task.getDescription());
        if(existingTask.getImage() != null) existingTask.setImage(task.getImage());
        if(existingTask.getDeadline() != null) existingTask.setDeadline(task.getDeadline());
        if(existingTask.getTaskStatus() != null) existingTask.setTaskStatus(task.getTaskStatus());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Integer taskId) throws TaskException, TaskNotFoundException {
        Task existingTask = getTaskById(taskId);
        taskRepository.delete(existingTask);
    }

    @Override
    public Task assignedToUser(Integer userId, Integer taskId) throws TaskException, TaskNotFoundException {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setTaskStatus(TaskStatus.ASSIGNED);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsersTask(Integer userId, TaskStatus status) throws Exception {
        List<Task> tasks = taskRepository.findAll();
        if(status == null) return tasks;

        return tasks.stream()
                .filter((task) -> task.getTaskStatus() != null && task.getTaskStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Task completeTask(Integer taskId) throws TaskException, TaskNotFoundException {
        Task task = getTaskById(taskId);
        task.setTaskStatus(TaskStatus.COMPLETED);
        return taskRepository.save(task);
    }
}
