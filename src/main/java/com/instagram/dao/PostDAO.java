package com.instagram.dao;

import com.instagram.model.Post;

import java.util.List;

public interface PostDAO {

    boolean addPost(Post post);

    Post getPostById(int postId);

    List<Post> getPostsByUserId(int userId);

    List<Post> getAllPosts();

    boolean updatePost(Post post);

    boolean deletePost(int postId);
}