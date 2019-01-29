package com.bridgeit.fundoo.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoo.model.Note;

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
			System.out.println(note);
		//	note.setUser(user);
			factory.getCurrentSession().save(note);
			System.out.println("note added successfull");
			return true;
		}
	//factory.getCurrentSession().save(user);
	return false;

	}

	@Override
	public Note getNote(Integer id) {
		Note note=(Note) factory.getCurrentSession().get(Note.class,id);
		return note;
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

}
