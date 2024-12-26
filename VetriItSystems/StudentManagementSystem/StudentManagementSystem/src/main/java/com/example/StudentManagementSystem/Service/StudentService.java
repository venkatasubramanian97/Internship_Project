package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	// Add Student
	public void addStudent(Student student) {
		this.studentRepository.save(student);
	}

	// Get All Users
	public List<Student> getAll() {
		List<Student> student = this.studentRepository.findAll();
		return student;
	}

	// Get Single User
	public Student getStudent(int id) {
		Optional<Student> optional = this.studentRepository.findById(id);
		Student student = optional.get();
		return student;
	}

	// Get Single Student
	public Optional<Student> getStudentId(int studentId) {
		return studentRepository.findById(studentId); // Assuming you're using Spring Data JPA
	}

	// Get Single User By Email
	public Student getStudentByEmail(String email) {
		Student student = this.studentRepository.findByStudentEmail(email);
		return student;
	}

	// Update Student
	public void updateStudent(Student student, int id) {
		student.setId(id);
		this.studentRepository.save(student);
	}

	// Delete Student
	public void deleteStudent(int id) {
		this.studentRepository.deleteById(id);
	}

	// Validating user login
	public boolean validateLoginCredentials(String email, String password) {
		Student Student = this.studentRepository.findByStudentEmail(email);

		if (Student != null && Student.getStudentPassword().equals(password)
				&& Student.getStudentEmail().equals(email)) {
			return true;
		}
		return false;
	}

}
