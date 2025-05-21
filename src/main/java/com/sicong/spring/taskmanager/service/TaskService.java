package com.sicong.spring.taskmanager.service;
import com.sicong.spring.taskmanager.model.TaskStatus;

import com.sicong.spring.taskmanager.model.Task;
import com.sicong.spring.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public  List<Task> getTaskByName(String name){
        return taskRepository.findByNameContaining(name);
    }
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    public Task updateTask(Task existingTask) {
        return taskRepository.save(existingTask);
    }
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }
    public List<Task> getTaskByStatus(TaskStatus  status) {
        return taskRepository.findByStatus(status);
    }
}

