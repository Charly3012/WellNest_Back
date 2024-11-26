package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
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
}
