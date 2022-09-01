package com.thymeleaf.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymeleaf.demo.entity.Student;
import com.thymeleaf.demo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	

	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		// get employees from db
		List<Student> theStudents = studentService.findAll();
		
		// add to the spring model
		theModel.addAttribute("students", theStudents);
		
		return "list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Student theStudent = new Student();
		
		theModel.addAttribute("student", theStudent);
		
		return "student-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {
		
		// get the employee from the service
		Student theStudent = studentService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		
		// send over to our form
		return "student-form";			
	}
	
	
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		
		// save the employee
		studentService.save(theStudent);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		
		// delete the employee
		studentService.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/students/list";
		
	}
}
