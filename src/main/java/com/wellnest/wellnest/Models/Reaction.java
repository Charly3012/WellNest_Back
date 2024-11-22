package com.wellnest.wellnest.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reaction")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReaction")
    public long idReaction;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    public User user;
    @ManyToOne(fetch = FetchType.LAZY) // Usa LAZY para cargar Post solo cuando se necesite
    @JoinColumn(name = "idPost", nullable = false)
    public Post post;
    @Column(name = "reactionType")
    public String reactionType;
    @Column(name = "reactionDate")
    public Date reactionDate;
}
