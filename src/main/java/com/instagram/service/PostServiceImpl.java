package com.instagram.service;

import com.instagram.dao.PostDAO;
import com.instagram.dao.PostDAOImpl;
import com.instagram.exception.PostException;
import com.instagram.model.Post;

import java.util.List;
import java.util.logging.Logger;

public class PostServiceImpl implements PostService {

    private static final Logger logger =
            Logger.getLogger(PostServiceImpl.class.getName());

    private final PostDAO postDAO;

    public PostServiceImpl() {

        this.postDAO = new PostDAOImpl();

        logger.info(
                "PostServiceImpl initialized"
        );
    }

    public PostServiceImpl(PostDAO postDAO) {

        this.postDAO = postDAO;

        logger.info(
                "PostServiceImpl initialized with DAO"
        );
    }

    @Override
    public boolean createPost(Post post) {

        logger.info(
                "Create post request received"
        );

        if (post == null) {

            logger.warning(
                    "Post is null"
            );

            throw new PostException(
                    "Post cannot be null"
            );
        }

        if (post.getUserId() <= 0) {

            logger.warning(
                    "Invalid userId: "
                            + post.getUserId()
            );

            throw new PostException(
                    "User ID must be greater than 0"
            );
        }

        if (post.getCaption() == null
                || post.getCaption().trim().isEmpty()) {

            logger.warning(
                    "Post caption is empty"
            );

            throw new PostException(
                    "Post caption is required"
            );
        }

        boolean result =
                postDAO.addPost(post);

        if (result) {

            logger.info(
                    "Post created successfully for userId: "
                            + post.getUserId()
            );

        } else {

            logger.warning(
                    "Post creation failed for userId: "
                            + post.getUserId()
            );
        }

        return result;
    }

    @Override
    public List<Post> getOwnPosts(
            int userId) {

        logger.info(
                "Getting own posts for userId: "
                        + userId
        );

        validateUserId(userId);

        List<Post> posts =
                postDAO.getPostsByUserId(
                        userId
                );

        logger.info(
                "Own posts retrieved: "
                        + posts.size()
        );

        return posts;
    }

    @Override
    public List<Post> getOtherUsersPosts(
            int userId) {

        logger.info(
                "Getting other users' posts for userId: "
                        + userId
        );

        validateUserId(userId);

        List<Post> posts =
                postDAO.getOtherUsersPosts(
                        userId
                );

        logger.info(
                "Other users' posts retrieved: "
                        + posts.size()
        );

        return posts;
    }

    @Override
    public Post getPostById(
            int postId) {

        logger.info(
                "Getting post details for postId: "
                        + postId
        );

        validatePostId(postId);

        Post post =
                postDAO.getPostById(
                        postId
                );

        if (post != null) {

            logger.info(
                    "Post retrieved successfully for postId: "
                            + postId
            );

        } else {

            logger.warning(
                    "Post not found for postId: "
                            + postId
            );

            throw new PostException(
                    "Post not found with ID: "
                            + postId
            );
        }

        return post;
    }

    @Override
    public boolean updateOwnPost(
            Post post) {

        logger.info(
                "Update own post request for postId: "
                        + (post != null
                        ? post.getPostId()
                        : "null")
        );

        if (post == null) {

            logger.warning(
                    "Post is null"
            );

            throw new PostException(
                    "Post cannot be null"
            );
        }

        validatePostId(
                post.getPostId()
        );

        validateUserId(
                post.getUserId()
        );

        if (post.getCaption() == null
                || post.getCaption().trim().isEmpty()) {

            logger.warning(
                    "Post caption is empty"
            );

            throw new PostException(
                    "Post caption is required"
            );
        }

        boolean result =
                postDAO.updatePost(post);

        if (result) {

            logger.info(
                    "Post updated successfully: "
                            + post.getPostId()
            );

        } else {

            logger.warning(
                    "Post update failed or user is not owner: "
                            + post.getPostId()
            );
        }

        return result;
    }

    @Override
    public boolean deleteOwnPost(
            int postId,
            int userId) {

        logger.info(
                "Delete own post request for postId: "
                        + postId
                        + ", userId: "
                        + userId
        );

        validatePostId(postId);

        validateUserId(userId);

        boolean result =
                postDAO.deletePost(
                        postId,
                        userId
                );

        if (result) {

            logger.info(
                    "Post deleted successfully: "
                            + postId
            );

        } else {

            logger.warning(
                    "Post deletion failed or user is not owner: "
                            + postId
            );
        }

        return result;
    }

    private void validateUserId(
            int userId) {

        if (userId <= 0) {

            throw new PostException(
                    "User ID must be greater than 0"
            );
        }
    }

    private void validatePostId(
            int postId) {

        if (postId <= 0) {

            throw new PostException(
                    "Post ID must be greater than 0"
            );
        }
    }
}