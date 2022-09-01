package com.thymeleaf.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.demo.entity.Student;
import com.thymeleaf.demo.repository.StudentRepository;

@Service
public class StudentService {
 
	@Autowired
	private StudentRepository studentRepository;
	

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);

		Student theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find student id - " + theId);
		}

		return theEmployee;
	}

	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}

	public void deleteById(int theId) {
		 studentRepository.deleteById(theId);
	}
}
