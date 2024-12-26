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

import com.example.StudentManagementSystem.Model.Admin;
import com.example.StudentManagementSystem.Model.AdminMessage;
import com.example.StudentManagementSystem.Model.StudentMessage;
import com.example.StudentManagementSystem.Service.AdminMessageService;
import com.example.StudentManagementSystem.Service.AdminService;
import com.example.StudentManagementSystem.Service.StudentMessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminMessageController {

	@Autowired
	private AdminMessageService adminMessageService;

	@Autowired
	private StudentMessageService studentMessageService;

	@Autowired
	private AdminService adminService;

	// Admin Message All Data
	@GetMapping("/adminMessage/all/data")
	public String adminmessagealldata(HttpSession session, Model model) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminDetails";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		List<AdminMessage> adminMessages = this.adminMessageService.getMessageByAdmin(admin);
		List<StudentMessage> studentMessage = this.studentMessageService.getMessageByAdmin(admin);
		model.addAttribute("adminMessages", adminMessages);
		model.addAttribute("studentMessage", studentMessage);
		return "AdminMessage/Admin_Message_All_Data.html";
	}

	// Return Message Details
	@GetMapping("/message/details")
	public String messageDetails() {
		return "AdminMessage/Admin_Message_Details.html";
	}

	// Admin Add Message
	@GetMapping("/adminAddMessage")
	public String adminAddMessagePage() {
		return "AdminMessage/Admin_Add_Message.html";
	}

	// Admin Adding Message
	@PostMapping("adminAddingMessage")
	public String adminAddMessage(@ModelAttribute AdminMessage adminMessage, HttpSession session) {
		adminMessage.setSentTime(LocalDateTime.now());
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId != null) {
			Admin admin = this.adminService.getAdmin(adminId);
			adminMessage.setAdmin(admin);
		} else {
			return "redirect:/home";
		}

		this.adminMessageService.adminaddMessage(adminMessage);
		return "redirect:/message/details";
	}

	// Admin Update Message
	@GetMapping("/adminUpdateMessage")
	public String adminUpdateMessage(Model model, HttpSession session) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminDetails";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		List<AdminMessage> adminMessages = this.adminMessageService.getMessageByAdmin(admin);
		model.addAttribute("adminMessages", adminMessages);
		return "AdminMessage/Admin_Update_Message_Data.html";
	}

	// Admin Update Message With Id
	@GetMapping("/adminUpdateMessage/{id}")
	public String adminUpdate(@PathVariable("id") int id, Model model) {
		AdminMessage adminMessage = this.adminMessageService.admingetMessage(id);
		model.addAttribute("adminMessage", adminMessage);
		return "AdminMessage/Admin_Update_Message.html";
	}

	// Admin Updateing Message
	@PostMapping("/adminUpdatingMessage/{id}")
	public String adminUpdateMessage(@PathVariable("id") int id, @ModelAttribute AdminMessage adminMessage,
			HttpSession session) {
		adminMessage.setSentTime(LocalDateTime.now());
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId != null) {
			Admin admin = this.adminService.getAdmin(adminId);
			adminMessage.setAdmin(admin);
		} else {
			return "redirect:/home";
		}

		this.adminMessageService.adminupdateMessage(id, adminMessage);
		return "redirect:/message/details";
	}

	// Admin Delete Message
	@GetMapping("/adminDeleteMessage")
	public String deleteMessage(Model model, HttpSession session) {
		Integer adminId = (Integer) session.getAttribute("adminId");

		if (adminId == null) {
			return "redirect:/adminDetails";
		}

		Admin admin = this.adminService.getAdmin(adminId);
		List<AdminMessage> adminMessages = this.adminMessageService.getMessageByAdmin(admin);
		model.addAttribute("adminMessages", adminMessages);
		return "AdminMessage/Admin_Delete_Message_Data.html";
	}

	// Admin Deleteing Message
	@GetMapping("/adminDeleteMessage/{id}")
	public String deleteMessage(@PathVariable("id") int id) {
		this.adminMessageService.delete(id);
		return "redirect:/message/details";
	}

}
