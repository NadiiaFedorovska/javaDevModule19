package com.example.notesRestAPI.notes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class NoteDto {
    private long id;
    private String title;
    private String content;

    @JsonIgnore
    public boolean isTitleValid() {
        return title != null && !title.isBlank();
    }

    @JsonIgnore
    public boolean isContentValid() {
        return content != null && !content.isBlank();
    }

    public static List<NoteDto> from(Iterable<NoteEntity> noteEntities) {
        List<NoteDto> result = new ArrayList<>();

        for (NoteEntity noteEntity : noteEntities) {
            result.add(from(noteEntity));
        }
        return result;
    }

    public static NoteDto from(NoteEntity noteEntity) {
        return NoteDto
                .builder()
                .id(noteEntity.getId())
                .content(noteEntity.getContent())
                .title(noteEntity.getTitle())
                .build();
    }
}

