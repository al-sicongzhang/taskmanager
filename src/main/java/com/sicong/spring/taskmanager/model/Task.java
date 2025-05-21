package com.sicong.spring.taskmanager.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "details")
	private String details;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;


	@Column(name = "due_date")
	private LocalDate dueDate;

	public Task() {

	}

	public Task(String name, String details, TaskStatus status, LocalDate dueDate) {
		this.name = name;
		this.details = details;
		this.status = status;
		this.dueDate=dueDate;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name +
				", details=" + details + ", status=" + status + ", dueDate=" + dueDate +"]";
	}
}
