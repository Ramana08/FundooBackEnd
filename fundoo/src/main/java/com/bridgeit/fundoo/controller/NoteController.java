package com.bridgeit.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.Response;
import com.bridgeit.fundoo.service.INoteService;

@RestController
//@RequestMapping("/fundoo")

@CrossOrigin(origins= {"http://localhost:4203"},exposedHeaders= {"token"})
public class NoteController 
{

	Response response;
	@Autowired
	INoteService noteService;


	//@PostMapping("/addNote")
	@RequestMapping(value="/addNote",method=RequestMethod.POST) 
	public ResponseEntity<Response> addNoteToUser(@RequestBody Note note,@RequestHeader("token")String token)
	{/*
		System.out.println(note);
		System.out.println(note.getUser().getUserId());*/
		System.out.println("entered token is "+token);
		noteService.addNote(token,note);
		
		response=new Response();

		response.setStatusCode(166);
		response.setStatus("note added successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/editNote/{id}")
	public ResponseEntity<Response> editNote(@RequestBody Note note,@PathVariable Integer id)
	{
		boolean check=noteService.editNote(note,id);
		response=new Response();
		if(check)
		{
			response.setStatus("note updated successfully");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatus("note is not present");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/getNote",method=RequestMethod.GET)
	public ResponseEntity<Note> getNote(@RequestHeader("token")String token)
	{
		//System.out.println("hi");
		Note userNote=noteService.getNote(token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note get successfully");
		System.out.println("before send "+userNote);
		return new ResponseEntity<Note>(userNote,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAllNote",method=RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNote(@RequestHeader("token") String token)
	{	
		List<Note> noteList=noteService.getAllNote(token);	
		return new ResponseEntity<List<Note>>(noteList,HttpStatus.OK);
	}
	
}
