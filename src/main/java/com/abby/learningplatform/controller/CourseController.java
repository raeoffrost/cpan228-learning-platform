package com.abby.learningplatform.controller;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.abby.learningplatform.model.Course;
import com.abby.learningplatform.model.CourseCategory;
import com.abby.learningplatform.model.DifficultyLevel;
import com.abby.learningplatform.repository.CourseRepository;

import jakarta.validation.Valid;

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

	@GetMapping("/courses/new")
	public String showCreateForm(Model model) {
		model.addAttribute("course", new Course());
		model.addAttribute("categories", CourseCategory.values());
		model.addAttribute("difficultyLevels", DifficultyLevel.values());
		return "course-form";
	}

	@PostMapping("/courses")
	public String createCourse(@Valid Course course, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", CourseCategory.values());
			model.addAttribute("difficultyLevels", DifficultyLevel.values());
			return "course-form";
		}

		courseRepository.save(course);
		return "redirect:/courses";
	}
}
