package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
