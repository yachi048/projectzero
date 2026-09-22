package com.instagram.controller;

import com.instagram.service.*;
import com.instagram.service.UserServiceImpl;
import com.instagram.service.ProfileServiceImpl;
import com.instagram.service.PostServiceImpl;
import com.instagram.service.CommentServiceImpl;
import com.instagram.service.LikeServiceImpl;
import com.instagram.service.FollowServiceImpl;

public class MainController {

    public static void main(String[] args) {

        // Create Service objects
        UserService userService = new UserServiceImpl();
        ProfileService profileService = new ProfileServiceImpl();
        PostService postService = new PostServiceImpl();
        CommentService commentService = new CommentServiceImpl();
        LikeService likeService = new LikeServiceImpl();
        FollowService followService = new FollowServiceImpl();

        // Create Controller objects
        UserController userController =
                new UserController(userService);

        ProfileController profileController =
                new ProfileController(profileService);

        PostController postController =
                new PostController(postService);

        CommentController commentController =
                new CommentController(commentService);

        LikeController likeController =
                new LikeController(likeService);

        FollowController followController =
                new FollowController(followService);

        System.out.println("Instagram Application Started Successfully!");
    }
}