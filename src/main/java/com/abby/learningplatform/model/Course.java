package com.abby.learningplatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required.")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters.")
	private String title;

	@NotBlank(message = "Description is required.")
	@Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters.")
	private String description;

	@NotNull(message = "Category is required.")
	@Enumerated(EnumType.STRING)
	private CourseCategory category;

	@NotNull(message = "Difficulty level is required.")
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficulty;

	@NotNull(message = "Duration in hours is required.")
	@Min(value = 1, message = "Duration must be at least 1 hour.")
	@Max(value = 100, message = "Duration must be 100 hours or less.")
	private Integer durationHours;

	private LocalDateTime createdAt;

	public Course() {
	}

	public Course(Long id, String title, String description, CourseCategory category,
			DifficultyLevel difficulty, Integer durationHours, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.difficulty = difficulty;
		this.durationHours = durationHours;
		this.createdAt = createdAt;
	}

	@PrePersist
	public void setCreatedAt() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseCategory getCategory() {
		return category;
	}

	public void setCategory(CourseCategory category) {
		this.category = category;
	}

	public DifficultyLevel getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
