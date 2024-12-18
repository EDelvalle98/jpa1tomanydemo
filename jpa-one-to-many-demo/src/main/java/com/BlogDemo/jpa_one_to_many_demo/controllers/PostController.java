package com.BlogDemo.jpa_one_to_many_demo.controllers;

import com.BlogDemo.jpa_one_to_many_demo.entities.Post;
import com.BlogDemo.jpa_one_to_many_demo.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;



@RestController
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody Post post){
        postService.addPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        logger.info("Created a new post with an id of " + post.getId());
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getAPostById(id));
    }
    @PutMapping("/posts/{id}")
    public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        postService.updatePost(id, updatedPost);
        logger.info("Updated post with an id of " + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<Post>> getPostsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(postService.getAllPostsByTitle(title));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        logger.info("Deleted post with an id of " + id);
        return ResponseEntity.noContent().build();
    }
}
