package com.pw3.aleatorypost.model.service;

import javax.servlet.http.HttpSession;


public interface AuthenticationService  {

    public boolean isAuthenticate(HttpSession session);
    public boolean authenticate(String email, String password, HttpSession session);
    
}
