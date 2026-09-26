package com.instagram.controller;

import com.instagram.service.*;

public class MainController {

    private final UserController userController;
    private final ProfileController profileController;
    private final PostController postController;
    private final CommentController commentController;
    private final LikeController likeController;
    private final FollowController followController;

    public MainController() {

        // Create Service objects

        UserService userService =
                new UserServiceImpl();

        ProfileService profileService =
                new ProfileServiceImpl();

        PostService postService =
                new PostServiceImpl();

        CommentService commentService =
                new CommentServiceImpl();

        LikeService likeService =
                new LikeServiceImpl();

        FollowService followService =
                new FollowServiceImpl();

        // Create Controller objects

        this.userController =
                new UserController(
                        userService
                );

        this.profileController =
                new ProfileController(
                        profileService
                );

        this.postController =
                new PostController(
                        postService
                );

        this.commentController =
                new CommentController(
                        commentService
                );

        this.likeController =
                new LikeController(
                        likeService
                );

        this.followController =
                new FollowController(
                        followService
                );
    }

    public UserController getUserController() {
        return userController;
    }

    public ProfileController getProfileController() {
        return profileController;
    }

    public PostController getPostController() {
        return postController;
    }

    public CommentController getCommentController() {
        return commentController;
    }

    public LikeController getLikeController() {
        return likeController;
    }

    public FollowController getFollowController() {
        return followController;
    }

    public static void main(String[] args) {

        MainController mainController =
                new MainController();

        System.out.println(
                "Instagram Application Started Successfully!"
        );
    }
}