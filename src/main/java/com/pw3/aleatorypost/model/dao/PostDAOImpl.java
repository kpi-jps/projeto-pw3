package com.pw3.aleatorypost.model.dao;

import java.util.List;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;

import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl extends AbstractDAO<Post, Long> implements PostDAO {

    @Override
    public List<Post> findByUserIdAndCategory(Long userId, Category category) {
        String jpql = new StringBuilder("SELECT p FROM Post p ")
                .append("WHERE p.user.id = ?1 AND p.category = ?2")
                .append("ORDER BY p.datetime DESC")
                .toString();
        return createQuery(jpql, userId, category);
    }

}
