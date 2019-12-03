package com.revature.controller;

import com.revature.model.Message;
import com.revature.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William Gentry
 */
@Controller
public class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("/message")
	public String showMessagePage(Model model) {
		List<Message> messages = messageService.getAllMessages();
		model.addAttribute("messages", messages);
		return "message";
	}

	@PostMapping(value="/message", consumes="application/x-www-form-urlencoded;charset=UTF-8")
	public String createMessage(@ModelAttribute("message") Message message, Model model, Principal principal) {
		final Message newMessage = messageService.createMessage(message, principal);
		if (newMessage != null) {
			model.addAttribute("success", "Message Added Successfully");
			model.addAttribute("messages", messageService.getAllMessages());
		} else {
			model.addAttribute("error", "Invalid Message");
		}
		return "redirect:/message";
	}


	@ModelAttribute("message")
	public Message messageModelAttribute() {
		return new Message();
	}
}

