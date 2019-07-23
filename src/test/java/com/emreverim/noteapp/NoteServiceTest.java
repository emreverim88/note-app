package com.emreverim.noteapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.emreverim.config.EmailConfig;
import com.emreverim.entity.Note;
import com.emreverim.entity.User;
import com.emreverim.repository.UserRepository;
import com.emreverim.service.NoteService;
import com.emreverim.service.imp.NoteServiceImpl;

@RunWith(SpringRunner.class)
public class NoteServiceTest {

	@TestConfiguration
	static class NoteAppApplicationTestsContextConfiguration {

		@Bean
		public NoteService noteService() {
			return new NoteServiceImpl();
		}
	}

	@Autowired
	private NoteService noteService;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private EmailConfig emailConfig;
	
	@Before
	public void setUp() {

		User user = new User();
		user.setId((long) 1);
		user.setEmailaddress("test@test.com");

		Note note = new Note();
		note.setId((long) 1);
		note.setNote("test note");

		List<Note> notes = new ArrayList<>();
		notes.add(note);
		user.setNotes(notes);

		Optional<User> userOptional = Optional.of(user);

		Mockito.when(userRepository.findById(user.getId())).thenReturn(userOptional);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Mockito.when(emailConfig.mailSender(user, "create")).thenReturn(Boolean.TRUE);

	}

	@Test
	public void test_saveNoteById() throws Exception {
		
		Note note = new Note();
		note.setNote("new note");
		
	    List<Note> notes = noteService.saveNoteById(note, (long) 1);

		Assert.assertEquals(notes.get(1).getNote(), "new note");
		
	}

	@Test
	public void test_updateNoteByUserIdAndNoteId() throws Exception {
		
		Note note = new Note();
		note.setNote("updated note");
		
	    note = noteService.updateNoteByUserIdAndNoteId((long) 1, (long) 1, note);
		
		Assert.assertEquals(note.getNote(), "updated note");
		
	}

}
