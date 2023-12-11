package com.example.notesRestAPI.notes;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "notes")
public class NoteEntity {
    @Id
    private long id;
    private String title;
    private String content;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
