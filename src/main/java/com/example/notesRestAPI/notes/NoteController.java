package com.example.notesRestAPI.notes;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
    private static final Logger LOGGER = Logger.getLogger(NoteController.class);
    private final NoteService noteService;

    @GetMapping("/list")
    public List<NoteDto> notesList() {
        LOGGER.info("get list method");
        return NoteDto.from(noteService.findAll());
    }

    @PostMapping("/create")
    public ModifyResponse createNote(@RequestBody NoteDto noteDto) {
        LOGGER.info("post create method");
        if (!noteDto.isTitleValid()) {
            return ModifyResponse.failed("Title can't be null or empty");
        }
            if (!noteDto.isContentValid()) {
            return ModifyResponse.failed("Content can't be null or empty");
        }
        noteService.createNoteFromDto(noteDto);
        return ModifyResponse.success("created successfully");
    }

    @PostMapping("/update")
    public ModifyResponse update(@RequestBody NoteDto noteDto) {
        if (!noteDto.isTitleValid()) {
            return ModifyResponse.failed("Title can't be null or empty");
        }
        if (!noteDto.isContentValid()) {
            return ModifyResponse.failed("Content can't be null or empty");
        }

        if (noteService.exists(noteDto.getId())) {
            noteService.updateNoteFromDto(noteDto);
            return ModifyResponse.success("updated successfully");
        } else {
            return ModifyResponse.failed("Note with Id <" + noteDto.getId() + "> doesn't exist");
        }
    }

    @PostMapping("/delete")
    public ModifyResponse delete(@RequestBody NoteDto noteDto) {
        if (noteService.exists(noteDto.getId())) {
            noteService.deleteById(noteDto.getId());
            return ModifyResponse.success("successfully deleted");
        } else {
            return ModifyResponse.failed("Note with Id <" + noteDto.getId() + "> doesn't exist");
        }
    }
}