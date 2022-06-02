package com.pw3.aleatorypost.controller;

import com.pw3.aleatorypost.model.domain.User;
import com.pw3.aleatorypost.model.service.UserService;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends Controller {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/is_authenticate", method = RequestMethod.GET)
    public ResponseEntity<Object> isUserAuthenticate(HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>("User already authenticated!", HttpStatus.OK);    
    }

    @RequestMapping(value = "/get_user", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()) &&
            request.getAttribute("id") != null)
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        Long userId = (Long)request.getAttribute("id");
        User user = service.findById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> doLogin(@RequestBody User user, HttpServletRequest request) {
        if(authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("User already authenticated!", HttpStatus.OK);
        if(authenticationService.authenticate(user.getEmail(), user.getPass(), request.getSession()))
            return new ResponseEntity<>("User authenticated!", HttpStatus.OK);
        return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);    
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<Object> doLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ResponseEntity<>("User logged out!", HttpStatus.OK);
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ResponseEntity<Object> saveUser(@RequestBody User user, HttpServletRequest request) {
        if(service.isUserNameAlreadyRegistred(user.getEmail()))
            return new ResponseEntity<>("User already registred!", HttpStatus.OK);
        user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()));
        
        service.save(user);
        return new ResponseEntity<>("User save!", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    /*
    @RequestMapping(value = "/user/{email}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        User user = service.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    */

    @RequestMapping(value = "/edit_user", method = RequestMethod.PUT)
    public ResponseEntity<Object> editUser(@RequestBody User user, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.edit(user);
        return new ResponseEntity<>("User successifully edited!", HttpStatus.OK);
    }

}
