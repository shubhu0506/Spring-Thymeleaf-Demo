package com.thymeleaf.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thymeleaf.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

}
