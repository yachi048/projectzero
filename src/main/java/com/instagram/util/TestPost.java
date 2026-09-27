package com.instagram.util;

import com.instagram.controller.PostController;
import com.instagram.model.Post;
import com.instagram.service.PostService;
import com.instagram.service.PostServiceImpl;

import java.util.List;

public class TestPost {

    public static void main(String[] args) {

        PostService postService =
                new PostServiceImpl();

        PostController postController =
                new PostController(postService);


        // ==========================================
        // 1. CREATE POST - USER 1
        // ==========================================

        Post post = new Post();

        post.setUserId(1);

        post.setCaption(
                "My first Instagram post"
        );

        boolean created =
                postController.createPost(post);

        System.out.println(
                "1. Create Post (user 1): "
                        + created
        );


// ==========================================
// 2. VIEW OWN POSTS - USER 1
// ==========================================

        List<Post> ownPosts =
                postController.getOwnPosts(1);

        System.out.println(
                "2. Own Posts (user 1): "
                        + ownPosts.size()
        );

        if (ownPosts.isEmpty()) {

            System.out.println(
                    "No post found. Check database and user_id."
            );

            return;
        }

// Get the latest post
        Post latestPost =
                ownPosts.get(0);

        int postId =
                latestPost.getPostId();

        System.out.println(
                "   Post ID for Like testing: "
                        + postId
        );

        System.out.println(
                "   Caption: "
                        + latestPost.getCaption()
        );


        // ==========================================
        // 3. CREATE POST - USER 2
        // ==========================================

        Post otherPost =
                new Post();

        otherPost.setUserId(2);

        otherPost.setCaption(
                "Post from another user"
        );

        boolean otherCreated =
                postController.createPost(
                        otherPost
                );

        System.out.println(
                "3. Create Post (user 2): "
                        + otherCreated
        );


        // ==========================================
        // 4. VIEW OTHER USERS' POSTS
        // ==========================================

        List<Post> otherPosts =
                postController.getOtherUsersPosts(1);

        System.out.println(
                "4. Other Users' Posts: "
                        + otherPosts.size()
        );


        // ==========================================
        // 5. VIEW POST DETAILS
        // ==========================================

        Post result =
                postController.getPostById(
                        postId
                );

        System.out.println(
                "5. Post Details: "
                        + (result != null
                        ? "PASS"
                        : "FAIL")
        );

        if (result != null) {

            System.out.println(
                    "   Post ID: "
                            + result.getPostId()
            );

            System.out.println(
                    "   User ID: "
                            + result.getUserId()
            );

            System.out.println(
                    "   Caption: "
                            + result.getCaption()
            );
        }


        // ==========================================
        // 6. UPDATE OWN POST
        // ==========================================

        if (result != null) {

            result.setCaption(
                    "Updated caption by owner"
            );

            boolean updatedByOwner =
                    postController.updateOwnPost(
                            result
                    );

            System.out.println(
                    "6. Update by owner: "
                            + updatedByOwner
            );
        }


        // ==========================================
        // 7. VERIFY UPDATE
        // ==========================================

        Post afterUpdate =
                postController.getPostById(
                        postId
                );

        boolean captionChanged =
                afterUpdate != null
                        && "Updated caption by owner"
                        .equals(
                                afterUpdate.getCaption()
                        );

        System.out.println(
                "7. Verify caption changed: "
                        + captionChanged
        );


        // ==========================================
        // 8. UPDATE SOMEONE ELSE'S POST
        // ==========================================

        Post hijackAttempt =
                new Post();

        hijackAttempt.setPostId(
                postId
        );

        hijackAttempt.setUserId(2);

        hijackAttempt.setCaption(
                "Hacked caption"
        );

        boolean updatedByStranger =
                postController.updateOwnPost(
                        hijackAttempt
                );

        System.out.println(
                "8. Update by non-owner: "
                        + updatedByStranger
        );


        // ==========================================
        // 9. DELETE SOMEONE ELSE'S POST
        // ==========================================

        boolean deletedByStranger =
                postController.deleteOwnPost(
                        postId,
                        2
                );

        System.out.println(
                "9. Delete by non-owner: "
                        + deletedByStranger
        );


        // ==========================================
        // 10. DO NOT DELETE POSTS
        // ==========================================

        System.out.println(
                "10. Posts are kept in database "
                        + "for Like testing."
        );


        // ==========================================
        // FINISHED
        // ==========================================

        System.out.println(
                "Post testing completed."
        );
    }
}