package com.example.notesRestAPI.notes;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NoteService {
    private static final Logger LOGGER = Logger.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    public List<NoteEntity> findAll() {
        LOGGER.info("exist method");
        return noteRepository.findAll();
    }

    public void createNoteFromDto(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        long id = generateUniqueId();
        noteEntity.setId(id);
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setContent(noteDto.getContent());
        noteRepository.save(noteEntity);
        LOGGER.info("add NoteEntity " + noteEntity + " to DB");
    }

    private long generateUniqueId() {
        Random random = new Random();
        long id = random.nextInt(100);
        LOGGER.info("generate Unique Id " + id);
        return id;
    }

    public boolean exists(long id){
        LOGGER.info("exist method");
        if (id == 0){
            return false;
        }
        return noteRepository.existsById(id);
    }

    public void updateNoteFromDto(NoteDto noteDto) {
        NoteEntity noteEntity = noteRepository.findById(noteDto.getId()).orElseThrow();
        noteEntity.setTitle(noteDto.getTitle());
        noteEntity.setContent(noteDto.getContent());
        LOGGER.info("Note was updated");
        noteRepository.save(noteEntity);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
        LOGGER.info("Note was deleted");
    }
}