package com.BlogDemo.jpa_one_to_many_demo.service;

import com.BlogDemo.jpa_one_to_many_demo.ResourceNotFoundException;
import com.BlogDemo.jpa_one_to_many_demo.entities.Post;
import com.BlogDemo.jpa_one_to_many_demo.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public List<Post> getAllPosts(){
        List<Post> postList = new ArrayList<>();  // make a list to store all the post into
        // repeatedly fetch all the post and store each post into the list
        for(Post post : postRepo.findAll()){
            postList.add(post);
        }
        // return the list
        return postList;
    }

    public void addPost(Post post){
        postRepo.save(post);
    }
    public Post getAPostById(Long postId){
        for (Post post: getAllPosts()){
            if(post.getId().equals(postId)){
                return post;
            }
        }
        throw new ResourceNotFoundException("Post with id of " + postId + " not found");
    }
    public void updatePost(Long postId, Post updatedPost) {
        Post existingPost = getAPostById(postId); // Find the post by ID
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setDescription(updatedPost.getDescription());
        existingPost.setContent(updatedPost.getContent());
        postRepo.save(existingPost); // Save the updated post
    }

    public List<Post> getAllPostsByTitle(String title) {
        List<Post> matchingPosts = new ArrayList<>();
        for (Post post : getAllPosts()) {
            if (post.getTitle().equalsIgnoreCase(title)) { // Case-insensitive match
                matchingPosts.add(post);
            }
        }
        return matchingPosts;
    }

    public void deletePost(Long postId) {
        Post post = getAPostById(postId); // Ensure the post exists
        postRepo.delete(post); // Delete the post
    }
}
