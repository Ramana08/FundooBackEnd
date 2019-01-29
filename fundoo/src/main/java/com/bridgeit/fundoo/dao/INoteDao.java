package com.bridgeit.fundoo.dao;

import com.bridgeit.fundoo.model.Note;

public interface INoteDao {

	boolean saveNote(Note note);

	Note getNote(Integer id);

	boolean updateNote(Note note);

}
