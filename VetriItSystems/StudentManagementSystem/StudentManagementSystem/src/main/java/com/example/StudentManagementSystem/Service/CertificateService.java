package com.example.StudentManagementSystem.Service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Certificate;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Repository.CertificateRepository;

import jakarta.transaction.Transactional;

@Service
public class CertificateService {

	@Autowired
	private CertificateRepository certificateRepository;

	// Add Certificate
	public void addCertificate(Certificate certificate) {
		this.certificateRepository.save(certificate);
	}

	// Get All Certificate
	public List<Certificate> getAll() {
		List<Certificate> certificate = this.certificateRepository.findAll();
		return certificate;
	}

	// Get Single Certificate
	public Certificate getCertificate(int id) {
		Optional<Certificate> optional = this.certificateRepository.findById(id);
		Certificate certificate = optional.get();
		return certificate;
	}

	// Update Certificate
	public void updateCertificate(int id, Certificate certificate) {
		certificate.setId(id);
		this.certificateRepository.save(certificate);
	}

	// Delete certificate
	public void delete(int id) {
		this.certificateRepository.deleteById(id);
	}

	// Delete all messages by Student
	@Transactional
	public void deleteCertificateByStudent(int id) {
		certificateRepository.deleteAllByStudent_id(id);
	}

	// Get Certificate in Student
	public List<Certificate> getCertificateByStudent(Student student) {
		return certificateRepository.findCertificateByStudent(student);
	}

}
