package com.pw3.aleatorypost.model.dao;

import java.util.List;

import com.pw3.aleatorypost.model.domain.Comment;

import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends AbstractDAO<Comment, Long> implements CommentDAO {

    @Override
    public List<Comment> findByPostId(Long postId) {
        String jpql = new StringBuilder("SELECT c FROM Comment c ")
                .append("WHERE c.post.id = ?1")
                .append("ORDER BY c.datetime DESC")
                .toString();
        return createQuery(jpql, postId);
    }
    
}
