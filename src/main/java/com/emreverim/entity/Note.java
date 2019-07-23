package com.emreverim.entity;

import javax.persistence.Id;

public class Note {

	@Id
	private long id;

	private String note;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
