package com.wellnest.wellnest.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPost")
    public long idPost;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", nullable = false)
    public User user;
    @Column(name = "postDate")
    public Date postDate;
    @Column(name = "postContent")
    public String postContent;
    @Column(name = "conter")
    public int conter;
    @Column(name = "mood")
    public String mood;

}
