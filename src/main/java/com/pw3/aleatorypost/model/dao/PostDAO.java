package com.pw3.aleatorypost.model.dao;
import java.util.List;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

public interface PostDAO {
    void save(Post post);
    void update(Post post);
    void delete(Long id);
    Post findById(Long id);
    List<Post> findByUserIdAndCategory(Long userId, Category category);
    List<Post> findAll();
}