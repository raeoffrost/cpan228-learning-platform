package com.abby.learningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abby.learningplatform.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
