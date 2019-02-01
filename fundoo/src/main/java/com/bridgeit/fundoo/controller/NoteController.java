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
		System.out.println("return Note "+noteList);
		return new ResponseEntity<List<Note>>(noteList,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/archiveNote",method=RequestMethod.POST)
	public ResponseEntity<Response> archiveNote(@RequestBody Note note,@RequestHeader("token") String token)
	{
		System.out.println(token);
		noteService.archiveNote(token,note);
		response=new Response();
		response.setStatusCode(200);
		response.setStatus("archive successfully");
		System.out.println("conroller");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/getArchiveNote",method=RequestMethod.GET)
	public ResponseEntity<List<Note>> getArchiveNote(@RequestHeader("token")String token)
	{
		//System.out.println("hi");
		List<Note> userNote=noteService.getArchiveNote(token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note get successfully");
		System.out.println("before send "+userNote);
		return new ResponseEntity<List<Note>>(userNote,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateArchiveNote" , method=RequestMethod.POST)
	public ResponseEntity<Response> updateArchive(@RequestBody Note note)
	{
		//noteService.updateArchive(note);
		
		System.out.println("archive "+note.getArchive());
		noteService.updateArchive(note);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note updated successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/trashNote", method=RequestMethod.POST)
	public ResponseEntity<Response> deleteNote(@RequestBody Note note)
	{
		
		System.out.println("delete note");
		System.out.println(note);
		noteService.deleteNote(note);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note deleted successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="getTrashNote",method=RequestMethod.GET)
	public ResponseEntity<List<Note>> getTrashNote(@RequestHeader("token") String token)
	{
		List<Note> trashNote=noteService.getTrashNote(token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note get successfully");
		System.out.println("before send "+trashNote);
		return new ResponseEntity<List<Note>>(trashNote,HttpStatus.OK);
	}
	
}
