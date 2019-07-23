package com.emreverim.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class User {

	@Id
	private long id;

	private String emailaddress;

	@ManyToOne
	private List<Note> notes;

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
