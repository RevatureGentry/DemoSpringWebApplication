package com.revature;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.model.AppUser;
import com.revature.model.ApplicationAuthority;
import com.revature.model.PhoneType;
import com.revature.model.State;
import com.revature.model.UserInformation;
import com.revature.repository.UserInfoRepository;
import com.revature.repository.UserRepository;

@SpringBootApplication
public class DemoSpringWebAppApplication {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserInfoRepository infoRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringWebAppApplication.class, args);
	}

	@PostConstruct
	public void initDb() {
		Set<ApplicationAuthority> userAuthority = new HashSet<>();
		userAuthority.add(new ApplicationAuthority("ROLE_USER"));
		Set<ApplicationAuthority> adminAuthority = new HashSet<>();
		adminAuthority.add(new ApplicationAuthority("ROLE_ADMIN"));
		adminAuthority.add(new ApplicationAuthority("ROLE_USER"));

		// Bootstrap Users
		/*
		 * William
		 */
		AppUser user = new AppUser();
		user.setUsername("admin");
		user.setPassword(encoder.encode("Password123!"));
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setAuthorities(adminAuthority);
		
		UserInformation info = new UserInformation();
		info.setFirstname("Admin");
		info.setLastname("User");
		info.setEmail("william.gentry@revature.com");
		info.setStreet("123 Main Street");
		info.setCity("Atlanta");
		info.setState(State.Georgia);
		info.setZipCode("30024");
		info.setPhoneNumber("770-867-5309");
		info.setPhoneType(PhoneType.CELL);
		
		info.setUser(user);
		userRepo.save(user);
		infoRepo.save(info);
		
		/*
		 * Populate Other Users
		 */
		for (int i = 2; i < 11; i++) {
			user = new AppUser();
			user.setUsername("user" + i);
			user.setPassword(encoder.encode("Password123!"));
			user.setEnabled(true);
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setAuthorities(userAuthority);
			
			info = new UserInformation();
			info.setFirstname("Some");
			info.setLastname("User");
			info.setEmail("some.user@test.com");
			info.setStreet("123 Tomato Street");
			info.setCity("Reston");
			info.setState(State.Virginia);
			info.setZipCode("20190");
			info.setPhoneNumber("123-867-4454");
			info.setPhoneType(PhoneType.CELL);
			
			info.setUser(user);
			userRepo.save(user);
			infoRepo.save(info);
		}
		
	}
}

