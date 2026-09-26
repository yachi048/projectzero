package com.instagram.service;

import com.instagram.model.Post;

import java.util.List;

public interface PostService {

    boolean createPost(Post post);

    List<Post> getOwnPosts(int userId);

    List<Post> getOtherUsersPosts(int userId);

    Post getPostById(int postId);

    boolean updateOwnPost(Post post);

    boolean deleteOwnPost(int postId, int userId);
}