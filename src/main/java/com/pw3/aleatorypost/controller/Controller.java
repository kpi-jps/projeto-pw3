package com.pw3.aleatorypost.controller;

import com.pw3.aleatorypost.model.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Controller {
    @Autowired
    protected AuthenticationService authenticationService;
}
