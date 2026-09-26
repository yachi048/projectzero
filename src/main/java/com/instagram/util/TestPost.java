package com.instagram.util;

import com.instagram.controller.PostController;
import com.instagram.model.Post;
import com.instagram.service.PostService;
import com.instagram.service.PostServiceImpl;

import java.util.List;

public class TestPost {

    public static void main(String[] args) {

        PostService postService = new PostServiceImpl();
        PostController postController = new PostController(postService);

        // 1. CREATE POST (user 1)
        Post post = new Post();
        post.setUserId(1);
        post.setCaption("My first Instagram post");
        boolean created = postController.createPost(post);
        System.out.println("1. Create Post: " + created);

        // 2. VIEW OWN POSTS
        List<Post> ownPosts = postController.getOwnPosts(1);
        System.out.println("2. Own Posts: " + ownPosts.size());

        if (ownPosts.isEmpty()) {
            System.out.println("No post found - stopping test. Check DB connection / user_id 1 exists.");
            return;
        }
        int postId = ownPosts.get(0).getPostId();

        // 3. CREATE A SECOND POST FROM A DIFFERENT USER (change 2 to a real user_id in your DB)
        Post otherPost = new Post();
        otherPost.setUserId(2);
        otherPost.setCaption("Post from another user");
        boolean otherCreated = postController.createPost(otherPost);
        System.out.println("3. Create Post (user 2): " + otherCreated);

        // 4. VIEW OTHER USERS' POSTS (as user 1)
        List<Post> otherPosts = postController.getOtherUsersPosts(1);
        System.out.println("4. Other Users' Posts (should be >= 1): " + otherPosts.size());

        // 5. VIEW POST DETAILS
        Post result = postController.getPostById(postId);
        System.out.println("5. Post Details: " + (result != null ? "PASS" : "FAIL"));
        if (result != null) {
            System.out.println("   Caption: " + result.getCaption());
        }

        // 6. UPDATE OWN POST (as owner, user 1) - should succeed
        result.setCaption("Updated caption by owner");
        boolean updatedByOwner = postController.updateOwnPost(result);
        System.out.println("6. Update by owner (expect true): " + updatedByOwner);

        Post afterUpdate = postController.getPostById(postId);
        boolean captionChanged = afterUpdate != null && "Updated caption by owner".equals(afterUpdate.getCaption());
        System.out.println("7. Verify caption actually changed: " + captionChanged);

        // 8. UPDATE SOMEONE ELSE'S POST (as user 2, not owner) - should FAIL
        Post hijackAttempt = new Post();
        hijackAttempt.setPostId(postId);
        hijackAttempt.setUserId(2);
        hijackAttempt.setCaption("Hacked caption");
        boolean updatedByStranger = postController.updateOwnPost(hijackAttempt);
        System.out.println("8. Update by non-owner (expect false): " + updatedByStranger);

        // 9. DELETE SOMEONE ELSE'S POST (as user 2, not owner) - should FAIL
        boolean deletedByStranger = postController.deleteOwnPost(postId, 2);
        System.out.println("9. Delete by non-owner (expect false): " + deletedByStranger);

        // 10. DELETE OWN POST (as user 1, real owner) - should succeed
        boolean deletedByOwner = postController.deleteOwnPost(postId, 1);
        System.out.println("10. Delete by owner (expect true): " + deletedByOwner);

        // 11. CLEAN UP user 2's test post
        List<Post> user2Posts = postController.getOwnPosts(2);
        for (Post p : user2Posts) {
            postController.deleteOwnPost(p.getPostId(), 2);
        }

        System.out.println("Post testing completed.");
    }
}