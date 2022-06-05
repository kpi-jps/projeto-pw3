package com.pw3.aleatorypost.model.dao;

import java.util.List;


import com.pw3.aleatorypost.model.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends AbstractDAO<User, Integer> implements UserDAO {

    @Override
    public User findByEmail(String email) {
        List<User> users = createQuery("select u from User u where u.email like ?1", email);
        if(users.isEmpty()) return null;
        return users.get(0);
    }
}
