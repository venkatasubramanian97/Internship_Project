package com.example.StudentManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.StudentManagementSystem.LoginCredential.AdminLogin;
import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Service.AdminMessageService;
import com.example.StudentManagementSystem.Service.AdminService;
import com.example.StudentManagementSystem.Service.FeesService;
import com.example.StudentManagementSystem.Service.StudentMessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminMessageService adminMessageService;

	@Autowired
	private FeesService fessService;

	@Autowired
	private StudentMessageService studentMessageService;

	// Admin Login
	@PostMapping("/adminLogin")
	public String getAllAdminData(@ModelAttribute AdminLogin login, Model model, HttpSession session) {
		String email = login.getAdminEmail();
		String password = login.getAdminPassword();

		if (adminService.validateAdminCredentials(email, password)) {
			Admin admin = adminService.getAdminByEmail(email);
			session.setAttribute("adminId", admin.getAdminId());
			return "Admin/Admin_Details.html";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "Login/Admin_Login.html";
		}
	}

	// Return Admin Details
	@GetMapping("/admin/details")
	public String adminDetails() {
		return "Admin/Admin_Details.html";
	}

	// Get All Admin Data
	@GetMapping("/admin/all/data")
	public String adminalldata(Model model) {
		List<Admin> admins = this.adminService.getAll();
		model.addAttribute("admins", admins);
		return "Admin/Admin_All_Data.html";
	}

	// Return Admin Page
	@GetMapping("/adminDetails")
	public String showAdminDetails() {
		return "Admin/Admin_Details.html";
	}

	// Add Admin
	@GetMapping("/addAdmin")
	public String addAdminPage() {
		return "Admin/Add_Admin.html";
	}

	// Adding Admin
	@PostMapping("addingAdmin")
	public String addAdmin(@ModelAttribute Admin admin) {
		this.adminService.addAdmin(admin);
		return "redirect:/adminDetails";
	}

	// Update Admin
	@GetMapping("/updateAdmin")
	public String updateAdminDetails(HttpSession session, Model model) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminLogin";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		model.addAttribute("admin", admin);
		return "Admin/Update_Admin.html";
	}

	// Updateing Admin
	@PostMapping("/updatingAdmin")
	public String updateAdmin(HttpSession session, @ModelAttribute Admin admin) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminLogin";
		}

		this.adminService.updateAdmin(adminId, admin);
		return "redirect:/adminDetails";
	}

	// Delete Admin
	@PostMapping("/deleteAdmin")
	public String deleteAdmin(HttpSession session) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId != null) {
			this.adminMessageService.deleteMessagesByAdmin(adminId);
			this.studentMessageService.deleteMessagesByAdmin(adminId);
			this.fessService.deleteFeesByAdmin(adminId);
			this.adminService.delete(adminId);
		}
		return "redirect:/home";
	}

	// Forgot Password
	@GetMapping("/forgotPassword")
	public String showForgotPasswordPage() {
		return "Admin/ForgotPassword";
	}

	// Send OTP
	@PostMapping("/sendOtp")
	public ResponseEntity<String> sendOtp(@RequestParam("email") String email) {
		String otp = this.adminService.sendOtp(email);

		if (otp == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
		}
		return ResponseEntity.ok(otp);
	}

	// Varify OTP
	@PostMapping("/validateOtp")
	public ResponseEntity<String> validateOtp(@RequestParam("email") String email, @RequestParam("otp") String otp,
			@RequestParam("newPassword") String newPassword) {
		boolean isValid = adminService.verifyOtp(email, otp, newPassword);

		if (isValid) {
			return ResponseEntity.ok("OTP verification and Password change successful.");
		} else {
			return ResponseEntity.ok("Invalid OTP. Please try again.");
		}
	}

}
