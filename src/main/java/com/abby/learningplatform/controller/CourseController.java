package com.abby.learningplatform.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String listCourses(
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String difficulty,
			@RequestParam(defaultValue = "titleAsc") String sort,
			@RequestParam(defaultValue = "0") int page,
			Model model) {
		String selectedSort = normalizeSort(sort);
		Pageable pageable = PageRequest.of(page, 5, getSort(selectedSort));
		CourseCategory selectedCategory = parseCategory(category);
		DifficultyLevel selectedDifficulty = parseDifficulty(difficulty);
		Page<Course> coursePage;

		if (selectedCategory != null && selectedDifficulty != null) {
			coursePage = courseRepository.findByCategoryAndDifficulty(selectedCategory, selectedDifficulty, pageable);
		} else if (selectedCategory != null) {
			coursePage = courseRepository.findByCategory(selectedCategory, pageable);
		} else if (selectedDifficulty != null) {
			coursePage = courseRepository.findByDifficulty(selectedDifficulty, pageable);
		} else {
			coursePage = courseRepository.findAll(pageable);
		}

		model.addAttribute("coursePage", coursePage);
		model.addAttribute("courses", coursePage.getContent());
		model.addAttribute("categories", CourseCategory.values());
		model.addAttribute("difficultyLevels", DifficultyLevel.values());
		model.addAttribute("selectedCategory", category == null ? "" : category);
		model.addAttribute("selectedDifficulty", difficulty == null ? "" : difficulty);
		model.addAttribute("selectedSort", selectedSort);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", coursePage.getTotalPages());
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

	private CourseCategory parseCategory(String category) {
		try {
			return (category == null || category.isBlank()) ? null : CourseCategory.valueOf(category);
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	private DifficultyLevel parseDifficulty(String difficulty) {
		try {
			return (difficulty == null || difficulty.isBlank()) ? null : DifficultyLevel.valueOf(difficulty);
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	private Sort getSort(String sort) {
		return switch (sort) {
			case "titleDesc" -> Sort.by("title").descending();
			case "durationAsc" -> Sort.by("durationHours").ascending();
			case "durationDesc" -> Sort.by("durationHours").descending();
			case "titleAsc" -> Sort.by("title").ascending();
			default -> Sort.by("title").ascending();
		};
	}

	private String normalizeSort(String sort) {
		return switch (sort) {
			case "titleDesc", "durationAsc", "durationDesc", "titleAsc" -> sort;
			default -> "titleAsc";
		};
	}
}
