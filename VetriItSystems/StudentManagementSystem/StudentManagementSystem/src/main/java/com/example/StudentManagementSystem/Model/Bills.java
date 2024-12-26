package com.example.StudentManagementSystem.Model;

import java.util.Base64;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bills")
public class Bills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String studentName;
	private String courseName;
	private String amount;
	private String issueDate;
	private String issuedBy;

	@Lob
	private byte[] billImage;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Bills() {
	}

	public Bills(int id, String studentName, String courseName, String amount, String issueDate, String issuedBy,
			byte[] billImage, Student student) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.courseName = courseName;
		this.amount = amount;
		this.issueDate = issueDate;
		this.issuedBy = issuedBy;
		this.billImage = billImage;
		this.student = student;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public byte[] getBillImage() {
		return billImage;
	}

	public void setBillImage(byte[] billImage) {
		this.billImage = billImage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getBillImageBase64() {
		if (billImage != null) {
			return Base64.getEncoder().encodeToString(billImage);
		}
		return null;
	}

	public Integer getStudentId() {
		return student != null ? student.getId() : null;
	}
}
