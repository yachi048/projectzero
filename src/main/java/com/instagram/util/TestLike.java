package com.instagram.util;

import com.instagram.controller.LikeController;
import com.instagram.controller.PostController;
import com.instagram.model.Like;
import com.instagram.model.Post;
import com.instagram.service.LikeService;
import com.instagram.service.LikeServiceImpl;
import com.instagram.service.PostService;
import com.instagram.service.PostServiceImpl;

import java.util.List;

public class TestLike {

    public static void main(String[] args) {

        LikeService likeService = new LikeServiceImpl();
        LikeController likeController = new LikeController(likeService);

        PostService postService = new PostServiceImpl();
        PostController postController = new PostController(postService);

        int userId = 1;

        // ==========================================
        // 0. CREATE A POST TO LIKE (so this test doesn't depend on old data)
        // ==========================================

        Post post = new Post();
        post.setUserId(userId);
        post.setCaption("Post created for TestLike");

        boolean postCreated = postController.createPost(post);
        System.out.println("0. Create Post for testing: " + postCreated);

        if (!postCreated) {
            System.out.println("Cannot continue - post creation failed. Check DB connection / user_id " + userId + " exists.");
            return;
        }

        List<Post> ownPosts = postController.getOwnPosts(userId);
        int postId = ownPosts.get(ownPosts.size() - 1).getPostId();   // the one just created
        System.out.println("   Using postId: " + postId);

        // ==========================================
        // 1. LIKE POST
        // ==========================================

        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);

        boolean liked = likeController.likePost(like);
        System.out.println("1. Like Post: " + liked);

        // ==========================================
        // 2. CHECK LIKE
        // ==========================================

        boolean hasLiked = likeController.hasUserLikedPost(userId, postId);
        System.out.println("2. Has User Liked Post: " + hasLiked);

        // ==========================================
        // 3. LIKE COUNT
        // ==========================================

        int count = likeController.getLikeCount(postId);
        System.out.println("3. Like Count: " + count);

        // ==========================================
        // 4. GET LIKES BY POST
        // ==========================================

        List<Like> postLikes = likeController.getLikesByPostId(postId);
        System.out.println("4. Likes for Post " + postId + ": " + postLikes.size());

        for (Like l : postLikes) {
            System.out.println("   Like ID: " + l.getLikeId());
            System.out.println("   User ID: " + l.getUserId());
            System.out.println("   Post ID: " + l.getPostId());
        }

        // ==========================================
        // 5. GET LIKES BY USER
        // ==========================================

        List<Like> userLikes = likeController.getLikesByUserId(userId);
        System.out.println("5. Likes by User " + userId + ": " + userLikes.size());

        // ==========================================
        // 6. DUPLICATE LIKE (should fail/be rejected)
        // ==========================================

        Like duplicateLike = new Like();
        duplicateLike.setUserId(userId);
        duplicateLike.setPostId(postId);

        boolean duplicateLiked;
        try {
            duplicateLiked = likeController.likePost(duplicateLike);
        } catch (Exception e) {
            duplicateLiked = false;
            System.out.println("   (duplicate like threw exception: " + e.getMessage() + ")");
        }
        System.out.println("6. Duplicate Like (expect false): " + duplicateLiked);

        // ==========================================
        // 7. UNLIKE POST
        // ==========================================

        boolean unliked = likeController.unlikePost(userId, postId);
        System.out.println("7. Unlike Post: " + unliked);

        // ==========================================
        // 8. CHECK AGAIN
        // ==========================================

        boolean hasLikedAfterUnlike = likeController.hasUserLikedPost(userId, postId);
        System.out.println("8. Has User Liked After Unlike: " + hasLikedAfterUnlike);

        // ==========================================
        // 9. CLEAN UP - delete the test post
        // ==========================================

        boolean cleanedUp = postController.deleteOwnPost(postId, userId);
        System.out.println("9. Cleanup (delete test post): " + cleanedUp);

        System.out.println("Like testing completed.");
    }
}