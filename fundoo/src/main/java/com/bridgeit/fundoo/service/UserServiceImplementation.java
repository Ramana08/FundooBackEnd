package com.bridgeit.fundoo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.IUserDao;
import com.bridgeit.fundoo.dto.UserDto;
import com.bridgeit.fundoo.model.GenerateOtp;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.utility.UserToken;
import com.bridgeit.fundoo.utility.Utility;

@Transactional
public class UserServiceImplementation implements IUserService{

	@Autowired
	IUserDao userDao;
	
	@Autowired
	String key;
	
	
	@Override
	public boolean addUser(User user) 
	{
	//	System.out.println(userDao+" "+key);
		String encryptedPassword=Utility.encrypt(user.getPassword(), key);
		//System.out.println(encryptedPassword);
		user.setPassword(encryptedPassword);
		user.setIsActive(true);
		boolean check=userDao.save(user);
		return check;
	}
	@Override
	public boolean sendOtpCall(User user) {
		
		List<User> userList=userDao.getAllUser();
		List<GenerateOtp> generateOtps=userDao.getAllOtp();
		for (int i = 0; i < userList.size(); i++) 
		{
			if(user.getEmail().equals(userList.get(i).getEmail()) && generateOtps.get(i).getEmail().equals(user.getEmail()))
			{
				System.out.println("already exist");
				return false;
			}
		}
		String password=Utility.OTP();
		
		
		GenerateOtp userOtp=new GenerateOtp();
		
		
		System.out.println(user);
//		userOtp.setEmail(user.getEmail());
//		userOtp.setOtpPassword(password);
		for (int i = 0; i < generateOtps.size() ; i++) 
		{
			if(user.getEmail().equals(generateOtps.get(i).getEmail()))
			{
				userOtp=generateOtps.get(i);
				System.out.println(userOtp);
				userOtp.setEmail(user.getEmail());
				userOtp.setOtpPassword(password);
				System.out.println("after generate"+userOtp);
				userDao.updateOtp(userOtp);
				Utility.sendEmail(user, password);
				return true;
			}
		}
		userOtp.setEmail(user.getEmail());
		userOtp.setOtpPassword(password);
		userDao.saveOtp(userOtp);
		Utility.sendEmail(user, password);
		
		return true;
	}
	@Override
	public boolean verifyOtp(GenerateOtp userOtp) 
	{
		
		List<GenerateOtp> otp=userDao.getAllOtp();
		System.out.println("hdjsg");
		System.out.println(userOtp.getEmail()+" "+userOtp.getOtpPassword()+" "+otp.size());
		for (int i = 0; i < otp.size(); i++)
		{
			if(userOtp.getOtpPassword().equals(otp.get(i).getOtpPassword()))
			{
				System.out.println("hi");
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean updateUser(User user,Integer id) 
	{
		User updateUser=userDao.getUser(id);
		updateUser.setName(user.getName());
		updateUser.setEmail(user.getEmail());
		boolean check=userDao.updateUser(updateUser);
		if(check)
			return true;
		return false;
	}
	@Override
	public boolean deleteUser(Integer id)
	{
		User updateUser=userDao.getUser(id);

		boolean check=userDao.deleteUser(updateUser);
		if(check)
			return true;
		return false;
	}
	@Override
	public UserDto userLogin(User user) 
	{
		
		List<User> userList=userDao.getAllUser();
		for (int i = 0; i < userList.size(); i++)
		{
			String encrptedPassword=Utility.encrypt(user.getPassword(), key);
			if(user.getEmail().equals(userList.get(i).getEmail()) && encrptedPassword.equals(userList.get(i).getPassword()))
			{
				ModelMapper mapper=new ModelMapper();
				UserDto loginUser=mapper.map(userList.get(i),UserDto.class);
				System.out.println("login user "+loginUser);
				return loginUser;
			}
		}
		return null;
	}
	@Override
	public User getUser(Integer id) {
		User user=userDao.getUser(id);
		return user;
	}
	@Override
	public boolean forgetPassword(User user) 
	{
		List<User> userList=userDao.getAllUser();
		List<GenerateOtp> userOtp=userDao.getAllOtp();
		for (int i = 0; i < userList.size(); i++) 
		{
			if(user.getEmail().equals(userList.get(i).getEmail()))
			{
				//User newUser=userList.get(i);

				String password=Utility.OTP();
				GenerateOtp newUserOtp=new GenerateOtp();
				
				if(userOtp.get(i).getEmail().equals(user.getEmail()))
				{
					newUserOtp=userOtp.get(i);
				}
				newUserOtp.setEmail(user.getEmail());
				newUserOtp.setOtpPassword(password);
				boolean check=userDao.updateOtp(newUserOtp);
				if(check)
				{
					Utility.sendEmail(user, password);
					/*String encryptedPassword=Utility.encrypt(user.getPassword(), key);
					newUser.setPassword(encryptedPassword);
					boolean check1=userDao.updateUser(newUser);
					if(check1)*/
						return true;
				}
			
			}
		}
			return false;
	}
	@Override
	public boolean forgetVerification(GenerateOtp generateOtp,User forgetUser) 
	{
		List<GenerateOtp> userOtp=userDao.getAllOtp();
		List<User> userList=userDao.getAllUser();
		for (int i = 0; i <userOtp.size(); i++) 
		{
			if(generateOtp.getOtpPassword().equals(userOtp.get(i).getOtpPassword()))
			{
				for (int j = 0; j < userList.size(); j++) 
				{
					
					if(userList.get(i).getEmail().equals(forgetUser.getEmail()))
					{
						String encryptedPassword=Utility.encrypt(forgetUser.getPassword(), key);
						User newUser=userList.get(i);
						newUser.setPassword(encryptedPassword);
						boolean check=userDao.updateUser(newUser);
						if(check)
						{
							return true;
						}
					}
				}
			}
		}
		return false;

	}
	

}
