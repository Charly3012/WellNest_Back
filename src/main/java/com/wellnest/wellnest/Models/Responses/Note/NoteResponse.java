package com.wellnest.wellnest.Models.Responses.Note;

import com.wellnest.wellnest.Models.Note;

import java.util.Date;

public record NoteResponse (
        Date date,
        String content,
        boolean state
){
    public NoteResponse(Note note){
        this(note.getDate(), note.getContent(), note.isState());
    }
}
