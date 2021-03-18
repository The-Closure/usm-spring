package org.closure.app.postModule.services;

import java.util.Date;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.PostEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostRequest;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.exceptions.PostErrorException;
import org.closure.app.postModule.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    public PostResponse addPost(Long userID, PostRequest request)
    {
        UserEntity userEntity = userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no user with this id"));
        if(userEntity.getCommuninty().getId() == null) 
            throw new CommunityErrorException("no community for this user");
        PostEntity postEntity = postRepo.save
            (
                new PostEntity()
                    .withAttach(request.getAttach())
                    .withCreated_at(new Date())
                    .withTitle(request.getTitle())
                    .withValue(request.getValue())
                    .withUEntity(userEntity)
                    .withPcommuninty(userEntity.getCommuninty())
            );
        return new PostResponse()
            .withAttach(postEntity.getAttach())
            .withPostID(postEntity.getId())
            .withTitle(postEntity.getTitle())
            .withUserID(postEntity.getUEntity().getId())
            .withCommunityID(postEntity.getPcommuninty().getId())
            .withValue(postEntity.getValue());
    }
    public PostResponse getpost(Long id)
    {
        PostEntity pEntity = postRepo.findById(id).orElseThrow(
            () -> new PostErrorException("no post with this id"));
        return new PostResponse()
            .withAttach(pEntity.getAttach())
            .withPostID(pEntity.getId())
            .withTitle(pEntity.getTitle())
            .withUserID(pEntity.getUEntity().getId())
            .withCommunityID(pEntity.getPcommuninty().getId())
            .withValue(pEntity.getValue());
    }
    public PostResponse updatePost(Long postID, Long userID, PostRequest request)
    {
        PostEntity pEntity = postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"));
            if(userRepo.findById(userID).orElseThrow(
                () -> new UserErrorException("no user with this id"))
                    .getPosts().stream().allMatch(
                        (p) -> !p.getId().equals(postID))) 
                            throw new PostErrorException("you don't have permissions to edit this post");
            pEntity = postRepo.save(pEntity 
            .withAttach(request.getAttach() != null ? request.getAttach() : pEntity.getAttach())
            .withTitle(request.getTitle() != null ? request.getTitle() : pEntity.getTitle())
            .withValue(request.getValue() != null ? request.getValue() : pEntity.getValue()));
            
            return new PostResponse()
                .withAttach(pEntity.getAttach())
                .withPostID(pEntity.getId())
                .withTitle(pEntity.getTitle())
                .withUserID(pEntity.getUEntity().getId())
                .withCommunityID(pEntity.getPcommuninty().getId())
                .withValue(pEntity.getValue());
    }
    public boolean deletePost(Long postID, Long userID)
    {
        PostEntity pEntity = postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"));
        userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"));
        if(!pEntity.getUEntity().getId().equals(userID)) return false;
        postRepo.deleteById(postID);
        return postRepo.findById(postID).isEmpty();
    }
    public CommunityResponse getCommunity(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getPcommuninty().toCommunityResponse();
    }
    public UserResponse getUser(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getUEntity().toUserResponse();
    }
}
