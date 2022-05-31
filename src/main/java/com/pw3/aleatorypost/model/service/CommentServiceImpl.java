package com.pw3.aleatorypost.model.service;

import java.util.List;

import com.pw3.aleatorypost.model.dao.CommentDAO;
import com.pw3.aleatorypost.model.domain.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Transactional(readOnly = false)
    @Override
    public void save(Comment comment) {
        commentDAO.save(comment); 
    }

    @Transactional(readOnly = false)
    @Override
    public void edit(Comment comment) {
        commentDAO.update(comment); 
    }
    
    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        commentDAO.delete(id);  
    }

    @Override
    public Comment searchById(Long id) {
        return commentDAO.findById(id);
    }

    @Override
    public List<Comment> searchAll() {
        return commentDAO.findAll();
    }

    @Override
    public List<Comment> searchByPostId(Long postId) {
        return commentDAO.findByPostId(postId);
    }

}
