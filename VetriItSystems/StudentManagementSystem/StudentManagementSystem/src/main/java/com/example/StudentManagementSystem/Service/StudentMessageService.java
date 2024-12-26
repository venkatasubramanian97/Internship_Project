package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Model.StudentMessage;
import com.example.StudentManagementSystem.Repository.StudentMessageRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentMessageService {

	@Autowired
	private StudentMessageRepository studentmessageRepository;

	// Add Student Message
	public void studentaddMessage(StudentMessage studenMmessage) {
		this.studentmessageRepository.save(studenMmessage);
	}

	// Get AllStudent Message
	public List<StudentMessage> getAll() {
		List<StudentMessage> studentMessage = this.studentmessageRepository.findAll();
		return studentMessage;
	}

	// Get Single Message
	public StudentMessage studentgetMessage(int id) {
		Optional<StudentMessage> optional = this.studentmessageRepository.findById(id);
		StudentMessage studentMessage = optional.get();
		return studentMessage;
	}

	// Update Student Message
	public void studentupdateMessage(int id, StudentMessage studentMessage) {
		studentMessage.setId(id);
		this.studentmessageRepository.save(studentMessage);
	}

	// Delete Student Message
	public void delete(int id) {
		this.studentmessageRepository.deleteById(id);
	}

	// Delete all messages by Admin
	@Transactional
	public void deleteMessagesByAdmin(int adminId) {
		studentmessageRepository.deleteAllByAdmin_AdminId(adminId);
	}

	// Delete all messages by Student
	@Transactional
	public void deleteMessagesByStudent(int id) {
		studentmessageRepository.deleteAllByStudent_id(id);
	}

	// Get Meesage With Student
	public List<StudentMessage> getMessageByStudent(Student student) {
		return this.studentmessageRepository.findMessageByStudent(student);
	}

	// Get Message With Admin
	public List<StudentMessage> getMessageByAdmin(Admin admin) {
		return this.studentmessageRepository.findMessageByAdmin(admin);
	}

}
