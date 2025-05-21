package com.sicong.spring.taskmanager.controller;
import com.sicong.spring.taskmanager.model.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sicong.spring.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sicong.spring.taskmanager.model.Task;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String name) {
		try {
			List<Task> tasks = new ArrayList<Task>();

			if (name == null)
				taskService.getAllTasks().forEach(tasks::add);
			else
				taskService.getTaskByName(name).forEach(tasks::add);

			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) {
		Optional<Task> taskData = taskService.getTaskById(id);

		if (taskData.isPresent()) {
			return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		try {
			Task _task = taskService.createTask(task);
			return new ResponseEntity<>(_task, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		Optional<Task> taskData = taskService.getTaskById(id);

		if (taskData.isPresent()) {
			Task _task = taskData.get();
			_task.setName(task.getName());
			_task.setDetails(task.getDetails());
			_task.setStatus(task.getStatus());
			_task.setDueDate(task.getDueDate());
			return new ResponseEntity<>(taskService.updateTask(_task), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
		try {
			taskService.deleteTask(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<HttpStatus> deleteAllTasks() {
		try {
			taskService.deleteAllTasks();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tasks/status")
	public ResponseEntity<List<Task>> findByStatus(@RequestParam String status) {
		try {
			TaskStatus enumStatus = TaskStatus.valueOf(status.toUpperCase());
			List<Task> tasks = taskService.getTaskByStatus(enumStatus);

			if (tasks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tasks, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
