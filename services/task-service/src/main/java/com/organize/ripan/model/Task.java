package com.organize.ripan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
