package com.example.StudentManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.AdminMessage;
import com.example.StudentManagementSystem.Model.Student;

@Repository
public interface AdminMessageRepository extends JpaRepository<AdminMessage, Integer> {
	List<AdminMessage> findMessageByAdmin(Admin admin);

	List<AdminMessage> findMessageByStudent(Student student);

	void deleteAllByAdmin_AdminId(int adminId);

	void deleteAllByStudent_id(int id);

}