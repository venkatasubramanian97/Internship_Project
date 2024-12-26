package com.example.StudentManagementSystem.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String studentName;
	private String studentEmail;
	private String studentPassword;
	private String studentNumber;

	@OneToMany(mappedBy = "student")
	private List<AdminMessage> adminMessages;

	@OneToMany(mappedBy = "student")
	private List<Certificate> certificate;

	public Student() {
	}

	public Student(int id, String studentName, String studentEmail, String studentPassword, String studentNumber,
			List<AdminMessage> adminMessages, List<Certificate> certificate) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.studentNumber = studentNumber;
		this.adminMessages = adminMessages;
		this.certificate = certificate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public List<AdminMessage> getAdminMessages() {
		return adminMessages;
	}

	public void setAdminMessages(List<AdminMessage> adminMessages) {
		this.adminMessages = adminMessages;
	}

	public List<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(List<Certificate> certificate) {
		this.certificate = certificate;
	}

}
