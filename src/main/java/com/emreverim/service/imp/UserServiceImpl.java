package com.emreverim.service.imp;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emreverim.entity.Note;
import com.emreverim.entity.User;
import com.emreverim.repository.UserRepository;
import com.emreverim.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
		
	public List<User> getUsers() {
				
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@Override
	public List<Note> getNotesById(Long userId) {
		
		List<Note> userNotes = userRepository.findById(userId).get().getNotes();
		return userNotes;
	}

	@Override
	public void saveUser(@Valid User user) {

		userRepository.save(user);
	}

}
