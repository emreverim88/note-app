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

import com.emreverim.entity.Note;
import com.emreverim.entity.User;
import com.emreverim.repository.UserRepository;
import com.emreverim.service.UserService;
import com.emreverim.service.imp.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@TestConfiguration
	static class NoteAppApplicationTestsContextConfiguration {

		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
		
	}

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

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
		
		List<User> users = new ArrayList<>();
		users.add(user);
		
		Mockito.when(userRepository.findAll()).thenReturn(users);

	}

	@Test
	public void test_getUsers() throws Exception {

		List<User> users = userService.getUsers();
		
		Assert.assertEquals(users.get(0).getEmailaddress(), "test@test.com");
		
	}
	

	@Test
	public void test_getNotesByUserId() throws Exception {

		Long noteId = (long) 1;

		List<Note> notes = userService.getNotesById(noteId);
		
		Assert.assertEquals(notes.get(0).getNote(), "test note");
		
	}


}
