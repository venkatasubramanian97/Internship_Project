package com.example.StudentManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Bills;
import com.example.StudentManagementSystem.Model.Student;

@Repository
public interface BillsRepository extends JpaRepository<Bills, Integer> {
	List<Bills> findBillsByStudent(Student student);

	void deleteAllByStudent_id(int id);

}
