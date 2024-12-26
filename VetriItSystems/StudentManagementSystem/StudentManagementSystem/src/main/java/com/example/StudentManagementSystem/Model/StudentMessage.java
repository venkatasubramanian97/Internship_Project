package com.example.StudentManagementSystem.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_messages")
public class StudentMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String content;
	private String description;
	private LocalDateTime sentTime;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public StudentMessage() {
	}

	public StudentMessage(int id, String content, String description, LocalDateTime sentTime, Student student,
			Admin admin) {
		super();
		this.id = id;
		this.content = content;
		this.description = description;
		this.sentTime = sentTime;
		this.student = student;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getSentTime() {
		return sentTime;
	}

	public void setSentTime(LocalDateTime sentTime) {
		this.sentTime = sentTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getStudentId() {
		return student != null ? student.getId() : null;
	}

	public Integer getAdminId() {
		return admin != null ? admin.getAdminId() : null;
	}

}
