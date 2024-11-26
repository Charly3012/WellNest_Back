package com.wellnest.wellnest.Models;

import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import jakarta.persistence.*;
import jdk.jfr.Relational;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
@Entity(name = "note")
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNote")
    public Long idNote;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    @Column(name = "date")
    public Date date;
    @Column(name = "content")
    public String content;
    @Column(name = "state")
    public boolean state;

    public Note(InsertNoteRequest request){
        this.content = request.content();
        this.state = request.state();
    }
}
