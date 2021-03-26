package org.closure.app.commentModule.controllers;

import java.util.List;

import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.commentModule.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "/v2/api/comments")
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping(value="/add")
    public CommentResponse addComment
    (
        @RequestParam(name = "userID") Long userID, 
        @RequestParam(name = "postID") Long postID, 
        @RequestBody CommentRequest request 
    ) 
    {
        return commentService.addComment(userID, postID, request);
    }

    @GetMapping(value="/getcomment")
    public CommentResponse getComment(@RequestParam Long commentID) 
    {
        return commentService.getComment(commentID);
    }

    @PutMapping(value="updatecomment/{userID}/{postID}/{commentID}")
    public CommentResponse updateComment
    (
        @PathVariable(name = "userID") String userID,
        @PathVariable(name = "postID") String postID,
        @PathVariable(name = "commentID") String commentID, 
        @RequestBody CommentRequest request
    )
    {
        return commentService.updateComment
            (
                Long.parseLong(userID), 
                Long.parseLong(postID), 
                Long.parseLong(commentID), 
                request
            );  
    }

    @DeleteMapping(value = "delete/{userID}/{commentID}")
    public boolean deleteComment
    (
        @PathVariable(name = "userID") String userID,
        @PathVariable(name = "commentID") String commentID
    )
    {
        return commentService.deleteComment
            (
                Long.parseLong(userID), 
                Long.parseLong(commentID)
            );
    }

    @GetMapping(value="/getcomments/{postID}")
    public List<CommentResponse> getCommentsForPost(@PathVariable(name = "postID") String postID) 
    {
        return commentService.getCommentsForPost(Long.parseLong(postID));
    }
    
    
}
