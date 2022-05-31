package com.pw3.aleatorypost.model.service;

import com.pw3.aleatorypost.model.dao.UserDAO;
import com.pw3.aleatorypost.model.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService { 

    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = false)
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void edit(User user) {
        userDAO.update(user); 
    }
    
    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
    @Override
    public boolean isUserNameAlreadyRegistred(String email) {
        if (userDAO.findByEmail(email) == null) return false;
        return true;
    }

}
