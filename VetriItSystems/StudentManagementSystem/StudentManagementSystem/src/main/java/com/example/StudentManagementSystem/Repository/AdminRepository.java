package com.example.StudentManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentManagementSystem.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findByAdminEmail(String email);
}
