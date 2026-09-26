package com.instagram.dao;

import com.instagram.model.Post;

import java.util.List;

public interface PostDAO {

    boolean addPost(Post post);

    List<Post> getPostsByUserId(int userId);

    List<Post> getOtherUsersPosts(int userId);

    Post getPostById(int postId);

    boolean updatePost(Post post);

    boolean deletePost(int postId, int userId);
}