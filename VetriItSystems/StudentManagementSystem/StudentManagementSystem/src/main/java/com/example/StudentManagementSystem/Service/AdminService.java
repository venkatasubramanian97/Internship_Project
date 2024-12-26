package com.example.StudentManagementSystem.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	// Add Admin
	public void addAdmin(Admin admin) {
		this.adminRepository.save(admin);
	}

	// Get All Admin
	public List<Admin> getAll() {
		List<Admin> admins = this.adminRepository.findAll();
		return admins;
	}

	// Get Single Admin
	public Admin getAdmin(int id) {
		Optional<Admin> optional = this.adminRepository.findById(id);
		Admin admin = optional.get();
		return admin;
	}

	// Update Admin
	public void updateAdmin(int adminId, Admin admin) {
		admin.setAdminId(adminId);
		this.adminRepository.save(admin);
	}

	// Delete Admin
	public void delete(int id) {
		this.adminRepository.deleteById(id);
	}

	// Get Single User By Email
	public Admin getAdminByEmail(String email) {
		Admin admin = this.adminRepository.findByAdminEmail(email);
		return admin;
	}

	// Validating Admin login
	public boolean validateAdminCredentials(String email, String password) {
		Admin admin = adminRepository.findByAdminEmail(email);
		if (admin != null && admin.getAdminPassword().equals(password)) {
			return true;
		}
		return false;
	}

	//Response OTP
	public String sendOtp(String email) {
		Admin admin = adminRepository.findByAdminEmail(email);
		if (admin != null) {
			String generatedOtp = generateOtp();
			admin.setOtp(generatedOtp); 
			adminRepository.save(admin);
			return generatedOtp;
		}
		return null;
	}

	//Create OTP
	private String generateOtp() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(1000000));
	}

	//Verify OTP
	public boolean verifyOtp(String email, String otp ,String password) {
		Admin admin = adminRepository.findByAdminEmail(email);

		String generatedOtp = admin.getOtp();
		admin.setOtp(null);
		admin.setAdminPassword(password);
		adminRepository.save(admin);

		return otp.equals(generatedOtp);
	}

}
