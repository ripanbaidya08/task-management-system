package com.organize.ripan.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {
    private Integer id;

    private String title;
    private String description;
    private String image;
    private Integer assignedUserId; // I'd of the user to which the task is assigned

    private List<String> techStacks = new ArrayList<>(); // List of tech stacks we will use it for that task

    private TaskStatus taskStatus; // Status of the task, like it can be PENDING, ASSIGNED, COMPLETED
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}
