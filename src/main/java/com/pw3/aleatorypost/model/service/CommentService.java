package com.pw3.aleatorypost.model.service;

import java.util.List;

import com.pw3.aleatorypost.model.domain.Comment;

public interface CommentService {

    void save(Comment entity);

    void edit(Comment entity);

    void remove(Integer id);

    Comment searchById(Integer id);

    List<Comment> searchAll();

    List<Comment> searchByPostId(Integer postId);
}
 
