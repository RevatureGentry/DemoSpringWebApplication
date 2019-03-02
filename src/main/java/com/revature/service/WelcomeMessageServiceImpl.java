package com.revature.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WelcomeMessageServiceImpl implements WelcomeMessageService {

	private static final List<BufferedReader> readers = new ArrayList<>();
	
	@Override
	public List<String> getWelcomeMessage() {
		return getMessage();
	}
	
	private static List<String> getMessage() {
		try {
			BufferedReader reader = getReader();
			String s;
			List<String> message = new ArrayList<>();
			while ((s = reader.readLine()) != null) {
				message.add(s.intern());
			}
			message.add(getCurrentDate().intern());
			return message;
		} catch (IOException e) {
			return null;
		}
	}
	
	private static BufferedReader getReader() {
		try {
			readers.add(new BufferedReader(new FileReader(new File("src/main/resources/welcomeMessage.txt"))));
			return readers.get(readers.size() - 1);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	private static String getCurrentDate() {
		return "Today's Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
	}
}
