package com.instagram.service;

import com.instagram.model.Post;
import java.util.List;

public interface PostService {

    boolean createPost(Post post);

    Post getPostById(int postId);

    List<Post> getPostsByUserId(int userId);

    List<Post> getAllPosts();

    boolean updatePost(Post post);

    boolean deletePost(int postId);
}