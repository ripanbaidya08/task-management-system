package com.organize.ripan.service;

import com.organize.ripan.exception.NotAdminException;
import com.organize.ripan.exception.TaskException;
import com.organize.ripan.exception.TaskNotFoundException;
import com.organize.ripan.model.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE", url = "http://localhost:8002")
public interface TaskService {
    @GetMapping("/api/tasks/{id}")
    public TaskDto getTaskById(@PathVariable Integer id,
                               @RequestHeader("Authorization") String jwt) throws NotAdminException, TaskNotFoundException;

    @PutMapping("/api/tasks/{id}/complete")
    public TaskDto completeTask(@PathVariable Integer id) throws TaskException, TaskNotFoundException;
}
