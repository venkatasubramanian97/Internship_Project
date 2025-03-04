package com.example.StudentManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Model.StudentMessage;

@Repository
public interface StudentMessageRepository extends JpaRepository<StudentMessage, Integer> {
	List<StudentMessage> findMessageByStudent(Student student);

	List<StudentMessage> findMessageByAdmin(Admin admin);

	void deleteAllByAdmin_AdminId(int adminId);

	void deleteAllByStudent_id(int id);

}
