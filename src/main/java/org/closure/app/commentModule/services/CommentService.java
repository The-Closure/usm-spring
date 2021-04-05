package org.closure.app.commentModule.services;

import java.util.List;

import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.commentModule.exceptions.CommentErrorException;
import org.closure.app.commentModule.mapper.CommentMapper;
import org.closure.app.commentModule.repositories.CommentRepo;
import org.closure.app.entities.CommentEntity;
import org.closure.app.postModule.exceptions.PostErrorException;
import org.closure.app.postModule.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;
    
    public CommentResponse addComment(Long userID, Long postID, CommentRequest request)
    {          
        return CommentMapper.INSTANCE.commentToResponse(
            commentRepo.save(
                new CommentEntity().value(request.getValue()).pentity(
                    postRepo.findById(postID).orElseThrow(
                        ()->new PostErrorException("no post with this id")
                    )
                ).uentity(
                    userRepo.findById(userID).orElseThrow(
                        ()-> new UserErrorException("no user with this id")
                    )
                )
            )
        ).postID(postID).userID(userID);
    }

    public CommentResponse getComment(Long commentID)
    {
        return CommentMapper.INSTANCE.commentToResponse(commentRepo.findById(commentID).orElseThrow(
            () -> new CommentErrorException("no comment with this id"))
        );
    }

    public CommentResponse updateComment(Long userID, Long postID, Long commentID, CommentRequest commentRequest)
    {
        userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"));
        postRepo.findById(postID).orElseThrow(
            () -> new UserErrorException("no post with this id"));
        return CommentMapper.INSTANCE.commentToResponse(
            commentRepo.save( commentRepo.findById(commentID).orElseThrow(
                    () -> new UserErrorException("no comment with this id")).value(commentRequest.getValue())));
    }

    public boolean deleteComment(Long userID, Long commentID)
    {
        boolean isCommentOwner = commentRepo.findById(commentID).orElseThrow(
            ()-> new CommentErrorException("no comment with this id")).getUentity().getId().equals(userID);
        
        boolean isPostOwner = commentRepo.findById(commentID).orElseThrow(
            ()-> new CommentErrorException("no comment with this id")).getPentity().getUEntity().getId().equals(userID);
        if(isCommentOwner || isPostOwner)
            commentRepo.deleteById(commentID);
        return isCommentOwner || isPostOwner;

    }
    
    public List<CommentResponse> getCommentsForPost(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getComments().stream().map(CommentMapper.INSTANCE::commentToResponse).toList();
    }
}
