package com.revature.config;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class AsyncConfig {

	@Bean
	public AsyncTaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor(new CustomThreadFactory());
	}

	private static class CustomThreadFactory implements ThreadFactory {

		private final String namePrefix;
		private final ThreadGroup group;
		private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
		private final AtomicInteger threadNumber = new AtomicInteger(1);

		private CustomThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			namePrefix = "custom-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.incrementAndGet());
			t.setDaemon(true);
			return t;
		}
	}
}
