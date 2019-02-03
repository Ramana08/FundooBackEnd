package com.bridgeit.fundoo.service;

import com.bridgeit.fundoo.dto.UserDto;
import com.bridgeit.fundoo.model.GenerateOtp;
import com.bridgeit.fundoo.model.User;

public interface IUserService {

	boolean addUser(User user);

	
	boolean sendOtpCall(User user);

	
	boolean verifyOtp(GenerateOtp userOtp);


	boolean updateUser(User user, Integer id);

	
	boolean deleteUser(Integer id);

	
	UserDto userLogin(User user);
	
	
	public User getUser(Integer id);

	
	boolean forgetPassword(User user);


	boolean forgetVerification(GenerateOtp generateOtp, User forgetUser);

}
