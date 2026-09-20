package com.instagram.service;

import com.instagram.model.Comment;
import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByPostId(int postId);

    List<Comment> getCommentsByUserId(int userId);

    boolean updateComment(Comment comment);

    boolean deleteComment(int commentId);
}