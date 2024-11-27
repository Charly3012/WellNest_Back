package com.wellnest.wellnest.Models;

import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import jakarta.persistence.*;
import jdk.jfr.Relational;
import lombok.*;

import java.util.Date;
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notes")
@Entity(name = "note")
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNote")
    private Long idNote;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    @Column(name = "date")
    private Date date;
    @Column(name = "content")
    private String content;
    @Column(name = "state")
    private boolean state;

    public Note(InsertNoteRequest request){
        this.content = request.content();
        this.state = request.state();
    }
}
