package com.example.StudentManagementSystem.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.StudentManagementSystem.Model.AdminMessage;
import com.example.StudentManagementSystem.Model.Student;
import com.example.StudentManagementSystem.Model.StudentMessage;
import com.example.StudentManagementSystem.Service.AdminMessageService;
import com.example.StudentManagementSystem.Service.StudentMessageService;
import com.example.StudentManagementSystem.Service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentMessageController {

	@Autowired
	private StudentMessageService studentMessageService;

	@Autowired
	private AdminMessageService adminMessageService;

	@Autowired
	private StudentService studentService;

	// Student Message All Data
	@GetMapping("/studentMessage/all/data")
	public String studentmessagealldata(HttpSession session, Model model) {
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId == null) {
			return "redirect:/studentDetails";
		}

		Student student = this.studentService.getStudent(studentId);
		List<AdminMessage> adminMessages = this.adminMessageService.getMessageByStudent(student);
		List<StudentMessage> studentMessage = this.studentMessageService.getMessageByStudent(student);
		model.addAttribute("adminMessages", adminMessages);
		model.addAttribute("studentMessage", studentMessage);
		return "StudentMessage/Student_Message_All_Data.html";
	}

	// Return Message Details
	@GetMapping("/Message/details")
	public String MessageDetails() {
		return "StudentMessage/Message_Details.html";
	}

	// Student Add Message
	@GetMapping("/studentAddMessage")
	public String studentAddMessagePage() {
		return "StudentMessage/Student_Add_Message.html";
	}

	// Student Adding Message
	@PostMapping("studentAddingMessage")
	public String studentAddMessage(@ModelAttribute StudentMessage studentMessage, HttpSession session) {
		studentMessage.setSentTime(LocalDateTime.now());
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId != null) {
			Student student = this.studentService.getStudent(studentId);
			studentMessage.setStudent(student);
		} else {
			return "redirect:/home";
		}

		this.studentMessageService.studentaddMessage(studentMessage);
		return "redirect:/Message/details";
	}

	// Student Update Message
	@GetMapping("/studentUpdateMessage")
	public String studentUpdateMessage(Model model) {
		List<StudentMessage> studentMessages = this.studentMessageService.getAll();
		model.addAttribute("studentMessages", studentMessages);
		return "StudentMessage/Student_Update_Message_Data.html";
	}

	// Student Update Message With Id
	@GetMapping("/studentUpdateMessage/{id}")
	public String studentUpdate(@PathVariable("id") int id, Model model) {
		StudentMessage studentMessage = this.studentMessageService.studentgetMessage(id);
		model.addAttribute("studentMessage", studentMessage);
		return "StudentMessage/Student_Update_Message.html";
	}

	// Student Updateing Message
	@PostMapping("/studentUpdatingMessage/{id}")
	public String studentUpdateMessage(@PathVariable("id") int id, @ModelAttribute StudentMessage studentMessage,
			HttpSession session) {
		studentMessage.setSentTime(LocalDateTime.now());
		Integer studentId = (Integer) session.getAttribute("studentId");

		if (studentId != null) {
			Student student = this.studentService.getStudent(studentId);
			studentMessage.setStudent(student);
		} else {
			return "redirect:/home";
		}

		this.studentMessageService.studentupdateMessage(id, studentMessage);
		return "redirect:/Message/details";
	}

	// Student Delete Message
	@GetMapping("/studentDeleteMessage")
	public String deleteMessage(Model model) {
		List<StudentMessage> studentMessages = this.studentMessageService.getAll();
		model.addAttribute("studentMessages", studentMessages);
		return "StudentMessage/Student_Delete_Message_Data.html";
	}

	// Student Deleteing Message
	@GetMapping("/studentDeleteMessage/{id}")
	public String deleteMessage(@PathVariable("id") int id) {
		this.studentMessageService.delete(id);
		return "redirect:/Message/details";
	}

}
