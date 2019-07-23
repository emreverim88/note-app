package com.emreverim.service.imp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emreverim.config.EmailConfig;
import com.emreverim.entity.Note;
import com.emreverim.entity.User;
import com.emreverim.repository.UserRepository;
import com.emreverim.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailConfig emailConfig;

	@Override
	public List<Note> saveNoteById(Note note, Long userId) {

		List<Note> nodeList = userRepository.findById(userId).get().getNotes();
		nodeList.add(note);

		User user = new User();
		user.setEmailaddress(userRepository.findById(userId).get().getEmailaddress());
		user.setId(userId);
		user.setNotes(nodeList);

		userRepository.save(user);
		emailConfig.mailSender(user, "create");
		
		return user.getNotes();
	}

	@Override
	public Note updateNoteByUserIdAndNoteId(Long userId, Long noteId, @Valid Note note) {

		List<Note> myNoteList = userRepository.findById(userId).get().getNotes();
		
		for (Note note2 : myNoteList) {
			if (note2.getId() == noteId) {
				note2.setNote(note.getNote());
			}
		}
		
		User user = new User();
		user.setId(userId);
		user.setEmailaddress(userRepository.findById(userId).get().getEmailaddress());
		user.setNotes(myNoteList);

		userRepository.save(user);
		emailConfig.mailSender(user, "update");
		
		return note;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
