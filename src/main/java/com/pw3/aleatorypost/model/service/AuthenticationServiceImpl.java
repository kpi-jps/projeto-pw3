package com.pw3.aleatorypost.model.service;

import javax.servlet.http.HttpSession;

import com.pw3.aleatorypost.model.dao.UserDAO;
import com.pw3.aleatorypost.model.domain.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDAO userDAO;
    
    @Override
    public boolean isAuthenticate(HttpSession session) {
        if(session.getAttribute("id") != null &&
            session.getAttribute("username") != null && 
            session.getAttribute("password") != null) {
                Long id = (Long) session.getAttribute("id");
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                User user = userDAO.findById(id);
                if(user == null) return false;
                return user.getEmail().equals(username) && BCrypt.checkpw(password, user.getPass());
            }
        return false;
    }

    @Override
    public boolean authenticate(String email, String password, HttpSession session) {
        User userFound = userDAO.findByEmail(email);
        if(userFound == null) return false;
        session.setAttribute("id", userFound.getId());
        session.setAttribute("username", email);
        session.setAttribute("password", password);
        return userFound.getEmail().equals(email) && BCrypt.checkpw(password, userFound.getPass());
    }
    
}
