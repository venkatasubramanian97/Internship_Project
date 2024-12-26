package com.example.StudentManagementSystem.Controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.StudentManagementSystem.Model.Bills;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Service.BillsService;
import com.example.StudentManagementSystem.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BillsController {

	@Autowired
	private BillsService billsService;

	@Autowired
	private StudentService studentService;

	//View Bills Details
	@GetMapping("/billsview")
	public String viewBills(HttpSession session, Model model) {
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId == null) {
			return "redirect:/studentLogin";
		}

		Optional<Student> optionalStudent = this.studentService.getStudentId(studentId);

		if (optionalStudent.isPresent()) {
			Student student = optionalStudent.get();
			List<Bills> bills = billsService.getBillsByStudent(student);
			model.addAttribute("bills", bills);
		} else {
			model.addAttribute("error", "Student not found");
		}

		return "Bills/View_Bills";
	}

	// Return Bills Details
	@GetMapping("/bills/details")
	public String billsDetails() {
		return "Bills/Admin_Bills_Details.html";
	}

	// Return Bills Details
	@GetMapping("/Bills/details")
	public String BillsDetails() {
		return "Bills/Student_Bills_Details.html";
	}

	// Get All Bills
	@GetMapping("/bills/all/data")
	public String viewAllBills(Model model) {
		List<Bills> bills = this.billsService.getAll();
		model.addAttribute("bills", bills);
		return "Bills/Bills_All_Data.html";
	}

	// Add Bills
	@GetMapping("/addBills")
	public String addbillsPage() {
		return "Bills/Add_Bills.html";
	}

	// Adding Bills
	@PostMapping("/fees/add")
	public String addBill(@RequestParam("billData") String billData, @RequestParam("studentName") String studentName,
			@RequestParam("amount") String amount, @RequestParam("courseName") String courseName,
			@RequestParam("issueDate") String issueDate, @RequestParam("issuedBy") String issuedBy,
			@RequestParam("studentId") int studentId) {

		byte[] billsImageBytes = Base64.getDecoder().decode(billData.split(",")[1]);
		Student student = studentService.getStudent(studentId);

		Bills bills = new Bills();
		bills.setStudentName(studentName);
		bills.setCourseName(courseName);
		bills.setIssueDate(issueDate);
		bills.setIssuedBy(issuedBy);
		bills.setAmount(amount);
		bills.setBillImage(billsImageBytes);
		bills.setStudent(student);

		billsService.addBills(bills);
		return "redirect:/bills/details";
	}

	// Update Bills
	@GetMapping("/updateBills")
	public String updateBills(Model model) {
		List<Bills> bills = this.billsService.getAll();
		model.addAttribute("bills", bills);
		return "Bills/Update_Bills_Data.html";
	}

	// Update Bills With Id
	@GetMapping("/updateBills/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Bills bills = this.billsService.getBills(id);
		model.addAttribute("bills", bills);
		return "Bills/Update_Bills.html";
	}

	// Updating Bills
	@PostMapping("/bills/update")
	public String updateBill(@RequestParam("id") int id, @RequestParam("studentId") int studentId,
			@RequestParam("studentName") String studentName, @RequestParam("courseName") String courseName,
			@RequestParam("issueDate") String issueDate, @RequestParam("issuedBy") String issuedBy,
			@RequestParam("amount") String amount, @RequestParam("billData") String billData, Model model) {

		Bills existingBills = billsService.getBills(id);
		Optional<Student> studentOptional = studentService.getStudentId(studentId);

		if (!studentOptional.isPresent()) {
			model.addAttribute("errorMessage", "Student not found with ID: " + studentId);
			return "error";
		}

		Student student = studentOptional.get();
		existingBills.setStudent(student);
		existingBills.setStudentName(studentName);
		existingBills.setCourseName(courseName);
		existingBills.setIssueDate(issueDate);
		existingBills.setIssuedBy(issuedBy);
		existingBills.setAmount(amount);

		if (billData != null && !billData.isEmpty()) {
			if (billData.startsWith("data:image")) {
				billData = billData.split(",")[1];
			}
			byte[] imageBytes = java.util.Base64.getDecoder().decode(billData);
			existingBills.setBillImage(imageBytes);
		}

		billsService.updateBills(id, existingBills);
		return "redirect:/bills/details";
	}

	// Delete Bills
	@GetMapping("/deleteBills")
	public String deleteBills(Model model) {
		List<Bills> bills = this.billsService.getAll();
		model.addAttribute("bills", bills);
		return "Bills/Delete_Bills_Data.html";
	}

	// Deleteing Bills
	@GetMapping("/deleteBills/{id}")
	public String deleteBills(@PathVariable("id") int id) {
		this.billsService.delete(id);
		return "redirect:/bills/details";
	}

}
