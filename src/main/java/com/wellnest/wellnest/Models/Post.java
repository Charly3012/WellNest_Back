package com.wellnest.wellnest.Models;

import com.wellnest.wellnest.Models.Request.Post.InsertPostRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "posts")
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



    public Post(InsertPostRequest insertRequest){
        this.postContent = insertRequest.postContent();
        this.mood = insertRequest.mood();
        this.conter = 0;
        this.postDate = new Date();
    }


}

