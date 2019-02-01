package com.bridgeit.fundoo.service;

import java.util.List;

import com.bridgeit.fundoo.model.Note;

public interface INoteService {

	boolean addNote(String token,Note note);

	boolean editNote(Note note, Integer id);

	Note getNote(String token);

	List<Note> getAllNote(String token);

	boolean archiveNote(String token,Note note);

	List<Note> getArchiveNote(String token);

	boolean updateArchive(Note note);

	boolean deleteNote(Note note);

	List<Note> getTrashNote(String token);

}
