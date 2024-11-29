package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Request.Note.InsertNoteRequest;
import com.wellnest.wellnest.Models.Request.Post.InsertPostRequest;
import com.wellnest.wellnest.Models.Request.Post.ModifyPostRequest;
import com.wellnest.wellnest.Models.Responses.Note.NoteResponse;
import com.wellnest.wellnest.Models.Responses.Post.PostResponse;
import com.wellnest.wellnest.Repository.PostRepository;
import com.wellnest.wellnest.Services.PostService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor

public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @PostMapping("InsertPost")
    public ResponseEntity<Void> insertPost(@RequestBody @Valid InsertPostRequest request,
                                       @RequestHeader("Authorization") String token, UriComponentsBuilder uriComponentsBuilder){
       Long idPost = postService.insertPost(token, request);
       URI location = uriComponentsBuilder.path("/api/v1/post/{id}").buildAndExpand(idPost).toUri();
       return  ResponseEntity.created(location).build();
    }

    @GetMapping("GetUserPost")
    public Page<PostResponse> GetUserPost(@RequestHeader("Authorization") String token,
                                          @PageableDefault(size = 20) Pageable pageable){
       return postService.getUserPost(token, pageable);
    }

    @GetMapping("GetAllPost")
    public  Page<PostResponse> GetAllPost(@PageableDefault(size = 20) Pageable pageable){
        return postService.getAllPost(pageable);
    }
    @DeleteMapping("{postId}")
    public ResponseEntity<Void> DeletePost(@RequestHeader("Authorization") String token,
                                           @PathVariable Long postId){
        postService.deletePost(token,postId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{postId}")
    @Transactional
    public ResponseEntity<PostResponse> ModifyPost(@RequestHeader("Authorization") String token,
                                                   @PathVariable Long postId, @RequestBody ModifyPostRequest postRequest){
        PostResponse postResponse = postService.modifyPost(token,postId,postRequest);
        return ResponseEntity.ok(postResponse);
    }

}
