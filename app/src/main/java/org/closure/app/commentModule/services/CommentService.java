package org.closure.app.commentModule.services;

import java.util.List;

import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.commentModule.dto.CommentRequest;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.commentModule.exceptions.CommentErrorException;
import org.closure.app.commentModule.repositories.CommentRepo;
import org.closure.app.entities.CommentEntity;
import org.closure.app.entities.PostEntity;
import org.closure.app.entities.UserEntity;
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
        PostEntity pEntity = postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"));
        UserEntity uEntity = userRepo.findById(userID).orElseThrow(
            () -> new PostErrorException("no user with this id"));
         CommentEntity cEntity = commentRepo.save(
            new CommentEntity()
                .withPEntity(pEntity)
                .withUEntity(uEntity)
                .withValue(request.getValue())
            );
            
        CommentResponse commentResponse =new CommentResponse()
            .withId(cEntity.getId())
            .withPostID(cEntity.getPEntity().getId())
            .withUserID(cEntity.getUEntity().getId())
            .withValue(cEntity.getValue());
        return commentResponse;
    }

    public CommentResponse getComment(Long commentID)
    {
        CommentEntity commentEntity = commentRepo.findById(commentID).orElseThrow(
            () -> new CommentErrorException("no comment with this id"));
        return new CommentResponse()
            .withId(commentEntity.getId())
            .withPostID(commentEntity.getPEntity().getId())
            .withUserID(commentEntity.getUEntity().getId())
            .withValue(commentEntity.getValue());
    }

    public CommentResponse updateComment(Long userID, Long postID, Long commentID, CommentRequest commentRequest)
    {
        userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"));
        postRepo.findById(postID).orElseThrow(
            () -> new UserErrorException("no post with this id"));
        CommentEntity cEntity = commentRepo.findById(commentID).orElseThrow(
            () -> new UserErrorException("no comment with this id"));
        cEntity.setValue(commentRequest.getValue());
        commentRepo.save(cEntity);
        return new CommentResponse()
            .withId(cEntity.getId())
            .withPostID(cEntity.getPEntity().getId())
            .withUserID(cEntity.getUEntity().getId())
            .withValue(cEntity.getValue());
    }

    public boolean deleteComment(Long userID, Long commentID)
    {
        boolean isCommentOwner = commentRepo.findById(commentID).orElseThrow(
            ()-> new CommentErrorException("no comment with this id")).getUEntity().getId().equals(userID);
        
        boolean isPostOwner = commentRepo.findById(commentID).orElseThrow(
            ()-> new CommentErrorException("no comment with this id")).getPEntity().getUEntity().getId().equals(userID);
        if(isCommentOwner || isPostOwner)
            commentRepo.deleteById(commentID);
        return isCommentOwner || isPostOwner;

    }
    
    public List<CommentResponse> getCommentsForPost(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getComments().stream().map
                    (
                        (mapper)-> new CommentResponse()
                            .withId(mapper.getId())
                            .withPostID(mapper.getPEntity().getId())
                            .withUserID(mapper.getUEntity().getId())
                            .withValue(mapper.getValue())
                    )
                .toList();
    }
}
