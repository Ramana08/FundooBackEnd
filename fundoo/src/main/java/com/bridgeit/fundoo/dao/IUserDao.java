package com.bridgeit.fundoo.dao;

import java.util.List;

import com.bridgeit.fundoo.model.GenerateOtp;
import com.bridgeit.fundoo.model.User;

public interface IUserDao {

	boolean save(User user);

	List<User> getAllUser();

	void saveOtp(GenerateOtp userOtp);

	List<GenerateOtp> getAllOtp();


	User getUser(Integer id);

	boolean updateUser(User updateUser);

	boolean deleteUser(User updateUser);

	boolean updateOtp(GenerateOtp newUserOtp);

}
