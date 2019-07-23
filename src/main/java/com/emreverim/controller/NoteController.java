package com.emreverim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emreverim.entity.Note;
import com.emreverim.service.NoteService;
import com.emreverim.service.UserService;

@RestController
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteService noteService;

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public List<Note> getNotesByUserId(@PathVariable(name = "userId") Long userId) {

		return userService.getNotesById(userId);

	}

	@PostMapping("/{userId}")
	public void createNote(@PathVariable(name = "userId") Long userId, @Valid @RequestBody Note note) {

		noteService.saveNoteById(note, userId);

	}

	@PutMapping("/{userId}/{noteId}")
	public void updateNoteByUserIdAndNoteId(@PathVariable(name = "userId") Long userId,
			@PathVariable(name = "noteId") Long noteId, @Valid @RequestBody Note note) {

		noteService.updateNoteByUserIdAndNoteId(userId, noteId, note);

	}

	public NoteService getNoteService() {
		return noteService;
	}

	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

}
