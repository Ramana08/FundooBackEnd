package com.bridgeit.fundoo.service;

import java.util.List;

import com.bridgeit.fundoo.model.Note;

public interface INoteService {

	boolean addNote(String token,Note note);

	boolean editNote(Note note, Integer id);

	Note getNote(String token);

	List<Note> getAllNote(String token);

}
