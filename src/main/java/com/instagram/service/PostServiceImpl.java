package com.instagram.service;

import com.instagram.model.Post;
import java.util.List;

public class PostServiceImpl implements PostService {

    @Override
    public boolean createPost(Post post) {
        // TODO: Add validation and call PostDAO
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        // TODO: Call PostDAO
        return null;
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {
        // TODO: Call PostDAO
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        // TODO: Call PostDAO
        return null;
    }

    @Override
    public boolean updatePost(Post post) {
        // TODO: Add validation and call PostDAO
        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        // TODO: Call PostDAO
        return false;
    }
}