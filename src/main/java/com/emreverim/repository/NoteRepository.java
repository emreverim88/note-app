package com.emreverim.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emreverim.entity.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, Long> {

}
