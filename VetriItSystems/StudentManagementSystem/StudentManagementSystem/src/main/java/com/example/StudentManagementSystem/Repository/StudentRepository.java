package com.example.StudentManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findByStudentEmail(String email);
}
