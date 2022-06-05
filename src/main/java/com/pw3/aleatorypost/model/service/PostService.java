package com.pw3.aleatorypost.model.service;

import java.util.List;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

public interface PostService {

    void save(Post entity);

    void edit(Post entity);

    void remove(Integer id);

    Post searchById(Integer id);

    List<Post> searchByUserIdAndCategory(Integer userId, Category category);

    List<Post> searchByCategory(Category category);

    List<Post> searchAll();
}
