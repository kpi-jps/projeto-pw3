package com.pw3.aleatorypost.model.dao;
import java.util.List;

import com.pw3.aleatorypost.model.domain.Comment;


public interface CommentDAO {
    void save(Comment comment);
    void update(Comment comment);
    void delete(Integer id);
    Comment findById(Integer id);
    List<Comment> findByPostId(Integer postId);
    List<Comment> findAll();
}