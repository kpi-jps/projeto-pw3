package com.pw3.aleatorypost.model.service;

import com.pw3.aleatorypost.model.domain.User;

import java.util.Optional;

public interface UserService {

    void save(User entity);

    void edit(User entity);

    User findById(Long id);

    Optional<User> findByLogin(String login);

    User findByEmail(String email);

    public boolean isUserNameAlreadyRegistred(String email);

}
