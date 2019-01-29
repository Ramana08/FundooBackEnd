package com.bridgeit.fundoo.service;

import com.bridgeit.fundoo.model.Note;

public interface INoteService {

	boolean addNote(String token,Note note);

	boolean editNote(Note note, Integer id);

}
