package com.bridgeit.fundoo.dao;

import java.util.List;

import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;

public interface INoteDao {

	boolean saveNote(Note note);

	Note getNote(User user);

	boolean updateNote(Note note);

	List<Note> getAllNotes(User user);

	

}
