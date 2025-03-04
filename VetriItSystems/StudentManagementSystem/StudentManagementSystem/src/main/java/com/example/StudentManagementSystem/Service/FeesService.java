package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.Fees;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Repository.FeesRepository;

import jakarta.transaction.Transactional;

@Service
public class FeesService {

	@Autowired
	private FeesRepository feesRepository;

	// Add Fees
	public void addFees(Fees fees) {
		feesRepository.save(fees);
	}

	// Get All Fees
	public List<Fees> getAll() {
		List<Fees> fees = this.feesRepository.findAll();
		return fees;
	}

	// Get Single Fees
	public Fees getFees(int id) {
		Optional<Fees> optional = this.feesRepository.findById(id);
		Fees fees = optional.get();
		return fees;
	}

	// Update Fees
	public void updateFees(int id, Fees fees) {
		fees.setId(id);
		this.feesRepository.save(fees);
	}

	// Delete Fees
	public void delete(int id) {
		this.feesRepository.deleteById(id);
	}

	// Delete all messages by Student
	@Transactional
	public void deleteFeesByStudent(int id) {
		feesRepository.deleteAllByStudent_id(id);
	}

	// Delete all messages by Admin
	@Transactional
	public void deleteFeesByAdmin(int adminId) {
		feesRepository.deleteAllByAdmin_AdminId(adminId);
	}

	// Get Fees With Admin
	public List<Fees> getFeesByAdmin(Admin admin) {
		return this.feesRepository.findFeesByAdmin(admin);
	}

	// Get Fees With Student
	public List<Fees> getFeesByStudent(Student student) {
		return this.feesRepository.findFeesByStudent(student);
	}

}
