package com.example.StudentManagementSystem.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private String adminNumber;
	private String otp;

	@OneToMany(mappedBy = "admin")
	private List<StudentMessage> studentMessages;

	public Admin() {
	}

	public Admin(int adminId, String adminName, String adminEmail, String adminPassword, String adminNumber, String otp,
			List<StudentMessage> studentMessages) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminNumber = adminNumber;
		this.studentMessages = studentMessages;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}

	public List<StudentMessage> getStudentMessages() {
		return studentMessages;
	}

	public void setStudentMessages(List<StudentMessage> studentMessages) {
		this.studentMessages = studentMessages;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
