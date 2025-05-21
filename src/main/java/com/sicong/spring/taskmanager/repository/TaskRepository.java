package com.sicong.spring.taskmanager.repository;
import com.sicong.spring.taskmanager.model.TaskStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicong.spring.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByStatus( TaskStatus status);
	List<Task> findByNameContaining(String name);
}
