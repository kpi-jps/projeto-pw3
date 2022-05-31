package com.pw3.aleatorypost.model.dao;
import java.util.List;

import com.pw3.aleatorypost.model.domain.Comment;


public interface CommentDAO {
    void save(Comment comment);
    void update(Comment comment);
    void delete(Long id);
    Comment findById(Long id);
    List<Comment> findByPostId(Long postId);
    List<Comment> findAll();
}