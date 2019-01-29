package com.bridgeit.fundoo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.INoteDao;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.utility.UserToken;
import com.bridgeit.fundoo.utility.Utility;

@Transactional
public class NoteServiceImplementation implements INoteService
{

	@Autowired
	INoteDao noteDao;
	@Autowired
	IUserService userService;
	@Override
	public boolean addNote(String token,Note note) 
	{
		
		//System.out.println(note.getUser().getUserId());
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			if(user!=null)
			{
			System.out.println(user);
			note.setUser(user);
			System.out.println(note.getUser());
			boolean check=noteDao.saveNote(note);
			if(check)
				return true;
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean editNote(Note updateNote, Integer id) 
	{
		Note note= noteDao.getNote(id);
		note.setTitle(updateNote.getTitle());
		note.setDescription(updateNote.getDescription());
		boolean check=noteDao.updateNote(note);
		if(check)
			return true;
		return false;
	}

}
