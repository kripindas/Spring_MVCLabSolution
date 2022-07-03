package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.model.Student;
import com.greatlearning.services.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	@RequestMapping("/addStudentForm")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setFirstName(name);
			student.setDepartment(department);
			student.setCountry(country);
		} else {
			student = new Student(name, department, country);
		}
		studentService.saveStudent(student);
		return "redirect:/student/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = studentService.findById(id);
		model.addAttribute("Student", student);
		return "student-form";
	}

	@RequestMapping("/list")
	public String listBooks(Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("Students", students);
		return "list-students";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteStudent(id);
		return "redirect:/student/list";
	}
}
