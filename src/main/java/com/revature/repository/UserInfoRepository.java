package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.AppUser;
import com.revature.model.UserInformation;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInformation, Integer> {

	UserInformation findUserInformationByUser(AppUser user);
}
