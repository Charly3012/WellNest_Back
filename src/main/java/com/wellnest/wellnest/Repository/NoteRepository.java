package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAllByUser(User user);
}
