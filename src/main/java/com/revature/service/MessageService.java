package com.revature.service;

import com.revature.model.Message;

import java.security.Principal;
import java.util.List;

/**
 * @author William Gentry
 */
public interface MessageService {

	List<Message> getAllMessages();
	Message createMessage(Message message, Principal principal);
	Message findMessage(int id);
}
