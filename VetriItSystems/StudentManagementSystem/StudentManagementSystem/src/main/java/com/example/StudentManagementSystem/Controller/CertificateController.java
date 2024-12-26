package com.example.StudentManagementSystem.Controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.StudentManagementSystem.Model.Certificate;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Service.CertificateService;
import com.example.StudentManagementSystem.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CertificateController {

	@Autowired
	private CertificateService certificateservice;

	@Autowired
	private StudentService studentService;

	// View Certificate Details
	@GetMapping("/certificateview")
	public String viewMessage(HttpSession session, Model model) {
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId == null) {
			return "redirect:/studentLogin";
		}

		Optional<Student> optionalStudent = this.studentService.getStudentId(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			List<Certificate> certificates = certificateservice.getCertificateByStudent(student);
			model.addAttribute("certificates", certificates);
		} else {
			model.addAttribute("error", "Student not found");
		}
		return "Certificate/View_Certificate";
	}

	// Return Certificate Details
	@GetMapping("/certificate/details")
	public String certificateDetails() {
		return "Certificate/Admin_Certificate_Details.html";
	}

	// Return Certificate Details
	@GetMapping("/Certificate/details")
	public String CertificateDetails() {
		return "Certificate/Student_Certificate_Details.html";
	}

	// Get All Certificate
	@GetMapping("/certificate/all/data")
	public String viewAllCertificates(Model model) {
		List<Certificate> certificates = this.certificateservice.getAll();
		model.addAttribute("certificates", certificates);
		return "Certificate/Certificate_All_Data.html";
	}

	// Add Certificate
	@GetMapping("/addCertificate")
	public String addcertificatePage() {
		return "Certificate/Add_Certificate.html";
	}

	// Adding Certificate
	@PostMapping("/certificates/add")
	public String addCertificate(@RequestParam("certificateData") String certificateData,
			@RequestParam("studentName") String studentName, @RequestParam("courseName") String courseName,
			@RequestParam("issueDate") String issueDate, @RequestParam("issuedBy") String issuedBy,
			@RequestParam("studentId") int studentId) {

		byte[] certificateImageBytes = Base64.getDecoder().decode(certificateData.split(",")[1]);
		Student student = studentService.getStudent(studentId);

		Certificate certificate = new Certificate();
		certificate.setStudentName(studentName);
		certificate.setCourseName(courseName);
		certificate.setIssueDate(issueDate);
		certificate.setIssuedBy(issuedBy);
		certificate.setCertificateImage(certificateImageBytes);
		certificate.setStudent(student);

		certificateservice.addCertificate(certificate);
		return "redirect:/certificate/details";
	}

	// Update Certificate
	@GetMapping("/updateCertificate")
	public String updateCertificate(Model model) {
		List<Certificate> certificates = this.certificateservice.getAll();
		model.addAttribute("certificates", certificates);
		return "Certificate/Update_Certificate_Data.html";
	}

	// Update Certificate With Id
	@GetMapping("/updateCertificate/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Certificate certificate = this.certificateservice.getCertificate(id);
		model.addAttribute("certificate", certificate);
		return "Certificate/Update_Certificate.html";
	}

	// Updating Certificate
	@PostMapping("/certificates/update")
	public String updateCertificate(@RequestParam("id") int id, @RequestParam("studentId") int studentId,
			@RequestParam("studentName") String studentName, @RequestParam("courseName") String courseName,
			@RequestParam("issueDate") String issueDate, @RequestParam("issuedBy") String issuedBy,
			@RequestParam("certificateData") String certificateData, Model model) {

		Certificate existingCertificate = certificateservice.getCertificate(id);
		Optional<Student> studentOptional = studentService.getStudentId(studentId);

		if (!studentOptional.isPresent()) {
			model.addAttribute("errorMessage", "Student not found with ID: " + studentId);
			return "error";
		}

		Student student = studentOptional.get();
		existingCertificate.setStudent(student);
		existingCertificate.setStudentName(studentName);
		existingCertificate.setCourseName(courseName);
		existingCertificate.setIssueDate(issueDate);
		existingCertificate.setIssuedBy(issuedBy);

		if (certificateData != null && !certificateData.isEmpty()) {
			if (certificateData.startsWith("data:image")) {
				certificateData = certificateData.split(",")[1];
			}
			byte[] imageBytes = java.util.Base64.getDecoder().decode(certificateData);
			existingCertificate.setCertificateImage(imageBytes);
		}

		certificateservice.updateCertificate(id, existingCertificate);
		return "redirect:/certificate/details";
	}

	// Delete Certificate
	@GetMapping("/deleteCertificate")
	public String deleteCertificate(Model model) {
		List<Certificate> certificates = this.certificateservice.getAll();
		model.addAttribute("certificates", certificates);
		return "Certificate/Delete_Certificate_Data.html";
	}

	// Deleteing Certificate
	@GetMapping("/deleteCertificate/{id}")
	public String deleteMessage(@PathVariable("id") int id) {
		this.certificateservice.delete(id);
		return "redirect:/certificate/details";
	}

}
