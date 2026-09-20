package com.instagram.dao;

import com.instagram.model.Post;

import java.util.List;

public class PostDAOImpl implements PostDAO {

    @Override
    public boolean addPost(Post post) {
        // TODO: Implement INSERT query
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        // TODO: Implement SELECT query by post ID
        return null;
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        // TODO: Implement SELECT query by user ID
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        // TODO: Implement SELECT ALL query
        return null;
    }

    @Override
    public boolean updatePost(Post post) {
        // TODO: Implement UPDATE query
        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        // TODO: Implement DELETE query
        return false;
    }
}