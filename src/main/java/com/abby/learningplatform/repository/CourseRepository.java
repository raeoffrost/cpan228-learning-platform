package com.abby.learningplatform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abby.learningplatform.model.Course;
import com.abby.learningplatform.model.CourseCategory;
import com.abby.learningplatform.model.DifficultyLevel;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Page<Course> findByCategory(CourseCategory category, Pageable pageable);

	Page<Course> findByDifficulty(DifficultyLevel difficulty, Pageable pageable);

	Page<Course> findByCategoryAndDifficulty(CourseCategory category, DifficultyLevel difficulty, Pageable pageable);
}
