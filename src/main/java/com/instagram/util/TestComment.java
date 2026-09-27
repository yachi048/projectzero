package com.instagram.util;

import com.instagram.controller.CommentController;
import com.instagram.controller.PostController;
import com.instagram.model.Comment;
import com.instagram.model.Post;
import com.instagram.service.CommentService;
import com.instagram.service.CommentServiceImpl;
import com.instagram.service.PostService;
import com.instagram.service.PostServiceImpl;

import java.util.List;

public class TestComment {

    public static void main(String[] args) {

        CommentService commentService = new CommentServiceImpl();
        CommentController commentController = new CommentController(commentService);

        PostService postService = new PostServiceImpl();
        PostController postController = new PostController(postService);

        int userA = 1;   // comment author
        int userB = 2;   // will attempt to edit/delete someone else's comment

        // 0. CREATE A POST TO COMMENT ON
        Post post = new Post();
        post.setUserId(userA);
        post.setCaption("Post created for TestComment");
        boolean postCreated = postController.createPost(post);
        System.out.println("0. Create Post for testing: " + postCreated);

        if (!postCreated) {
            System.out.println("Cannot continue - post creation failed.");
            return;
        }

        List<Post> ownPosts = postController.getOwnPosts(userA);
        int postId = ownPosts.get(ownPosts.size() - 1).getPostId();
        System.out.println("   Using postId: " + postId);

        // 1. ADD COMMENT
        Comment comment = new Comment();
        comment.setUserId(userA);
        comment.setPostId(postId);
        comment.setCommentText("Nice post!");

        boolean added = commentController.addComment(comment);
        System.out.println("1. Add Comment: " + added);
        System.out.println("   Comment ID: " + comment.getCommentId());

        // 2. ADD A REPLY to that comment
        Comment reply = new Comment();
        reply.setUserId(userB);
        reply.setPostId(postId);
        reply.setParentCommentId(comment.getCommentId());
        reply.setCommentText("I agree!");

        boolean replyAdded = commentController.addComment(reply);
        System.out.println("2. Add Reply: " + replyAdded);

        // 3. GET COMMENTS BY POST (should be 2: comment + reply)
        List<Comment> postComments = commentController.getCommentsByPostId(postId);
        System.out.println("3. Comments for Post " + postId + ": " + postComments.size());
        for (Comment c : postComments) {
            System.out.println("   Comment ID: " + c.getCommentId()
                    + " | User: " + c.getUserId()
                    + " | Parent: " + c.getParentCommentId()
                    + " | Text: " + c.getCommentText());
        }

        // 4. GET COMMENT BY ID
        Comment fetched = commentController.getCommentById(comment.getCommentId());
        System.out.println("4. Get Comment By Id: " + (fetched != null ? "PASS" : "FAIL"));

        // 5. COMMENT COUNT for post (expect 2)
        int count = commentController.getCommentCount(postId);
        System.out.println("5. Comment Count (expect 2): " + count);

        // 6. GET COMMENTS BY USER (userA should have at least 1)
        List<Comment> userComments = commentController.getCommentsByUserId(userA);
        System.out.println("6. Comments by userA: " + userComments.size());

        // 7. UPDATE OWN COMMENT (as owner, userA) - should succeed
        comment.setCommentText("Updated: Nice post indeed!");
        boolean updatedByOwner = commentController.updateOwnComment(comment);
        System.out.println("7. Update by owner (expect true): " + updatedByOwner);

        Comment afterUpdate = commentController.getCommentById(comment.getCommentId());
        boolean textChanged = afterUpdate != null
                && "Updated: Nice post indeed!".equals(afterUpdate.getCommentText());
        System.out.println("8. Verify text actually changed: " + textChanged);

        // 9. UPDATE SOMEONE ELSE'S COMMENT (as userB, not owner) - should FAIL
        Comment hijackAttempt = new Comment();
        hijackAttempt.setCommentId(comment.getCommentId());
        hijackAttempt.setUserId(userB);
        hijackAttempt.setCommentText("Hacked comment");
        boolean updatedByStranger = commentController.updateOwnComment(hijackAttempt);
        System.out.println("9. Update by non-owner (expect false): " + updatedByStranger);

        // 10. DELETE SOMEONE ELSE'S COMMENT (as userB, not owner) - should FAIL
        boolean deletedByStranger = commentController.deleteOwnComment(comment.getCommentId(), userB);
        System.out.println("10. Delete by non-owner (expect false): " + deletedByStranger);

        // 11. DELETE OWN COMMENT (as userA, real owner) - should succeed
        boolean deletedByOwner = commentController.deleteOwnComment(comment.getCommentId(), userA);
        System.out.println("11. Delete by owner (expect true): " + deletedByOwner);

        // 12. CLEAN UP - delete the reply too, then the test post
        commentController.deleteOwnComment(reply.getCommentId(), userB);
        boolean cleanedUp = postController.deleteOwnPost(postId, userA);
        System.out.println("12. Cleanup (delete test post): " + cleanedUp);

        System.out.println("Comment testing completed.");
    }
}