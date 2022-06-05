package com.pw3.aleatorypost.model.service;

import java.util.List;

import com.pw3.aleatorypost.model.dao.PostDAO;
import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostDAO postDAO;

    @Transactional(readOnly = false)
    @Override
    public void save(Post post) {
        postDAO.save(post); 
    }

    @Transactional(readOnly = false)
    @Override
    public void edit(Post post) {
        postDAO.update(post); 
    }
    
    @Transactional(readOnly = false)
    @Override
    public void remove(Integer id) {
        postDAO.delete(id);  
    }

    @Override
    public Post searchById(Integer id) {
        return postDAO.findById(id);
    }

    @Override
    public List<Post> searchAll() {
        return postDAO.findAll();
    }

    @Override
    public List<Post> searchByUserIdAndCategory(Integer userId, Category category) {
        return postDAO.findByUserIdAndCategory(userId, category);
    }

    @Override
    public List<Post> searchByCategory(Category category) {
        return postDAO.findByCategory(category);
    }

}
