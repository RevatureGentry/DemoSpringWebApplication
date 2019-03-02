package com.revature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DarkModeServiceImpl implements DarkModeService {

	private final Object lock1 = new Object();
	private final Object lock2 = new Object();
	
	@Override
	public void enableDarkMode() {
		enableDarkModeAsynchronously();
	}
	
	private void enableDarkModeAsynchronously() {
		new Thread(() -> {
			Thread t1 = new EnableDarkModeThread();
			Thread t2 = new SupportDarkModeThread();
			t1.start();
			t2.start();
		}).start();
	}

	private class EnableDarkModeThread extends Thread {
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		
		@Override
		public void run() {
			synchronized(lock1) {
				logger.info("Enabling Dark Mode...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
				logger.info("Waiting for second lock...");
				
				synchronized(lock2) {
					logger.info("Dark Mode Enabled");
				}
			}
		}
	}
	
	private class SupportDarkModeThread extends Thread {
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		
		@Override
		public void run() {
			synchronized(lock2) {
				logger.info("Attempting to enable Dark Mode support...");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}
				logger.info("Waiting for second lock...");
				synchronized(lock1) {
					logger.info("Dark Mode Enabled");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new DarkModeServiceImpl().enableDarkMode();
	}
}
