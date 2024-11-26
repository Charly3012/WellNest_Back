package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.NoteRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Long userId = jwtService.getUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Note note = new Note(request);
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        return savedNote.getIdNote();
    }
}
