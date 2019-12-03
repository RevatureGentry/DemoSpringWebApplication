package com.revature.service;

import com.revature.model.AppUser;
import com.revature.model.Message;
import com.revature.repository.MessageRepository;
import com.revature.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author William Gentry
 */
@Service
public class MessageServiceImpl implements MessageService {

	private final MessageRepository repository;
	private final UserRepository userRepository;

	public MessageServiceImpl(MessageRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public List<Message> getAllMessages() {
		return repository.findAll();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public Message createMessage(Message message, Principal principal) {
		AppUser user = userRepository.getOne(principal.getName());
		if (message != null) {
			user.getMessages().add(message);
			message.setTimestamp(LocalDateTime.now());
			message.setCreatedBy(user);
			userRepository.save(user);
			return message;
		}
		return null;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public Message findMessage(int id) {
		return repository.getOne(id);
	}
}
