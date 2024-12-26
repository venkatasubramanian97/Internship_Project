package com.example.StudentManagementSystem.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.Fees;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Service.AdminService;
import com.example.StudentManagementSystem.Service.FeesService;
import com.example.StudentManagementSystem.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FeesController {

	@Autowired
	private FeesService feesService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private StudentService studentService;

	// View Fess Details
	@GetMapping("/feesview")
	public String viewFees(HttpSession session, Model model) {
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId == null) {
			return "redirect:/studentLogin";
		}

		Optional<Student> optionalStudent = this.studentService.getStudentId(studentId);
		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			List<Fees> fees = feesService.getFeesByStudent(student);
			model.addAttribute("fees", fees);
		} else {
			model.addAttribute("error", "Student not found");
		}
		return "Fees/View_Fees";
	}

	// Return Fees Details
	@GetMapping("/fees/details")
	public String feesDetails() {
		return "Fees/Admin_Fees_Details.html";
	}

	// Return Fees Details
	@GetMapping("/Fees/details")
	public String FeesDetails() {
		return "Fees/Student_Fees_Details.html";
	}

	// Fees All Data
	@GetMapping("/fees/all/data")
	public String feesalldata(HttpSession session, Model model) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminDetails";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		List<Fees> fees = this.feesService.getFeesByAdmin(admin);
		model.addAttribute("fees", fees);
		return "Fees/Fees_All_Data.html";
	}

	// Add Fees Page
	@GetMapping("/addFees")
	public String addFeesPage() {
		return "Fees/Add_Fees.html";
	}

	// Adding Fees
	@PostMapping("/addingFees")
	public String addFees(@ModelAttribute Fees fees, HttpSession session) {
		fees.setDate(LocalDateTime.now());
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId != null) {
			Admin admin = this.adminService.getAdmin(adminId);
			fees.setAdmin(admin);
		} else {
			return "redirect:/home";
		}

		this.feesService.addFees(fees);
		return "redirect:/fees/details";
	}

	// Update Fees
	@GetMapping("/updateFees")
	public String UpdateFees(Model model, HttpSession session) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/fees/details";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		List<Fees> fees = this.feesService.getFeesByAdmin(admin);
		model.addAttribute("fees", fees);
		return "Fees/Update_Fees_Data.html";
	}

	// Update Fees With Id
	@GetMapping("/updateFees/{id}")
	public String Update(@PathVariable("id") int id, Model model) {
		Fees fees = this.feesService.getFees(id);
		model.addAttribute("fees", fees);
		return "Fees/Update_Fees.html";
	}

	// Updateing Fees
	@PostMapping("/updatingFees/{id}")
	public String UpdateFees(@PathVariable("id") int id, @ModelAttribute Fees fees, HttpSession session) {
		fees.setDate(LocalDateTime.now());
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId != null) {
			Admin admin = this.adminService.getAdmin(adminId);
			fees.setAdmin(admin);
		} else {
			return "redirect:/home";
		}

		this.feesService.updateFees(id, fees);
		return "redirect:/fees/details";
	}

	// Delete Student
	@GetMapping("/deleteFees")
	public String deleteFees(Model model) {
		List<Fees> fees = this.feesService.getAll();
		model.addAttribute("fees", fees);
		return "Fees/Delete_Fees_Data.html";
	}

	// Deleteing Fees
	@GetMapping("/deleteFees/{id}")
	public String deleteFees(@PathVariable("id") int id) {
		this.feesService.delete(id);
		return "redirect:/fees/details";
	}

}
