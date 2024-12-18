package com.BlogDemo.jpa_one_to_many_demo.controllers;

import com.BlogDemo.jpa_one_to_many_demo.entities.Comment;
import com.BlogDemo.jpa_one_to_many_demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

@PostMapping("/comments/{postId}/comments")
    public void addCommentToPost(@PathVariable Long postId, Comment comment){
    commentService.addComment(postId,comment);
}
    @DeleteMapping("/comments/{commentId}/posts/{postId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long postId){
        commentService.deleteComment(commentId, postId);
    }
    // Get All Comments
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Get a specific comment - throw a resource not found exception
    @GetMapping("/comments/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    // Get all comments for a specific post
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllCommentsByPost(@PathVariable Long postId) {
        return commentService.getAllCommentsByPost(postId);
    }
}
