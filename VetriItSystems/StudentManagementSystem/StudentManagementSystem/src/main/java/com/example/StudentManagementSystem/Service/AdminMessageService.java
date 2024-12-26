package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.AdminMessage;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Repository.AdminMessageRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminMessageService {

	@Autowired
	private AdminMessageRepository adminMessageRepository;

	// Add Message
	public void adminaddMessage(AdminMessage adminMessage) {
		this.adminMessageRepository.save(adminMessage);
	}

	// Get All Admin Message
	public List<AdminMessage> getAll() {
		List<AdminMessage> adminMessage = this.adminMessageRepository.findAll();
		return adminMessage;
	}

	// Get Single Admin Message
	public AdminMessage admingetMessage(int id) {
		Optional<AdminMessage> optional = this.adminMessageRepository.findById(id);
		AdminMessage adminMessage = optional.get();
		return adminMessage;
	}

	// Update Admin Message
	public void adminupdateMessage(int id, AdminMessage adminMessage) {
		adminMessage.setId(id);
		this.adminMessageRepository.save(adminMessage);
	}

	// Delete Admin Message
	public void delete(int id) {
		this.adminMessageRepository.deleteById(id);
	}

	// Delete all messages by Admin
	@Transactional
	public void deleteMessagesByAdmin(int adminId) {
		adminMessageRepository.deleteAllByAdmin_AdminId(adminId);
	}

	// Delete all messages by Student
	@Transactional
	public void deleteMessagesByStudent(int id) {
		adminMessageRepository.deleteAllByStudent_id(id);
	}

	// Get Meesage With Admin
	public List<AdminMessage> getMessageByAdmin(Admin admin) {
		return this.adminMessageRepository.findMessageByAdmin(admin);
	}

	// Get Message With Student
	public List<AdminMessage> getMessageByStudent(Student student) {
		return this.adminMessageRepository.findMessageByStudent(student);
	}

}
