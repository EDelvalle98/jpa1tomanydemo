package com.BlogDemo.jpa_one_to_many_demo.service;

import com.BlogDemo.jpa_one_to_many_demo.entities.Comment;
import com.BlogDemo.jpa_one_to_many_demo.entities.Post;
import com.BlogDemo.jpa_one_to_many_demo.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostService postService;

    public void addComment(Long postId, Comment comment) {

        Post post = postService.getAPostById(postId);  // fetch the post using the id given to us
        // add the comment to the post
        comment.setPost(post);
        //save the comment to the database
        commentRepo.save(comment);

    }

    public void deleteComment(Long commentId, Long postId) {
        Comment comment = commentRepo.findByIdAndPostId(commentId, postId);
        commentRepo.delete(comment);

    }

    public List<Comment> getAllComments() {
        // Fetch all comments using iteration logic
        Iterable<Comment> iterable = commentRepo.findAll();
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : iterable) {
            comments.add(comment);
        }
        return comments;
    }

    public Comment getCommentById(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElse(null);
        if (comment == null) {
            throw new RuntimeException("Comment not found with id " + commentId);
        }
        return comment;
    }

    public List<Comment> getAllCommentsByPost(Long postId) {
        Post post = postService.getAPostById(postId);
        List<Comment> comments = getAllComments(); // Call the above method
        List<Comment> postComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getPost().getId().equals(post.getId())) {
                postComments.add(comment);
            }
        }
        return postComments;
    }
}