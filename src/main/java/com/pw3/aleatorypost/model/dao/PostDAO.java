package com.pw3.aleatorypost.model.dao;
import java.util.List;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

public interface PostDAO {
    void save(Post post);
    void update(Post post);
    void delete(Integer id);
    Post findById(Integer id);
    List<Post> findByUserIdAndCategory(Integer userId, Category category);
    List<Post> findByCategory(Category category);
    List<Post> findAll();
}