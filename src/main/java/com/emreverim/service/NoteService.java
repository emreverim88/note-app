package com.emreverim.service;

import java.util.List;

import javax.validation.Valid;

import com.emreverim.entity.Note;

public interface NoteService {

	public List<Note> saveNoteById(Note note, Long userId);

	public Note updateNoteByUserIdAndNoteId(Long userId, Long noteId, @Valid Note note);

}
