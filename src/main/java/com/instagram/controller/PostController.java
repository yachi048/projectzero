package com.instagram.controller;

import com.instagram.model.Post;
import com.instagram.service.PostService;

import java.util.List;
import java.util.logging.Logger;

public class PostController {

    private static final Logger logger =
            Logger.getLogger(PostController.class.getName());

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
        logger.info("PostController initialized");
    }

    public boolean createPost(Post post) {
        logger.info("Create post request");
        return postService.createPost(post);
    }

    public List<Post> getOwnPosts(int userId) {
        logger.info("Get own posts request for userId: " + userId);
        return postService.getOwnPosts(userId);
    }

    public List<Post> getOtherUsersPosts(int userId) {
        logger.info("Get other users' posts request for userId: " + userId);
        return postService.getOtherUsersPosts(userId);
    }

    public Post getPostById(int postId) {
        logger.info("Get post details request for postId: " + postId);
        return postService.getPostById(postId);
    }

    public boolean updateOwnPost(Post post) {
        logger.info("Update own post request for postId: "
                + (post != null ? post.getPostId() : "null"));
        return postService.updateOwnPost(post);
    }

    public boolean deleteOwnPost(int postId, int userId) {
        logger.info("Delete own post request for postId: " + postId);
        return postService.deleteOwnPost(postId, userId);
    }
}