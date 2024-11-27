package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import com.wellnest.wellnest.Models.Request.Note.ModifyNoteRequest;
import com.wellnest.wellnest.Models.Responses.Note.NoteResponse;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.NoteRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    private Note findNoteAndCheckOwnership(String token, Long noteId){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!(note.getUser().getIdUser() == userId || user.getRole().toString().equals("ADMIN"))) {
            throw new SecurityException("You are not authorized to modify this note");
        }

        return note;
    }


    public Long insertNote(String token, InsertNoteRequest request)
    {
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Note note = new Note(request);
        note.setUser(user);
        note.setDate(new Date());
        Note savedNote = noteRepository.save(note);
        return savedNote.getIdNote();
    }

    public List<NoteResponse> getUserNotes(String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.getReferenceById(userId);
        var notesPage = noteRepository.findAllByUser(user);

        return notesPage.stream()
                .map(NoteResponse::new)
                .collect(Collectors.toList());

    }

    public void deleteNote(String token, Long noteId) {
        Note note = findNoteAndCheckOwnership(token, noteId);
        noteRepository.delete(note);
    }

    public NoteResponse modifyNote(String token, Long noteId, ModifyNoteRequest request) {
        Note note = findNoteAndCheckOwnership(token, noteId);
        note.setContent(request.content());
        return new NoteResponse(note);
    }

    public void changeNoteState(String token, Long noteId) {
        Note note = findNoteAndCheckOwnership(token, noteId);
        note.setState(!note.isState());
        noteRepository.save(note);
    }
}
