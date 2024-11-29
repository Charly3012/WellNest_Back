package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Post;
import com.wellnest.wellnest.Models.Request.Post.InsertPostRequest;
import com.wellnest.wellnest.Models.Request.Post.ModifyPostRequest;
import com.wellnest.wellnest.Models.Responses.Post.PostResponse;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.PostRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    private Post findPostAndCheckOwnership(String token, Long noteId){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));
        Post post = postRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        if (!(post.getUser().getIdUser() == userId || user.getRole().toString().equals("ADMIN"))) {
            throw new SecurityException("You are not authorized to modify this note");
        }

        return post;
    }
    public Long insertPost(String token, InsertPostRequest insertRequest){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Post post = new Post(insertRequest);
        post.setUser(user);
        Post postSave = postRepository.save(post);

        return postSave.getIdPost();
    }

    public Page<PostResponse> getUserPost(String token, Pageable pageable){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.getReferenceById(userId);
        Page<Post> postPage = postRepository.findAllByUser(user, pageable);

        return postPage.map(PostResponse::new);
    }

    public Page<PostResponse> getAllPost(Pageable pageable){
        return postRepository.findAll(pageable).map(PostResponse::new);
    }

    public void deletePost(String token, Long postId){
        Post post = findPostAndCheckOwnership(token,postId);
        postRepository.delete(post);
    }

    public PostResponse modifyPost(String token, Long postId, ModifyPostRequest postRequest){
        Post post = findPostAndCheckOwnership(token, postId);
        post.setPostContent(postRequest.postContent());
        return new PostResponse(post);
    }

}
