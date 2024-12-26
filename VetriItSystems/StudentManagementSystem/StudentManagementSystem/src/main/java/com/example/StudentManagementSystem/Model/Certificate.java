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
@Table(name = "certificates")
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String studentName;
	private String courseName;
	private String issueDate;
	private String issuedBy;

	@Lob
	private byte[] certificateImage;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Certificate() {
	}

	public Certificate(int id, String studentName, String courseName, String issueDate, String issuedBy,
			byte[] certificateImage, Student student) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.courseName = courseName;
		this.issueDate = issueDate;
		this.issuedBy = issuedBy;
		this.certificateImage = certificateImage;
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

	public byte[] getCertificateImage() {
		return certificateImage;
	}

	public void setCertificateImage(byte[] certificateImage) {
		this.certificateImage = certificateImage;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCertificateImageBase64() {
		if (certificateImage != null) {
			return Base64.getEncoder().encodeToString(certificateImage);
		}
		return null;
	}

	public Integer getStudentId() {
		return student != null ? student.getId() : null;
	}

}
