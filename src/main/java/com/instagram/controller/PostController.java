package com.instagram.controller;

import com.instagram.model.Post;
import com.instagram.service.PostService;

import java.util.List;

public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public boolean createPost(Post post) {
        // TODO: Call PostService
        return false;
    }

    public Post getPostById(int postId) {
        // TODO: Call PostService
        return null;
    }

    public List<Post> getPostsByUserId(int userId) {
        // TODO: Call PostService
        return null;
    }

    public List<Post> getAllPosts() {
        // TODO: Call PostService
        return null;
    }

    public boolean updatePost(Post post) {
        // TODO: Call PostService
        return false;
    }

    public boolean deletePost(int postId) {
        // TODO: Call PostService
        return false;
    }
}