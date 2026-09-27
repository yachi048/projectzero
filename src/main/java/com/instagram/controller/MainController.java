package com.instagram.controller;

import com.instagram.model.Comment;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.service.*;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainController {

    private static final Logger logger =
            Logger.getLogger(MainController.class.getName());

    private final UserController userController;
    private final ProfileController profileController;
    private final PostController postController;
    private final CommentController commentController;
    private final LikeController likeController;
    private final FollowController followController;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainController() {

        logger.info("Starting Instagram application...");

        // ==============================
        // SERVICES
        // ==============================

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

        // ==============================
        // CONTROLLERS
        // ==============================

        userController =
                new UserController(userService);

        profileController =
                new ProfileController(profileService);

        postController =
                new PostController(postService);

        commentController =
                new CommentController(commentService);

        likeController =
                new LikeController(likeService);

        followController =
                new FollowController(followService);

        logger.info("All controllers initialized successfully");
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        MainController app =
                new MainController();

        app.startApplication();
    }

    // =========================================================
    // START APPLICATION
    // =========================================================

    public void startApplication() {

        Scanner scanner =
                new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       INSTAGRAM APPLICATION");
            System.out.println("=================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            String choice =
                    scanner.nextLine().trim();

            switch (choice) {

                case "1":

                    register(scanner);
                    break;

                case "2":

                    User loggedInUser =
                            login(scanner);

                    if (loggedInUser != null) {

                        instagramMenu(
                                scanner,
                                loggedInUser
                        );
                    }

                    break;

                case "3":

                    System.out.println();
                    System.out.println(
                            "Thank you for using Instagram!"
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    // =========================================================
    // REGISTER
    // =========================================================

    private void register(Scanner scanner) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          USER REGISTER");
        System.out.println("=================================");

        System.out.print("Enter Username: ");

        String username =
                scanner.nextLine().trim();

        System.out.print("Enter Email: ");

        String email =
                scanner.nextLine().trim();

        System.out.print("Enter Password: ");

        String password =
                scanner.nextLine().trim();

        if (username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()) {

            System.out.println();
            System.out.println(
                    "Username, email and password cannot be empty."
            );

            return;
        }

        User user =
                new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(password);

        try {

            boolean registered =
                    userController.registerUser(user);

            System.out.println();

            if (registered) {

                System.out.println(
                        "================================="
                );

                System.out.println(
                        "    Registration Successful!"
                );

                System.out.println(
                        "================================="
                );

            } else {

                System.out.println(
                        "================================="
                );

                System.out.println(
                        "    Registration Failed!"
                );

                System.out.println(
                        "================================="
                );
            }

        } catch (Exception e) {

            logger.warning(
                    "Registration failed: "
                            + e.getMessage()
            );

            System.out.println();
            System.out.println(
                    "Registration Failed!"
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private User login(Scanner scanner) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("             LOGIN");
        System.out.println("=================================");

        System.out.print("Enter Username: ");

        String username =
                scanner.nextLine().trim();

        System.out.print("Enter Password: ");

        String password =
                scanner.nextLine().trim();

        try {

            User user =
                    userController.login(
                            username,
                            password
                    );

            System.out.println();

            if (user != null) {

                logger.info(
                        "Login successful for username: "
                                + username
                );

                System.out.println(
                        "================================="
                );

                System.out.println(
                        "       LOGIN SUCCESSFUL"
                );

                System.out.println(
                        "================================="
                );

                System.out.println(
                        "Welcome, "
                                + user.getUsername()
                );

                return user;
            }

        } catch (Exception e) {

            logger.warning(
                    "Login failed for username: "
                            + username
                            + " - "
                            + e.getMessage()
            );
        }

        System.out.println(
                "================================="
        );

        System.out.println(
                "          LOGIN FAILED"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "Invalid username or password"
        );

        return null;
    }

    // =========================================================
    // INSTAGRAM MENU
    // =========================================================

    private void instagramMenu(
            Scanner scanner,
            User user) {

        boolean loggedIn = true;

        while (loggedIn) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       INSTAGRAM HOME");
            System.out.println("=================================");

            System.out.println(
                    "Welcome, "
                            + user.getUsername()
            );

            System.out.println();

            System.out.println("1. View Profile");
            System.out.println("2. Create Post");
            System.out.println("3. View Own Posts");
            System.out.println("4. Like Post");
            System.out.println("5. Unlike Post");
            System.out.println("6. Add Comment");
            System.out.println("7. View Comments");
            System.out.println("8. Follow User");
            System.out.println("9. Unfollow User");
            System.out.println("10. View Followers");
            System.out.println("11. View Following");
            System.out.println("12. View My Stats");
            System.out.println("13. Logout");

            System.out.println(
                    "================================="
            );

            System.out.print("Enter your choice: ");

            String choice =
                    scanner.nextLine().trim();

            switch (choice) {

                // =================================================
                // 1. VIEW PROFILE
                // =================================================

                case "1":

                    viewProfile(user);
                    break;

                // =================================================
                // 2. CREATE POST
                // =================================================

                case "2":

                    createPost(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 3. VIEW OWN POSTS
                // =================================================

                case "3":

                    viewOwnPosts(user);
                    break;

                // =================================================
                // 4. LIKE POST
                // =================================================

                case "4":

                    likePost(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 5. UNLIKE POST
                // =================================================

                case "5":

                    unlikePost(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 6. ADD COMMENT
                // =================================================

                case "6":

                    addComment(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 7. VIEW COMMENTS
                // =================================================

                case "7":

                    viewComments(scanner);
                    break;

                // =================================================
                // 8. FOLLOW USER
                // =================================================

                case "8":

                    followUser(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 9. UNFOLLOW USER
                // =================================================

                case "9":

                    unfollowUser(
                            scanner,
                            user
                    );

                    break;

                // =================================================
                // 10. VIEW FOLLOWERS
                // =================================================

                case "10":

                    viewFollowers(user);
                    break;

                // =================================================
                // 11. VIEW FOLLOWING
                // =================================================

                case "11":

                    viewFollowing(user);
                    break;

                // =================================================
                // 12. VIEW MY STATS
                // =================================================

                case "12":

                    viewMyStats(user);
                    break;

                // =================================================
                // 13. LOGOUT
                // =================================================

                case "13":

                    System.out.println();
                    System.out.println(
                            "Logging out..."
                    );

                    logger.info(
                            "User logged out: "
                                    + user.getUsername()
                    );

                    loggedIn = false;

                    System.out.println(
                            "Logout successful!"
                    );

                    break;

                default:

                    System.out.println();
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    // =========================================================
    // VIEW PROFILE
    // =========================================================

    private void viewProfile(User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          MY PROFILE");
        System.out.println("=================================");

        System.out.println(
                "User ID: "
                        + user.getUserId()
        );

        System.out.println(
                "Username: "
                        + user.getUsername()
        );

        System.out.println(
                "Email: "
                        + user.getEmail()
        );

        System.out.println(
                "Status: "
                        + user.getStatus()
        );

        System.out.println(
                "Role: "
                        + user.getRole()
        );

        System.out.println(
                "================================="
        );
    }

    // =========================================================
    // CREATE POST
    // =========================================================

    private void createPost(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          CREATE POST");
        System.out.println("=================================");

        System.out.print("Enter Caption: ");

        String caption =
                scanner.nextLine().trim();

        if (caption.isEmpty()) {

            System.out.println(
                    "Caption cannot be empty."
            );

            return;
        }

        Post post =
                new Post();

        post.setUserId(
                user.getUserId()
        );

        post.setCaption(caption);

        try {

            boolean created =
                    postController.createPost(post);

            if (created) {

                System.out.println();
                System.out.println(
                        "Post created successfully!"
                );

            } else {

                System.out.println();
                System.out.println(
                        "Failed to create post."
                );
            }

        } catch (Exception e) {

            logger.warning(
                    "Error creating post: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to create post."
            );
        }
    }

    // =========================================================
    // VIEW OWN POSTS
    // =========================================================

    private void viewOwnPosts(User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          YOUR POSTS");
        System.out.println("=================================");

        try {

            List<Post> posts =
                    postController.getOwnPosts(
                            user.getUserId()
                    );

            if (posts == null || posts.isEmpty()) {

                System.out.println(
                        "You don't have any posts."
                );

                return;
            }

            for (Post post : posts) {

                System.out.println();

                System.out.println(
                        "Post ID: "
                                + post.getPostId()
                );

                System.out.println(
                        "Caption: "
                                + post.getCaption()
                );

                System.out.println(
                        "Created At: "
                                + post.getCreatedAt()
                );

                System.out.println(
                        "---------------------------------"
                );
            }

        } catch (Exception e) {

            logger.warning(
                    "Error fetching own posts: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to fetch posts."
            );
        }
    }

    // =========================================================
    // LIKE POST
    // =========================================================

    private void likePost(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("            LIKE POST");
        System.out.println("=================================");

        System.out.print("Enter Post ID: ");

        String input =
                scanner.nextLine().trim();

        try {

            int postId =
                    Integer.parseInt(input);

            Like like =
                    new Like();

            like.setUserId(
                    user.getUserId()
            );

            like.setPostId(postId);

            boolean liked =
                    likeController.likePost(like);

            System.out.println();

            if (liked) {

                System.out.println(
                        "Post liked successfully!"
                );

            } else {

                System.out.println(
                        "Failed to like post."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Post ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error liking post: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to like post."
            );
        }
    }

    // =========================================================
    // UNLIKE POST
    // =========================================================

    private void unlikePost(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           UNLIKE POST");
        System.out.println("=================================");

        System.out.print("Enter Post ID: ");

        String input =
                scanner.nextLine().trim();

        try {

            int postId =
                    Integer.parseInt(input);

            boolean unliked =
                    likeController.unlikePost(
                            user.getUserId(),
                            postId
                    );

            System.out.println();

            if (unliked) {

                System.out.println(
                        "Post unliked successfully!"
                );

            } else {

                System.out.println(
                        "Failed to unlike post."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Post ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error unliking post: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to unlike post."
            );
        }
    }

    // =========================================================
    // ADD COMMENT
    // =========================================================

    private void addComment(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          ADD COMMENT");
        System.out.println("=================================");

        System.out.print("Enter Post ID: ");

        String postInput =
                scanner.nextLine().trim();

        try {

            int postId =
                    Integer.parseInt(postInput);

            System.out.print("Enter Comment: ");

            String commentText =
                    scanner.nextLine().trim();

            if (commentText.isEmpty()) {

                System.out.println();
                System.out.println(
                        "Comment cannot be empty."
                );

                return;
            }

            Comment comment =
                    new Comment();

            comment.setUserId(
                    user.getUserId()
            );

            comment.setPostId(postId);

            comment.setCommentText(
                    commentText
            );

            /*
             * This is a normal comment.
             * It is not a reply.
             */
            comment.setParentCommentId(null);

            boolean added =
                    commentController.addComment(
                            comment
                    );

            System.out.println();

            if (added) {

                System.out.println(
                        "Comment added successfully!"
                );

                System.out.println(
                        "Your message: "
                                + commentText
                );

            } else {

                System.out.println(
                        "Failed to add comment."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Post ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error adding comment: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to add comment."
            );
        }
    }

    // =========================================================
    // VIEW COMMENTS
    // =========================================================

    private void viewComments(
            Scanner scanner) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          VIEW COMMENTS");
        System.out.println("=================================");

        System.out.print("Enter Post ID: ");

        String input =
                scanner.nextLine().trim();

        try {

            int postId =
                    Integer.parseInt(input);

            List<Comment> comments =
                    commentController.getCommentsByPostId(
                            postId
                    );

            System.out.println();

            if (comments == null || comments.isEmpty()) {

                System.out.println(
                        "No comments found for this post."
                );

                return;
            }

            System.out.println(
                    "Comments for Post ID: "
                            + postId
            );

            System.out.println(
                    "================================="
            );

            for (Comment comment : comments) {

                System.out.println();

                System.out.println(
                        "Comment ID: "
                                + comment.getCommentId()
                );

                System.out.println(
                        "User ID: "
                                + comment.getUserId()
                );

                System.out.println(
                        "Message: "
                                + comment.getCommentText()
                );

                if (comment.getParentCommentId() != null) {

                    System.out.println(
                            "Reply to Comment ID: "
                                    + comment.getParentCommentId()
                    );
                }

                System.out.println(
                        "---------------------------------"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Post ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error fetching comments: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to fetch comments."
            );
        }
    }

    // =========================================================
    // FOLLOW USER
    // =========================================================

    private void followUser(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          FOLLOW USER");
        System.out.println("=================================");

        System.out.print(
                "Enter User ID to follow: "
        );

        String input =
                scanner.nextLine().trim();

        try {

            int targetUserId =
                    Integer.parseInt(input);

            if (targetUserId == user.getUserId()) {

                System.out.println();
                System.out.println(
                        "You cannot follow yourself."
                );

                return;
            }

            boolean followed =
                    followController.followUser(
                            user.getUserId(),
                            targetUserId
                    );

            System.out.println();

            if (followed) {

                System.out.println(
                        "User followed successfully!"
                );

            } else {

                System.out.println(
                        "Follow failed."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid User ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error following user: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to follow user."
            );
        }
    }

    // =========================================================
    // UNFOLLOW USER
    // =========================================================

    private void unfollowUser(
            Scanner scanner,
            User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("         UNFOLLOW USER");
        System.out.println("=================================");

        System.out.print(
                "Enter User ID to unfollow: "
        );

        String input =
                scanner.nextLine().trim();

        try {

            int targetUserId =
                    Integer.parseInt(input);

            if (targetUserId == user.getUserId()) {

                System.out.println();
                System.out.println(
                        "You cannot unfollow yourself."
                );

                return;
            }

            boolean unfollowed =
                    followController.unfollowUser(
                            user.getUserId(),
                            targetUserId
                    );

            System.out.println();

            if (unfollowed) {

                System.out.println(
                        "User unfollowed successfully!"
                );

            } else {

                System.out.println(
                        "Unfollow failed."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid User ID."
            );

        } catch (Exception e) {

            logger.warning(
                    "Error unfollowing user: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to unfollow user."
            );
        }
    }

    // =========================================================
    // VIEW FOLLOWERS
    // =========================================================

    private void viewFollowers(User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          MY FOLLOWERS");
        System.out.println("=================================");

        try {

            int followers =
                    followController.getFollowerCount(
                            user.getUserId()
                    );

            System.out.println(
                    "Total Followers: "
                            + followers
            );

        } catch (Exception e) {

            logger.warning(
                    "Error getting followers: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to get followers."
            );
        }

        System.out.println(
                "================================="
        );
    }

    // =========================================================
    // VIEW FOLLOWING
    // =========================================================

    private void viewFollowing(User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          MY FOLLOWING");
        System.out.println("=================================");

        try {

            int following =
                    followController.getFollowingCount(
                            user.getUserId()
                    );

            System.out.println(
                    "Total Following: "
                            + following
            );

        } catch (Exception e) {

            logger.warning(
                    "Error getting following: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to get following."
            );
        }

        System.out.println(
                "================================="
        );
    }

    // =========================================================
    // VIEW MY STATS
    // =========================================================

    private void viewMyStats(User user) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("           MY STATS");
        System.out.println("=================================");

        try {

            int followers =
                    followController.getFollowerCount(
                            user.getUserId()
                    );

            int following =
                    followController.getFollowingCount(
                            user.getUserId()
                    );

            System.out.println(
                    "Username  : "
                            + user.getUsername()
            );

            System.out.println(
                    "Followers : "
                            + followers
            );

            System.out.println(
                    "Following : "
                            + following
            );

        } catch (Exception e) {

            logger.warning(
                    "Error getting user stats: "
                            + e.getMessage()
            );

            System.out.println(
                    "Unable to get user statistics."
            );
        }

        System.out.println(
                "================================="
        );
    }
}