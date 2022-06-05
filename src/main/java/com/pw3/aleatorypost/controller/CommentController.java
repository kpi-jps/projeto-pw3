package com.pw3.aleatorypost.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pw3.aleatorypost.model.domain.Comment;
import com.pw3.aleatorypost.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController extends Controller{

    @Autowired
    private CommentService service;

    @RequestMapping(value = "/save_comment", method = RequestMethod.POST)
    public ResponseEntity<Object> saveComment(@RequestBody Comment comment, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.save(comment);
        return new ResponseEntity<>("Comment successifully save!", HttpStatus.OK);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity<Object> listPosts(HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        List<Comment> comments = service.searchAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPost(@PathVariable("id") Integer id, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        Comment comment = service.searchById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit_comment", method = RequestMethod.PUT)
    public ResponseEntity<Object> editPost(@RequestBody Comment comment, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.edit(comment);
        return new ResponseEntity<>("Comment successifully edited!", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete_comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable("id") Integer id, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.remove(id);
        return new ResponseEntity<>("Comment successifully removed!", HttpStatus.OK);
    }

    @RequestMapping(value = "/get_post_comments/{postId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostByUser(@PathVariable("postId") Integer postId, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        List<Comment> posts = service.searchByPostId(postId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
