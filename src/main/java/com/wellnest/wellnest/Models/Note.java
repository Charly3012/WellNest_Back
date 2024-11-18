package com.wellnest.wellnest.Models;

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
    public Long idNote;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
    public Date date;
    public String content;
    public String state;
}
