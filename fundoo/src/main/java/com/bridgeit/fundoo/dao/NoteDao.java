package com.bridgeit.fundoo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;

@Repository
public class NoteDao implements INoteDao
{

	@Autowired
	SessionFactory factory;
	
	@Override
	public boolean saveNote(Note note) 
	{
		if(factory!=null)
		{
		//System.out.println(factory);
			//System.out.println(note);
		//	note.setUser(user);
			factory.getCurrentSession().save(note);
			System.out.println("note added successfull");
			return true;
		}
	//factory.getCurrentSession().save(user);
	return false;

	}

	@Override
	public Note getNote(User user) {
		System.out.println(user.getUserId());
		Note userNote=(Note) factory.getCurrentSession().get(Note.class,user.getUserId());
		System.out.println("user new note "+userNote);
		return userNote;
	}

	@Override
	public boolean updateNote(Note note) 
	{
		if(factory!=null)
		{
			factory.getCurrentSession().update(note);
			System.out.println("Note updated successfully");
			return true;
		}
		return false;
	}

	@Override
	public List<Note> getAllNotes(User user) {
		if(factory!=null)
		{
			  List<Note> noteList =factory. getCurrentSession().createCriteria(Note.class).createCriteria("user").add(Restrictions.eq("userId", user.getUserId())).list();
			  return noteList;
		}
		return null;
	}

	
}
