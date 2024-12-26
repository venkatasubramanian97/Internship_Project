package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Bills;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Repository.BillsRepository;

import jakarta.transaction.Transactional;

@Service
public class BillsService {

	@Autowired
	private BillsRepository billsRepository;

	// Add Bills
	public void addBills(Bills bills) {
		this.billsRepository.save(bills);
	}

	// Get All Bills
	public List<Bills> getAll() {
		List<Bills> bills = this.billsRepository.findAll();
		return bills;
	}

	// Get Single Bills
	public Bills getBills(int id) {
		Optional<Bills> optional = this.billsRepository.findById(id);
		Bills bills = optional.get();
		return bills;
	}

	// Update Bills
	public void updateBills(int id, Bills bills) {
		bills.setId(id);
		this.billsRepository.save(bills);
	}

	// Delete Bills
	public void delete(int id) {
		this.billsRepository.deleteById(id);
	}

	// Delete all messages by Student
	@Transactional
	public void deleteBillsByStudent(int id) {
		billsRepository.deleteAllByStudent_id(id);
	}

	// Get Bills in Student
	public List<Bills> getBillsByStudent(Student student) {
		return billsRepository.findBillsByStudent(student);
	}

}
