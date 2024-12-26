package com.example.StudentManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentManagementSystem.LoginCredential.StudentLogin;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Service.AdminMessageService;
import com.example.StudentManagementSystem.Service.BillsService;
import com.example.StudentManagementSystem.Service.CertificateService;
import com.example.StudentManagementSystem.Service.FeesService;
import com.example.StudentManagementSystem.Service.StudentMessageService;
import com.example.StudentManagementSystem.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AdminMessageService adminMessageService;

	@Autowired
	private FeesService fessService;

	@Autowired
	private StudentMessageService studentMessageService;

	@Autowired
	private BillsService billsService;
	
	@Autowired
	private CertificateService certificateService;
	
	// Student Login
	@PostMapping("/studentLogin")
	public String getAllData(@ModelAttribute StudentLogin login, Model model, HttpSession session) {
		String email = login.getStudentEmail();
		String password = login.getStudentPassword();

		if (studentService.validateLoginCredentials(email, password)) {
			Student student = studentService.getStudentByEmail(email);
			session.setAttribute("studentId", student.getId());
			return "Student/Student_Details.html";
		} else {
			model.addAttribute("error1", "Invalid email or password");
			return "Login/Student_Login.html";
		}
	}

	// Return Student Details
	@GetMapping("/student/details")
	public String studentDetails() {
		return "Student/Admin_Student_Details.html";
	}

	// Return Student Details
	@GetMapping("/Student/details")
	public String StudentDetails() {
		return "Student/Student_Details.html";
	}

	// Student Details View
	@GetMapping("/studentview")
	public String viewStudent(HttpSession session, Model model) {
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId == null) {
			return "redirect:/studentLogin";
		}

		Student student = this.studentService.getStudent(studentId);
		model.addAttribute("students", student);
		return "Student/View_Student.html";
	}

	// Student All Data
	@GetMapping("/student/all/data")
	public String studentalldata(Model model) {
		List<Student> students = this.studentService.getAll();
		model.addAttribute("students", students);
		return "Student/Student_All_Data.html";
	}

	// Return Student Login
	@GetMapping("/studentDetails")
	public String showStudentDetails() {
		return "Student/Student_Details.html";
	}

	// Return Admin Page
	@GetMapping("/adminPage")
	public String showAdminDetails() {
		return "redirect:/adminDetails";
	}

	// Add Student
	@GetMapping("/addStudent")
	public String addStudentPage() {
		return "Student/Add_Student.html";
	}

	// Adding Student
	@PostMapping("addingStudent")
	public String addStudent(@ModelAttribute Student student) {
		this.studentService.addStudent(student);
		return "redirect:/student/details";
	}

	// Update Student
	@GetMapping("/updateStudent")
	public String updateStudent(Model model) {
		List<Student> students = this.studentService.getAll();
		model.addAttribute("students", students);
		return "Student/Update_Student_Data.html";
	}

	// Update Student With Id
	@GetMapping("/updateStudent/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Student student = this.studentService.getStudent(id);
		model.addAttribute("student", student);
		return "Student/Update_Student.html";
	}

	// Updateing With Student
	@PostMapping("/updatingStudent/{id}")
	public String updateStudent(@PathVariable("id") int id, @ModelAttribute Student student) {
		this.studentService.updateStudent(student, id);
		return "redirect:/student/details";
	}

	// Delete Student
	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model) {
		List<Student> students = this.studentService.getAll();
		model.addAttribute("students", students);
		return "Student/Delete_Student_Data.html";
	}

	// Deleteing Student
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") int id) {
		this.adminMessageService.deleteMessagesByStudent(id);
		this.studentMessageService.deleteMessagesByStudent(id);
		this.fessService.deleteFeesByStudent(id);
		this.billsService.deleteBillsByStudent(id);
		this.certificateService.deleteCertificateByStudent(id);
		this.studentService.deleteStudent(id);
		return "redirect:/student/details";
	}

}
