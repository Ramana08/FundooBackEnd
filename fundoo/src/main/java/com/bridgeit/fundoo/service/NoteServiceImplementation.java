package com.bridgeit.fundoo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.INoteDao;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.utility.UserToken;

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
//			List<Note> archiveNotes=new ArrayList<>();
////			int index=0;
//			for (int i = 0; i < userNoteList.size(); i++) 
//			{
//				if(userNoteList.get(i).isArchive()==false && userNoteList.get(i).isTrash()==false )
//				{
//					archiveNotes.add(userNoteList.get(i));
//				}
//			}
//			System.out.println("archive Note");
//			System.out.println(archiveNotes);
//			return archiveNotes;
			return userNoteList;
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

			note.setArchive(true);
			
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
				if(userNoteList.get(i).isArchive()==true)
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
		System.out.println("before "+note.isArchive());
		if(note.isArchive()==false)
			note.setArchive(true);
		else
			note.setArchive(false);
		System.out.println("update after "+note.isArchive());
		noteDao.updateNote(note);
		return true;
	}
	@Override
	public boolean trashUpdateNote(Note note) 
	{
		if(note.isTrash()==false)
		note.setTrash(true);
		else
			note.setTrash(false);
		System.out.println("update after trash"+note.isArchive());
		noteDao.updateNote(note);
		return true;
	}
	@Override
	public List<Note> getTrashNote(String token) {
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			
			
			List<Note> userNoteList=noteDao.getAllNotes(user);
			System.out.println("userNote "+userNoteList);
			List<Note> trashNote=new ArrayList<>();
//			int index=0;
			for (int i = 0; i < userNoteList.size(); i++) 
			{
				if(userNoteList.get(i).isTrash()==true)
				{
					trashNote.add(userNoteList.get(i));
				}
			}
			System.out.println(trashNote);
			return trashNote;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return null;
	}
	@Override
	public boolean colorUpdateNote(Note note) {
		noteDao.updateNote(note);
		return true;
	}
	@Override
	public boolean deleteNote(Note note) {
		
		noteDao.deleteNote(note);
		return true;
	}
	@Override
	public boolean updateNote(Note note) {
		noteDao.updateNote(note);
		return true;
	}

	}

