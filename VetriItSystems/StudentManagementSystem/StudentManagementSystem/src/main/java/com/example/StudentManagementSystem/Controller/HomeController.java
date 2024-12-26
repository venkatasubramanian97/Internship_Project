package com.example.StudentManagementSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.StudentManagementSystem.LoginCredential.AdminLogin;
import com.example.StudentManagementSystem.LoginCredential.StudentLogin;

@Controller
public class HomeController {

	// Get Home Page
	@GetMapping("/home")
	public String home(Model model) {
		return "Login/Home.html";
	}

	// Admin Login
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("adminLogin", new AdminLogin());
		return "Login/Admin_Login.html";
	}

	// Student Login
	@GetMapping("/student")
	public String student(Model model) {
		model.addAttribute("studentLogin", new StudentLogin());
		return "Login/Student_Login.html";
	}

}
