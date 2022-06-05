package com.pw3.aleatorypost.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pw3.aleatorypost.model.domain.Category;
import com.pw3.aleatorypost.model.domain.Post;
import com.pw3.aleatorypost.model.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController extends Controller{

    @Autowired
    private PostService service;

    @RequestMapping(value = "/save_post", method = RequestMethod.POST)
    public ResponseEntity<Object> savePost(@RequestBody Post post, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.save(post);
        return new ResponseEntity<>("Post successifully save!", HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<Object> listPosts(HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        List<Post> posts = service.searchAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPost(@PathVariable("id") Integer id, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        Post post = service.searchById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit_post", method = RequestMethod.PUT)
    public ResponseEntity<Object> editPost(@RequestBody Post post, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.edit(post);
        return new ResponseEntity<>("Post successifully edited!", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete_post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePost(@PathVariable("id") Integer id, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        service.remove(id);
        return new ResponseEntity<>("Post successifully removed!", HttpStatus.OK);
    }

    @RequestMapping(value = "/get_user_posts/{userId}/{category}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostByUser(@PathVariable("userId") Integer userId, @PathVariable("category") Category category, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        List<Post> posts = service.searchByUserIdAndCategory(userId, category);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/get_posts_by_category/{category}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostByCategory(@PathVariable("category") Category category, HttpServletRequest request) {
        if(!authenticationService.isAuthenticate(request.getSession()))
            return new ResponseEntity<>("Unauthorized access!", HttpStatus.UNAUTHORIZED);
        List<Post> posts = service.searchByCategory(category);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
