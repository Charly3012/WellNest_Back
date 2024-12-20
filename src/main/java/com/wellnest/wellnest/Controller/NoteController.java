package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import com.wellnest.wellnest.Models.Request.Note.ModifyNoteRequest;
import com.wellnest.wellnest.Models.Responses.Note.NoteResponse;
import com.wellnest.wellnest.Services.NoteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Page;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("InsertNote")
    public ResponseEntity<Void> insertNote(@RequestBody @Valid InsertNoteRequest request,
                                           @RequestHeader("Authorization") String token, UriComponentsBuilder uriComponentsBuilder) {
        Long noteId = noteService.insertNote(token, request);
        URI location = uriComponentsBuilder.path("/api/v1/notes/{id}").buildAndExpand(noteId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("GetUserNotes")
    public ResponseEntity<List<NoteResponse>> GetUserNotes(@RequestHeader("Authorization") String token) {
        List<NoteResponse> notes = noteService.getUserNotes(token);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<Void> deleteNote(@RequestHeader("Authorization") String token,
                                           @PathVariable Long noteId) {
        noteService.deleteNote(token, noteId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{noteId}")
    @Transactional
    public ResponseEntity<NoteResponse> modifyNote(@RequestHeader("Authorization") String token,
                                                   @PathVariable Long noteId,
                                                   @RequestBody ModifyNoteRequest request) {
        NoteResponse noteResponse = noteService.modifyNote(token, noteId, request);
        return ResponseEntity.ok(noteResponse);
    }

    @PostMapping("changeState/{noteId}")
    @Transactional
    public ResponseEntity<Void> changeNoteState(@RequestHeader("Authorization") String token,
                                                @PathVariable Long noteId){
        noteService.changeNoteState(token, noteId);
        return ResponseEntity.noContent().build();
    }

}
