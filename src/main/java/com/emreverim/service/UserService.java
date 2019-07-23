package com.emreverim.service;

import java.util.List;

import javax.validation.Valid;

import com.emreverim.entity.Note;
import com.emreverim.entity.User;

public interface UserService {

	public List<User> getUsers();

	public void saveUser(@Valid User user);

	public List<Note> getNotesById(Long userId);
	
}
