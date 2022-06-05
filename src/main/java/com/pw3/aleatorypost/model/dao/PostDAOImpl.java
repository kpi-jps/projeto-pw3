package com.pw3.aleatorypost.model.dao;

import java.util.List;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl extends AbstractDAO<Post, Integer> implements PostDAO {

    @Override
    public List<Post> findByUserIdAndCategory(Integer userId, Category category) {
        String jpql = new StringBuilder("SELECT p FROM Post p ")
                .append("WHERE p.user.id = ?1 AND p.category = ?2")
                .append("ORDER BY p.date DESC")
                .toString();
        return createQuery(jpql, userId, category);
    }

    @Override
    public List<Post> findByCategory(Category category) {
        String jpql = new StringBuilder("SELECT p FROM Post p ")
                .append("WHERE p.category = ?1")
                .append("ORDER BY p.date DESC")
                .toString();
        return createQuery(jpql, category);
    }

}
