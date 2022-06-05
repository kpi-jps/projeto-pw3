package com.pw3.aleatorypost.model.dao;


import com.pw3.aleatorypost.model.domain.User;


public interface UserDAO {
    void save(User user);
    void update(User task);
    User findById(Integer id);
    User findByEmail(String email);
}
