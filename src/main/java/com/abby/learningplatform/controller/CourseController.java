package com.abby.learningplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abby.learningplatform.repository.CourseRepository;

@Controller
public class CourseController {

	private final CourseRepository courseRepository;

	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping("/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "courses";
	}
}
