package org.closure.app.postModule.controllers;

import java.util.List;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.postModule.dto.PostRequest;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path = "/v2/api/posts")
public class PostController {
    
    @Autowired
    PostService postService;

    @PutMapping(value="/addpost/{userID}")
    public PostResponse addPost
        (
            @PathVariable(name = "userID") String userID, 
            @RequestBody PostRequest request
        ) 
    {
        return postService.addPost(Long.parseLong(userID), request);
    }

    @GetMapping(value="/getpost")
    public PostResponse getPost(@RequestParam(name = "postID") String postID) {
        return postService.getpost(Long.parseLong(postID));
    }

    @PutMapping(value="updatepost/{userID}/{postID}")
    public PostResponse putMethodName
        (
            @PathVariable(name = "userID") String userID, 
            @PathVariable(name = "postID") String postID, 
            @RequestBody PostRequest request
        )
    {
        return postService.updatePost(Long.parseLong(postID), Long.parseLong(userID), request);
    }

    @DeleteMapping(path = "/delete")
    public boolean deletePost
        (
            @RequestParam(name = "userID") String userID, 
            @RequestParam(name = "postID") String postID 
        )
    {
        return postService.deletePost(Long.parseLong(postID), Long.parseLong(userID));
    }
    
    @GetMapping(value="/getOwner")
    public UserResponse getOwner(@RequestParam(name = "postID") String postID) {
        return postService.getUser(Long.parseLong(postID));
    }
    
    @GetMapping(value="/getCommunity")
    public CommunityResponse getCommunity(@RequestParam(name = "postID") String postID) {
        return postService.getCommunity(Long.parseLong(postID));
    }
 
    @GetMapping(value = "getposts")
    public ResponseEntity<List<PostResponse>> getAllPosts(
                        @RequestParam(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        @RequestParam(name = "communityID") Long communityID,
                        @RequestParam(defaultValue = "id", required = false,value = "id") String sortBy,
                        @RequestParam(name = "userID") Long userID)
    {
        List<PostResponse> list = postService.getAllPosts(communityID, pageNo, pageSize, sortBy,userID);
 
        return new ResponseEntity<List<PostResponse>>(list, new HttpHeaders(), HttpStatus.OK); 
    }

    @GetMapping(value="/getcomments/{postID}")
    public ResponseEntity<List<CommentResponse>> getCommentsForPost(@PathVariable(name = "postID") Long postID) {
        return new ResponseEntity<List<CommentResponse>>(
            postService.getCommentsForPost(postID), 
            new HttpHeaders(), 
            HttpStatus.OK
        ); 
    }
    
}
