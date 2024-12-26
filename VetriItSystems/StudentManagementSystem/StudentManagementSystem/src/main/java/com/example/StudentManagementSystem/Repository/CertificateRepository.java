package com.example.StudentManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Certificate;
import com.example.StudentManagementSystem.Model.Student;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
	List<Certificate> findCertificateByStudent(Student student);

	void deleteAllByStudent_id(int id);

}
