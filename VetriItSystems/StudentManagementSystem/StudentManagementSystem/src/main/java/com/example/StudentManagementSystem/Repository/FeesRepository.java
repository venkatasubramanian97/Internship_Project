package com.example.StudentManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.Fees;
import com.example.StudentManagementSystem.Model.Student;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Integer> {
	List<Fees> findFeesByAdmin(Admin admin);

	List<Fees> findFeesByStudent(Student student);

	void deleteAllByAdmin_AdminId(int adminId);

	void deleteAllByStudent_id(int id);

}
