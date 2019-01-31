package com.bridgeit.fundoo.service;

import java.util.ArrayList;
import java.util.List;

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
		//	System.out.println(user);
			note.setUser(user);
			//System.out.println(note.getUser());
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
		/*Note note= noteDao.getNote(id);
		note.setTitle(updateNote.getTitle());
		note.setDescription(updateNote.getDescription());
		boolean check=noteDao.updateNote(note);
		if(check)
			return true;*/
		return false;
	}
	@Override
	public Note getNote(String token) 
	{
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			
			
			Note userNote=noteDao.getNote(user);
			return userNote;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Note> getAllNote(String token) 
	{
		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);

			List<Note> userNoteList=noteDao.getAllNotes(user);
			System.out.println("userNote "+userNoteList);
			List<Note> archiveNotes=new ArrayList<>();
//			int index=0;
			for (int i = 0; i < userNoteList.size(); i++) 
			{
				if(userNoteList.get(i).getArchive()==0)
				{
					archiveNotes.add(userNoteList.get(i));
				}
			}
			System.out.println(archiveNotes);
			return archiveNotes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean archiveNote(String token, Note note) 
	{
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			if(user!=null)
			{
		//	System.out.println(user);
			note.setUser(user);

			note.setArchive(1);
			
			//System.out.println(note.getUser());
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
	public List<Note> getArchiveNote(String token) {
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			
			
			List<Note> userNoteList=noteDao.getAllNotes(user);
			System.out.println("userNote "+userNoteList);
			List<Note> archiveNotes=new ArrayList<>();
//			int index=0;
			for (int i = 0; i < userNoteList.size(); i++) 
			{
				if(userNoteList.get(i).getArchive()==1)
				{
					archiveNotes.add(userNoteList.get(i));
				}
			}
			System.out.println(archiveNotes);
			return archiveNotes;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return null;
	}
	@Override
	public boolean updateArchive(Note note) {
		System.out.println("before "+note.getArchive());
		if(note.getArchive()==0)
			note.setArchive(1);
		else
			note.setArchive(0);
		System.out.println("update after "+note.getArchive());
		noteDao.updateNote(note);
		return true;
	}
	@Override
	public boolean deleteNote(Note note) 
	{
		noteDao.deleteNote(note);
		return false;
	}

}
